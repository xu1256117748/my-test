package com.xyk.Answer002;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2023/5/9 5:54
 * @Description
 */
public class Answer002 {


    public static void main(String[] args) {
        // 1.创建一个包含指定字符的集合
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            list.add((char) i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            list.add((char) i);
        }
        list.add('-');
        // 2.定义随机字符串的参数
        int strMaxLength = 10; // 生成的字符串最大长度
        int strMinLength = 3; // 生成的字符串最小长度
        int strLength = new Random().nextInt(strMaxLength-strMinLength+1)+strMinLength; // 随机字符串的随机长度[strMinLength,strMaxLength]
        //3.生成随机串
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < strLength; i++){
            int charIndex = new Random().nextInt(list.size());
            newStr.append(list.get(charIndex));
        }
        //4.输出随机串
        System.out.println(newStr);
    }

}