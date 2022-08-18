package com.xyk.config;

import com.xyk.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author 徐亚奎
 * @date 2021/7/10 8:35
 * 本类用于测试winwos下的redis连接
 */
@Slf4j
public class ConnectRedis_Pool {
    ShardedJedisPool pool=null;
    /**
     * 通过Jedis单个链接redis
     * */
    @Test
    public void testConnectRedis(){
        System.out.println("正在通过Jedis链接redis!");
        Jedis jedis = new Jedis(Constant.HOST,Constant.PORT);
        System.out.println("Jedis连接redis成功!");
//        jedis.auth(Constant.PASSWORD);
        jedis.set("user", "徐亚奎");
        System.out.println("所有的key"+jedis.keys("*"));
        System.out.println("------------------");
        Set<String> keys = jedis.keys("*");
        for (String s:keys){
            System.out.println(s+" : "+jedis.get(s));
        }
        System.out.println("------------------");
        System.out.println("以上为redis中的所有键值对的K:V值!");
    }

    /**
     * 通过集群模式(连接池)的方式链接redis(集群模式/也可当单个使用)
     * 必须要有redis密码.redis如果没有密码会报错!
     * */
    @Before
    public void testConnectRedisPool(){
        System.out.println("正在通过集群模式链接redis!");
//        配置redis的连接信息(集群模式/也可当单个使用)
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(Constant.HOST,Constant.PORT);
        jedisShardInfo1.setPassword(Constant.PASSWORD);
//        将redis的连接信息封装到list集合中
        LinkedList<JedisShardInfo> jedisShardInfos = new LinkedList<>();
        jedisShardInfos.add(jedisShardInfo1);
//        redis连接池的配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(15);
        config.setMaxIdle(5);//如果不自己手动配置,则默认值为8
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

//        config.setBlockWhenExhausted(true);
//        根据上述配置创建连接池,并获取连接
         pool = new ShardedJedisPool(config, jedisShardInfos);
//        ShardedJedis redis = pool.getResource();
//        redis.set("members", "杨建超");
//        System.out.println(redis.get("members"));
////        将redis连接归还到连接池
//        redis.close();
    }
    @Test
    public void testJedisPoolConfig_MaxTotal(){
//        此方法用于测试连接池的配置参数
//        参数1: config.setMaxTotal(15);
//              最大可从redis连接池中获取的连接数,如果只是获取连接,用完也不.close关掉连接则会报错:
//        redis.clients.jedis.exceptions.JedisExhaustedPoolException: Could not get a resource since the pool is exhausted
        for (int i =0; i<30;i++){
            ShardedJedis resource = pool.getResource();
            System.out.println(resource);
//            resource.close();
        }
    }
    @Test
    public void testJedisPoolConfig_Max_minIdle(){
//        此方法用于测试连接池的配置参数:SetMaxIdle
//        参数2: config.setMaxIdle(5);
//              控制一个pool最多有多少个状态为idle(空闲)的jedis实例,默认值为8；
//        参数3:config.setMinIdle(5);
//              在容器中的最小的闲置连接数，确保在对象逐出线程工作后确保线程池中有最小的实例数量,默认值为0
//              仅仅在此值为正数且timeBetweenEvictionRunsMillis值大于0时有效,，如果该值设定大于maxidle的值，此值不会生效，maxidle的值会生效
        for (int i =0; i<30;i++){
            ShardedJedis resource = pool.getResource();
            System.out.println(resource);
        }
    }
    @Test
    public void testJedisPoolConfig_BlockWhenExhausted_MaxWaitMillis() throws InterruptedException {
//        此方法用于测试连接池的配置参数:BlockWhenExhausted  and  MaxWaitMillis
//        参数4: config.setBlockWhenExhausted(true);
//              当连接池内的连接耗尽时阻塞，getBlockWhenExhausted为true时(默认为true)，
//              超过了阻塞的时间（设定的maxWaitMillis，单位毫秒）时会报错
//        此方法用于测试连接池的配置参数:setMaxWaitMillis
//        参数5: config.setMaxWaitMillis(3000);
//              最大等待时间(阻塞)为3秒,超时未获取到连接才抛异常
//              如果BlockWhenExhausted为false,那么会直接抛异常,不会等待
        ArrayList<ShardedJedis> redisList = new ArrayList<>();
        for (int i =0; i<30;i++){
//            System.out.println("当前连接池中--正在使用--的连接数量:"+pool.getNumActive());//正在使用的连接
//            System.out.println("当前连接池中-等待使用-的连接数量:"+pool.getNumWaiters());
            System.out.println("当前连接池中-等待使用-的连接数量:"+pool.getNumIdle());
            ShardedJedis resource = pool.getResource();

            System.out.println("从连接池中获取第"+i+"连接:"+resource);
            redisList.add(resource);

//            System.out.println("当前连接池中--正在使用--的连接数量:"+pool.getNumActive());
//            System.out.println("当前连接池中-等待使用-的连接数量:"+pool.getNumWaiters());
            System.out.println("当前连接池中-等待使用-的连接数量:"+pool.getNumIdle());
//要测试最大连接数,则将n的值设置为14(完全利用)和15(连接耗尽不够)
            int n = 14;
            if(i==n){
//                Thread.sleep(1500);
//                System.out.println("正在从数组中获取并释放第"+(i-n)+"个连接:"+redisList.get(i-n));
//                redisList.get(i-n).close();

                for (int j=0;j<redisList.size();j++){
                    redisList.get(j).close();
                }
            }
        }
    }
    @Test
    public void testJedisPoolConfig_MaxTotal2(){
//        此方法用于测试连接池的配置参数
//        参数1: config.setMaxTotal(15);
//              最大可从redis连接池中获取的连接数,如果只是获取连接,用完也不.close关掉连接则会报错:
//        redis.clients.jedis.exceptions.JedisExhaustedPoolException: Could not get a resource since the pool is exhausted
        for (int i =0; i<30;i++){
            ShardedJedis resource = pool.getResource();
            System.out.println(resource);
//            resource.close();
        }
    }

}
