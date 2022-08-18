package com.xyk.config;

import com.xyk.config.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.LinkedList;

/**
 * @author 徐亚奎
 * @date 13/07/2021 15:12
 */
@Configuration
//@ComponentScan("com.xyk.windos")
public class RedisConfig {

    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(Constant.HOST,Constant.PORT);
        jedis.auth(Constant.PASSWORD);
        return jedis;
    }
    @Bean
    public ShardedJedis shardedJedis(){
//      配置redis的连接信息(集群模式/也可当单个使用)
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
        ShardedJedisPool pool = new ShardedJedisPool(config, jedisShardInfos);
        ShardedJedis redis = pool.getResource();
//        redis.set("members", "杨建超");
//        System.out.println(redis.get("members"));
////        将redis连接归还到连接池
//        redis.close();
        return redis;
    }

    }


