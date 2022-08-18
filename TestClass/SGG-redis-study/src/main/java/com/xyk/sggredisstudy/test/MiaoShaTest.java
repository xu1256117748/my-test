package com.xyk.sggredisstudy.test;

import com.xyk.sggredisstudy.utils.JedisPoolUtil;
import com.xyk.sggredisstudy.utils.JedisUtil;
import com.xyk.sggredisstudy.utils.RedisKeysUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 14:54
 * @Description
 *  秒杀案例,未进行多线程,
 *  P1:高并发下连接超时的问题使用redisPool解决
 *  P2:超卖的问题使用监听事务锁的问题解决
 *  P3:乐观锁造成的库存遗留问题-lua脚本
 *  详情参见word文档
 */
public class MiaoShaTest {
    public static void main(String[] args) {

        String itemId = "fcd88";
        for(int i = 0; i<100 ; i++){ // 100个用户同时抢
//            Jedis jedis = JedisUtil.getJedis();
            // 通过连接池获取jedis对象
            Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();

            String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            String miaoShaItemKey = RedisKeysUtil.getMiaoShaItemKey(itemId);
            // 开启监听和事务(悲观锁)
            jedis.watch(miaoShaItemKey);

            String num = jedis.get(miaoShaItemKey);
            if (Strings.isEmpty(num)){
                System.out.println("商品 "+itemId+" 的秒杀活动尚未开始!");
                jedis.close();
                return;
            }

            if ( Integer.valueOf(num) <= 0 ){
                System.out.println("商品 "+itemId+" 的秒杀活动已经结束了!");
                jedis.close();
                return;
            }
            // 判断同一用户重复秒杀
            if (jedis.sismember(RedisKeysUtil.getMiaoShaItemUsersKey(itemId), userId)){
                System.out.println("用户 "+ userId+" 已经秒杀成功了,不能重复参与!");
                jedis.close();
                return;
            }
            // 开启事务
            Transaction multi = jedis.multi();
            multi.decr(miaoShaItemKey);
            multi.sadd(RedisKeysUtil.getMiaoShaItemUsersKey(itemId), userId);
            System.out.println("用户 "+userId+" 秒杀成功!");
            multi.exec();
            }
    }
}
