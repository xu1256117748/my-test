package com.xyk.sggredisstudy.utils;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 11:45
 * @Description
 * redis中的key生成器
 */
public class RedisKeysUtil {
    /**
     * 存放手机号验证码的key
     * */
    public static String getPhoneCodeKey(String phone){
        return "phoneCode:" + phone;
    }
    /**
     * 存放手机号验证码每日发送次数的key
     * */
    public static String getPhoneCodeCountKey(String phone){
        return "phoneCodeCount:" + phone;
    }

    /**
     * 秒杀商品库存的key
     * */
    public static String getMiaoShaItemKey(String itemId){
        return "MiaoShaItem:"+itemId;

    }
    /**
     * 秒杀商品成功秒杀的用户们的key
     * */
    public static String getMiaoShaItemUsersKey(String itemId){
        return getMiaoShaItemKey(itemId) +":users";
    }
}
