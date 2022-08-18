package com.xyk.sggredisstudy.test;

import com.xyk.sggredisstudy.utils.JedisUtil;
import com.xyk.sggredisstudy.utils.RedisKeysUtil;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 14:54
 * @Description
 * 创建秒杀商品
 */
public class MiaoShaCreate {

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        String itemId = UUID.randomUUID().toString().replace("-","").substring(0, 5);
        Integer itemNum = 10;
        String miaoShaItemKey = RedisKeysUtil.getMiaoShaItemKey(itemId);
        String miaoShaItemUsersKey = RedisKeysUtil.getMiaoShaItemUsersKey(itemId);
        jedis.set(miaoShaItemKey, itemNum.toString());
        jedis.sadd(miaoShaItemUsersKey, "测试");
        jedis.spop(miaoShaItemUsersKey);
        System.out.println("秒杀商品: "+itemId+" 成功创建,剩余数量为:"+itemNum);
        System.out.println("该商品在redis中的库存的key为: "+miaoShaItemKey);
        System.out.println("该商品在redis中的秒杀成功的用户的key为: "+ miaoShaItemUsersKey);
    }
}
