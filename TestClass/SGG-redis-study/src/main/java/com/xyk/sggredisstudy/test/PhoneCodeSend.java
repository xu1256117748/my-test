package com.xyk.sggredisstudy.test;

import com.xyk.sggredisstudy.constant.Constant;
import com.xyk.sggredisstudy.utils.JedisUtil;
import com.xyk.sggredisstudy.utils.RedisKeysUtil;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 10:47
 * @Description
 *
 *      * 模拟手机验证码
 *      * 1.输入手机号,点击发送六位随机数字,2分钟有效
 *      * 2.输入验证码,点击验证,返回成功或失败
 *      * 3.每个手机号每天只能发送三次验证码
 *
 */
public class PhoneCodeSend {

    public static void main(String[] args) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        /**
         * 两种方式获取随机六位数字
         * */
        Integer num = new Random().nextInt(899999) + 100000;
        String phoneCode = getRandomPhoneCode(6);
//        System.out.println("num = "+ num + ";  code = " + phoneCode + " (当前使用)");
        /**
         * 使用何种redis的数据结构?
         * 验证码放到一个key中,一个手机号每天发送三次的限制放到另一个key中
         * */
        sendCode(jedis,"18438607196", phoneCode);
        jedis.close();
    }



    /**
     * 生成六位随机数
     * @param
     * */
    public static String getRandomPhoneCode(Integer len) throws Exception {
        if (len<0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < len ; i++){
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 发送手机验证码(此种方法会存在大量冗余key,每次不同的手机号发送验证码都会产生两个唯一的key)
     * @param jedis
     * @param phone 手机号
     * @param phoneCode 验证码
     * */
    public static void sendCode(Jedis jedis, String phone, String phoneCode){
        // 手机验证码的key
        String phoneCodeKey = RedisKeysUtil.getPhoneCodeKey(phone);
        // 手机验证码发送次数的key
        String phoneCodeCountKey = RedisKeysUtil.getPhoneCodeCountKey(phone);

        // 手机验证码发送次数
        String count = jedis.get(phoneCodeCountKey);
        if (! StringUtils.hasLength(count)){
            // 手机验证码
            jedis.setex(phoneCodeKey, 2*60, phoneCode);
            jedis.setex(phoneCodeCountKey, 24*60*60,"1");
            System.out.println("验证码:"+phoneCode+",有效期为两分钟,请在有效期内进行验证!");

        }else if (Integer.parseInt(count) <= 2){
            // 手机验证码
            jedis.setex(phoneCodeKey, 2*60, phoneCode);
            jedis.incr(phoneCodeCountKey);
            System.out.println("验证码:"+phoneCode+",有效期为两分钟,请在有效期内进行验证!");
        }else{
            System.out.println("手机号 "+phone+" 今日已达到三次验证码发送上限,请明天再试!");
        }


    }
}
