package com.xyk.sggredisstudy.test;

import com.sun.org.apache.regexp.internal.RE;
import com.xyk.sggredisstudy.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-19 22:01
 * @Description
 * 此类用于不启动springBoot项目测试jedis的相关api
 */
@Service
@Slf4j
public class JedisAPITest {
    public Jedis jedis;
    @Before
    public void before(){
        jedis = new Jedis(Constant.HOST, Constant.PORT);
        jedis.auth(Constant.PASSWORD);
    }

    // 测试链接jedis是否成功
    @Test
    public void testConnectRedis(){
        String ping = jedis.ping();
        String result = "PONG".equals(ping) ? "success !" : "fail !";
        System.out.println("connect redis " + result);
    }

    /**
     * 获取所有的key,及其类型,以及对String类型和list类型的基础操作
     * */
    @Test
    public void testGetAllKeys(){
        jedis.flushDB();
        jedis.mset("name1", "xyk", "name2", "ldd");
        jedis.lpush("names","xyk","ldd");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        HashMap<String, String> keyTypeMap = new HashMap<>();
        for (String key : keys){
            String type = jedis.type(key);
            keyTypeMap.put(key, type);
        }
        System.out.println(keyTypeMap);

        List<String> names = jedis.lrange("names", 0, -1);
        System.out.println("names:"+names);

        System.out.println("name1:"+jedis.get("name1"));
        System.out.println("name2:"+jedis.get("name2"));
    }


    @Test
    public void phoneCode() throws Exception {


        //

    }







}
