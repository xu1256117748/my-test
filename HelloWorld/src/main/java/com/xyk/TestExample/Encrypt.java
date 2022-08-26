package com.xyk.TestExample;

import com.xyk.EncryptUtils;

import java.util.Objects;

/**
 * @author 徐亚奎
 * @date 2021-10-28 12:56
 * @Description
 */
public class Encrypt {
    public static void main(String[] args) throws Exception {
        String s = "ateeeaagdgregrhshw";
        String p = "2";
        String s1 = EncryptUtils.encryptMD5(s);
        System.out.println("加密后的密码:  "+s1);
        String s10 = EncryptUtils.encryptMD5(p);
        System.out.println("加密后的密码:  "+s10);

        System.out.println(Objects.equals(s1,s10));

    }
}
