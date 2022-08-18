package com.xyk.sggredisstudy.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 16:41
 * @Description
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    // HOST redis服务器所在的ip地址,如虚拟机的ip地址
    public static final String HOST = "192.168.126.130";
    public static final Integer PORT = 6379;
    public static final String PASSWORD = "123456";

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if (null == jedisPool){

                    // redis连接池的配置
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(200);
                    jedisPoolConfig.setMaxIdle(32);//如果不自己手动配置,则默认值为8
                    jedisPoolConfig.setMaxWaitMillis(100*1000);
                    jedisPoolConfig.setBlockWhenExhausted(true);
                    jedisPoolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(jedisPoolConfig,HOST,PORT,60000,PASSWORD);
                }
            }
        }
        return jedisPool ;
    }

    /**
     * 释放redis连接
     * */
    public static void release(Jedis jedis){
        if (null != jedis){
            jedis.close();
        }
    }
}
