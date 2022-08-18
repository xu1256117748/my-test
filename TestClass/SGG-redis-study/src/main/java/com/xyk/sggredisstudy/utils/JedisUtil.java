package com.xyk.sggredisstudy.utils;

import com.xyk.sggredisstudy.constant.Constant;
import redis.clients.jedis.Jedis;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 11:36
 * @Description
 */
public class JedisUtil {
    // HOST redis服务器所在的ip地址,如虚拟机的ip地址
    public static final String HOST = "192.168.126.130";
    public static final Integer PORT = 6379;
    public static final String PASSWORD = "123456";

    /**
     * 获取jedis对象
     * */
    public static Jedis getJedis(){
        Jedis jedis = new Jedis(HOST,PORT);
        jedis.auth(PASSWORD);
        testConnectRedis(jedis);
        return jedis;
    }
    /**
     * 测试jedis连接是否成功
     * */
    public static void testConnectRedis(Jedis jedis){
        String ping = jedis.ping();
        String result = "PONG".equals(ping) ? "success !" : "fail !";
        System.out.println("connect redis " + result);
    }

}
