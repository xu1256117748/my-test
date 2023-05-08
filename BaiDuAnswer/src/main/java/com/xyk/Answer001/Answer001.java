package com.xyk.Answer001;

import io.jsonwebtoken.lang.Objects;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2023/5/9 3:38
 * @Description
 */
public class Answer001 {

    @Test
    public void answer(){

        // 求最小值一
        int num1=new Random().nextInt(21);
        int num2=new Random().nextInt(21);
        Integer min = min(num1,num2);
        System.out.println("最小数为:"+min);

        // 判断素数一
        int num3 = new Random().nextInt(300);
        boolean prime = isPrime(num3);
        System.out.println(num3+(prime==true?"是":"不是")+"素数");

//
//        // 求最小值二
//        // 生成随机数
//        int size=50;
//        Integer[] ints = new Integer[size];
//        for (int i=0; i<ints.length; i++){
//            ints[i]=new Random().nextInt(21);
//        }
//        System.out.println("随机数组:"+Arrays.toString(ints));
//        Integer min2 = min2(ints);
//        System.out.println("最小数为:"+min2);
//
//
//        // 判断素数二
//        int num4 = new Random().nextInt(300);
//        Boolean prime2 = (Boolean) isPrime2(num4)[0];
//        System.out.print(num4+(prime2==true?"是":"不是")+"素数");
//
//        if (!prime2){
//            Integer n = (Integer) isPrime2(num4)[1];
//            System.out.println(",可以被 "+n+" 整除,结果为 "+(num4/n));
//        }else{
//            System.out.println();
//        }
//        System.out.println("----------");


    }
    /**
     *  取最小值实现方式一
     * */
    public static int min(int num1, int num2){
        return num1>num2?num2:num1;
    }
    /**
     * 取最小值实现方式二
     * */
    public static Integer min2(Integer ...nums){
        if (nums == null || nums.length==0){
            return null;
        }
        return Arrays.stream(nums).min(Integer::compare).get();
    }

    /**
     * 判断一个数是不是素数
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个数是不是素数
     * @param num 要判断是否是素数的数字
     * @return result 返回一个Object[],
     *                 result[0] 数值类型:boolean 下标0存储这个数字是否是素数
     *                 result[1] 数值类型:int 下标1存储这个数字为什么不是素数(因为能被整个数字整除)
     */
    public static Object[] isPrime2(int num) {
        Object[] result = new Object[2];
        if (num <= 1) {
            result[0]=false;
            result[1]=num;
            return result;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                result[0]=false;
                result[1]=i;
                return result;
            }
        }
        result[0]=true;
        result[1]=1;
        return result;
    }

}
