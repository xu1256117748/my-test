package com.xyk.sggredisstudy.test;

import com.xyk.sggredisstudy.utils.JedisUtil;
import com.xyk.sggredisstudy.utils.RedisKeysUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-02-21 11:33
 * @Description
 * 用户填入验证码后,进行验证
 */
public class PhoneCodeVerify {
    public static void main(String[] args) {

        // 模拟用户输入的手机号和验证码
        String phone = "18438607196";
        String inputCode = "174577";
        if (Strings.isEmpty(phone)){
            System.out.println("输入的手机号不能为空!");
        }
        if (Strings.isEmpty(inputCode)){
            System.out.println("输入的验证码不能为空!");
            return;
        }

        Jedis jedis = JedisUtil.getJedis();

        String phoneCodeKey = RedisKeysUtil.getPhoneCodeKey(phone);
        String phoneCode = jedis.get(phoneCodeKey);
        if (Strings.isEmpty(phoneCode)){
            System.out.println("验证码已过期!");
        }else {
            String msg = inputCode.equals(phoneCode) ? "验证成功!" : "验证失败!验证码错误!";
            System.out.println(msg);
        }
        jedis.close();

    }
}
