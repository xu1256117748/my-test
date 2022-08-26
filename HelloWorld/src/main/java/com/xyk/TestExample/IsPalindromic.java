package com.xyk.TestExample;

import java.util.ArrayList;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-26 11:15
 * @Description
 */
public class IsPalindromic {
    public static void main(String[] args) {

        ArrayList<String> strs = new ArrayList<>();
        String s="shnkfnkisikn";
        String target;
        Integer length=0;
//        获取所有可能为回文串的字符串
        for (int i=0;i<s.length()-1;i++){
            for (int j=i+1;j<s.length();j++){
//                System.out.println(s.substring(i,j+1));
                strs.add(s.substring(i,j+1));
//                如果是回文数,则计算长度,并确认替换目标
                if(isTrue(s.substring(i,j+1))){

                };
            }

        }
////        将所有回文串存入到map集合中
//        HashMap<String, Integer> map = new HashMap<>();
//        for (int i=0;i<strs.size();i++){
//            System.out.println(strs.get(i));
//
//
//
//
//        }

    }
    //  此方法用于判断一个字符串是否为回文字符串,并返回true/false
    public static Boolean isTrue(String target){
        Boolean flag=false;
        if (target.length()<=1) {
            return flag;
        }

        for (int i=0;i<target.length()/2;i++){
            if (target.charAt(i)==target.charAt(target.length()-i-1)){
                if ((i+1)>=target.length()/2){
                    System.out.println(target+"是回文串");
                    flag=true;

                }
            }else {
                System.out.println(target+"不是回文串");
                flag=false;
            }
        }
        return flag;

    }
}
