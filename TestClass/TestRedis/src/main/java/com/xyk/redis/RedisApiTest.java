package com.xyk.redis;

import com.xyk.config.Constant;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2021-12-22 19:40
 * @Description
 */
public class RedisApiTest {
    public Jedis jedis;

    @Before
    public void before() throws Exception {
        jedis = new Jedis(Constant.HOST , Constant.PORT);
    }
    /**
     * 测试链接redis,展示所有键值对的值
     * */
    @Test
    public void connectionTest() {
        Set<String> keys = jedis.keys("*");
        System.out.println("当前redis中的所有键值对如下:");
        System.out.println("------------------------");
        for (String key : keys){
            System.out.println(key+" ==> "+jedis.get(key));
        }
        System.out.println("------------------------");

    }
}
