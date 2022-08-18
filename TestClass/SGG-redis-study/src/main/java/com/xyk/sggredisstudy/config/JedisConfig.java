package com.xyk.sggredisstudy.config;

import com.xyk.sggredisstudy.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-19 20:56
 * @Description
 * jedis配置类
 *
 * 在 JavaWeb 中实现对 Redis 的操作，主要有两种方式：Jedis和RedisTemplate。
 * Jedis是Redis官方推荐的面向Java操作Redis的客户端开发Jar包；
 * 而RedisTemplate是Spring框架对Jedis API的进行了高度封装，支持连接池自动管理，
 * 我们可以在Spring应用中通过简单的连接池配置信息就能访问Redis服务并进行相关缓存操作。
 * 为了多线程安全，以前是Jedis+JedisPool组合 ,现在在SpringBoot 2.0应用中直接使用Lettuce客户端的API封装RedisTemplate即可，
 * 只要配置好连接池属性，那么SpringBoot就能自动管理连接池。
 */
@Configuration
public class JedisConfig {
    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(Constant.HOST,Constant.PORT);
        jedis.auth(Constant.PASSWORD);
        return jedis;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis(Constant.HOST, Constant.PORT);
        jedis.auth(Constant.PASSWORD);
        // 如果连接成功,则ping之后会返回一个值 pong
        String ping = jedis.ping();
        System.out.println(ping);
    }
}
