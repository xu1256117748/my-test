package com.xyk;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author 徐亚奎
 * @date 2021-10-28 12:58
 * @Description 加密算法比较
 */
public class EncryptUtils {
    private final static String CHARSET_NAME="UTF-8";
    /***
     * 1.BASE64加密
     * (支持中文,可双向操作,因此安全性不高)
     *      Base64 编码是我们程序开发中经常使用到的编码方法，它用 64 个可打印字符来表示二进制数据。
     * 这 64 个字符是：小写字母 a-z、大写字母 A-Z、数字 0-9、符号"+"、"/"（再加上作为垫字的"="，实际上是 65 个字符），
     * 其他所有符号都转换成这个字符集中的字符。Base64 编码通常用作存储、传输一些二进制数据编码方法，
     * 所以说它本质上是一种将二进制数据转成文本数据的方案。
     * @param password 原密码
     * @return BASE64加密后的密码(可解密还原, 安全性不高)
     * @throws Exception
     */
    public static String encryptBASE64(String password) throws Exception {
        return (new BASE64Encoder()).encode(password.getBytes(CHARSET_NAME));
    }
    /**
     * 1.BASE64解密(支持中文,可双向操作,因此安全性不高)
     * @param password 经过[BASE64加密]后的密码
     * @return 未经加密的原密码
     * @throws Exception
     * */
    public static String decryBASE64(String password) throws Exception {
        return new String(new BASE64Decoder().decodeBuffer(password),CHARSET_NAME);
    }

    /**
     * 2.MD5(Message Digest Algorithm)加密
     * (是否支持中文微测试,是一种单向加密算法，只能加密不能解密,可配合加盐实现用户密码的校验)
     *      MD5 是将任意长度的数据字符串转化成短小的固定长度的值的单向操作，任意两个字符串不应有相同的散列值。
     * 因此 MD5 经常用于校验字符串或者文件，因为如果文件的 MD5 不一样，说明文件内容也是不一样的，
     * 如果发现下载的文件和给定的 MD5 值不一样，就要慎重使用。
     *      MD5 主要用做数据一致性验证、数字签名和安全访问认证，而不是用作加密。
     * 比如说用户在某个网站注册账户时，输入的密码一般经过 MD5 编码，更安全的做法还会加一层盐（salt），
     * 这样密码就具有不可逆性。然后把编码后的密码存入数据库，下次登录的时候把密码 MD5 编码，
     * 然后和数据库中的作对比，这样就提升了用户账户的安全性。
     * @param password 原密码
     * @return MD5加密后的密码(可解密还原, 安全性不高)
     * @throws Exception
     */
    public static String encryptMD5(String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes(CHARSET_NAME));
        return new String(md5.digest(),CHARSET_NAME);
    }

}