package com.xyk.sggredisstudy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-19 15:09
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/redis/connection/")
@Api(tags = "测试连接redis")
public class RedisConnectionTestController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Jedis jedis;


    @ApiOperation(value = "测试项目是否启动成功!(通过jedis操作redis)")
    @GetMapping("jedis")
    public String testRunner() {
        jedis.set("name","lucy");
        String name = jedis.get("name");
        System.out.println(name);
        return "the program is running!" + "  name = "+ name;
    }

    @ApiOperation(value = "测试项目是否启动成功!(通过redisTemplate操作redis)")
    @GetMapping("redisTemplate")
    public String testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name","露西");
        //从redis获取值(注意空指针异常,使用(String)强制转换,尽量避免使用.toString() 从而避免空指针异常 )
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
        return "the program is running!" + "  name = "+ name;
    }

    @ApiOperation(value = "通过redis实现分布式锁")
    @GetMapping("redis_lock")
    public String redis_lock() {
        // 0.分布式锁的锁名(任意)
        String lock = "redis_lock";
        //0.分布式锁的服务器线程标识/版本号,只能释放自己的锁
        String uuid = UUID.randomUUID().toString().replace("-","");
        //1.创建/获取 分布式锁,该锁的过期时间为12秒 ，setnx
        //(一定要在创建锁的同时设置锁的过期时间,要不然一旦当前线程出错导致没有执行释放锁的代码,其他线程将永远阻塞)
        Boolean unlock = redisTemplate.opsForValue().setIfAbsent(lock, uuid, Duration.ofSeconds(10));
        //2.获取锁状态
        // unlock:true => 分布式环境下,无其他线程在访问此程序
        // unlock:false = > 分布式环境下,有其他线程正在访问此程序,此时应当进入阻塞状态稍后再次访问
        if (unlock){
            // 3.需要锁住的代码块
            Object num = redisTemplate.opsForValue().get("num");
            if (Objects.isNull(num)){
                redisTemplate.opsForValue().set("num", 0,Duration.ofSeconds(10));
                // 4.释放锁(可以把释放锁的代码注释掉,演示一下不释放锁的效果)
                redisUnlock(lock, uuid);
                return "num : 0";
            }else {
                int i = Integer.parseInt(num + "");
                redisTemplate.opsForValue().set("num",++i,Duration.ofSeconds(10));
                // 4.释放锁(可以把释放锁的代码注释掉,演示一下不释放锁的效果)
                redisUnlock(lock, uuid);
                return "num : " + i;
            }
        }else{
            try {
                // 5.0.1S后再次尝试
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                return redis_lock();
            }
        }
    }

    /**
     * 通过lua脚本释放redis分布式锁,lua脚本能够保证原子性
     * @param unlockKey 需要释放锁的锁名
     * @param uuid 当前服务器的线程生成锁的版本号
     * */
    private void redisUnlock(String unlockKey ,String uuid) {
        /*使用lua脚本来释放锁*/
        // 定义lua 脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        // 使用redis执行lua执行
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        // 设置一下返回值类型 为Long
        // 因为删除判断的时候，返回的0,给其封装为数据类型。如果不封装那么默认返回String 类型，
        // 那么返回字符串与0 会有发生错误。
        redisScript.setResultType(Long.class);
        // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
        redisTemplate.execute(redisScript, Arrays.asList(unlockKey), uuid);

    }

}
