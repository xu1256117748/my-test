package com.xyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author 徐亚奎
 * @date 13/07/2021 15:14
 */
@RestController
@CrossOrigin
public class RedisController {
    @Autowired
    private Jedis jedis;
    @Autowired
    private ShardedJedis shardedJedis;

    @GetMapping("/hello")
    public String test(){
        System.out.println(jedis.keys("*"));
        return jedis.keys("*").toString();
    }
    @GetMapping("/hello2")
        public String test2(){
        return shardedJedis.get("members");
        }

}
