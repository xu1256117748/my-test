package com.xyk;

import com.xyk.common.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 徐亚奎
 * @date 2021-09-05 20:46
 * @Description 主实现类
 */
public class TimeCounter {
    /**
     * 经过一个时间(本地时间)和用户输入的时差的计算,计算得出,需要输出的最终时间
     * */
    public  static  Map<String, Integer> destinationTime =new HashMap<>();

    /**
     * 控制计算的流程:
     * 1.获取本地时间,并格式化为字符串
     * 2.获取用户输入的时差
     * 3.将本地时间字符串截取成一个map集合
     * 4.将用户输入的时差截取并对map集合中的数据计算
     * 5.校验并格式化map集合中的数据格式(秒不能超过60s否则进1,分钟不能超过60m否则进1)
     * 6.拼接map集合中的数据
     * 7.输出结果
     * */
    public static Boolean  main() {
        // 1.获取本地时间,并格式化为字符串
        Date nowTime = getNowTime();
        String stringNow = timeFormat(nowTime);
        // 1.1 保存计算之前的本地时间用以输入,便于用于做两个时间的对比和验证
        String now = stringNow;
        // 2.获取用户输入的时差
        System.out.println("---请输入要计算的时间---");
        System.out.println("(示例:YMDhms 表示:10h12m  计算10小时12分钟后的时间)");
        String input = new Scanner(System.in).nextLine();
        System.out.println(input);
        if (input.isEmpty())  {
            return false;
        }
        // 3.将本地时间字符串截取成一个map集合
        stringTimeToMap(stringNow);
        // 4.将用户输入的时差截取并对map集合中的数据计算
        timeCounter(input);
        // 5.校验并格式化map集合中的数据格式(秒不能超过60s否则进1,分钟不能超过60m否则进1)
        checkDateTime();
        // 6.拼接map集合中的数据
        String future = dateAppend();
        // 7.输出结果
        System.out.println("当前时间:"+now);
        System.out.println("预计时间:"+future);
        return true;
    }

    public static void timeCounter(String input) {
        if (input.contains("Y")){
            int y = input.indexOf("Y");
            destinationTime.put("Y",destinationTime.get("Y")+Integer.valueOf(input.substring(0,y)));
            input = input.substring(y+1);
//            System.out.println(in);
        }
        if (input.contains("M")){
            int m = input.indexOf("M");
            destinationTime.put("M",destinationTime.get("M")+Integer.valueOf(input.substring(0,m)));
            input = input.substring(m+1);
//            System.out.println(in);
        }
        if (input.contains("D")){
            int d = input.indexOf("D");
            destinationTime.put("D",destinationTime.get("D")+Integer.valueOf(input.substring(0,d)));
            input = input.substring(d+1);
//            System.out.println(in);
        }
        if (input.contains("h")){
            int h = input.indexOf("h");
            destinationTime.put("h",destinationTime.get("h")+Integer.valueOf(input.substring(0,h)));
            input = input.substring(h+1);
//            System.out.println(in);
        }
        if (input.contains("m")){
            int m = input.indexOf("m");
            destinationTime.put("m",destinationTime.get("m")+Integer.valueOf(input.substring(0,m)));
            input = input.substring(m+1);
//            System.out.println(in);
        }
        if (input.contains("s")){
            int s = input.indexOf("s");
            destinationTime.put("s",destinationTime.get("s")+Integer.valueOf(input.substring(0,s)));
            input = input.substring(s+1);
//            System.out.println(in);
        }

    }

    public static String dateAppend(){
        StringBuilder str = new StringBuilder("");
        Integer Y = destinationTime.get("Y");
        Integer M = destinationTime.get("M");
        Integer D = destinationTime.get("D");
        Integer h = destinationTime.get("h");
        Integer m = destinationTime.get("m");
        Integer s = destinationTime.get("s");
        str.append(Y).append("-");
        if (M<10){
            str.append("0").append(M).append("-");
        }else{
            str.append(M).append("-");
        }
        if (D<10){
            str.append("0").append(D).append(" ");
        }else{
            str.append(D).append(" ");
        }
        if (h<10){
            str.append("0").append(h).append(":");
        }else{
            str.append(h).append(":");
        }
        if (m<10){
            str.append("0").append(m).append(":");
        }else{
            str.append(m).append(":");
        }
        if (s<10){
            str.append("0").append(s);
        }else{
            str.append(s);
        }
        return str.toString();

    }

    public static void checkDateTime(){
        Integer s = destinationTime.get("s");
        Integer m = destinationTime.get("m");
        Integer h = destinationTime.get("h");
        Integer D = destinationTime.get("D");
//        Integer M = null;
//        Integer Y = null;
        if (s>=60){
            destinationTime.put("s",s%60);
            destinationTime.put("m", m+s/60);
            m= destinationTime.get("m");
        }
        if (m>=60){
            destinationTime.put("m",m%60);
            destinationTime.put("h", h+m/60);
            h= destinationTime.get("h");
        }
        if (h>=24){
            destinationTime.put("h",h%24);
            destinationTime.put("D", D+h/24);
            D= destinationTime.get("D");
        }

    }


    public static void stringTimeToMap(String now){
        if (!destinationTime.isEmpty()){
            destinationTime.clear();
        }
        destinationTime.put("Y", Integer.valueOf(getYear(now)));
        destinationTime.put("M", Integer.valueOf(getMonth(now)));
        destinationTime.put("D", Integer.valueOf(getDate(now)));
        destinationTime.put("h", Integer.valueOf(getHour(now)));
        destinationTime.put("m", Integer.valueOf(getMinute(now)));
        destinationTime.put("s", Integer.valueOf(getSecond(now)));

    }

    public static Date getNowTime() {
        Date date = new Date();
//        System.out.println("date:"+date);
        return date;
    }

    public static String timeFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.TIME_FORMAT);
        String format = simpleDateFormat.format(date);
        System.out.println("当前时间:"+format);
        return format;
    }

    public static String getYear(String date) {
        String year = date.substring(0,4);
//        System.out.println("year:"+year);
        return year;
    }
    public static String getMonth(String date) {
        String month = date.substring(5,7);
//        System.out.println("month:"+month);
        return month;
    }
    public static String getDate(String date) {
        String d = date.substring(8,10);
//        System.out.println("date:"+d);
        return d;
    }
    public static String getHour(String date) {
        String hour = date.substring(11,13);
//        System.out.println("hour:"+hour);
        return hour;
    }
    public static String getMinute(String date) {
        String minute = date.substring(14,16);
//        System.out.println("minute:"+minute);
        return minute;
    }
    public static String getSecond(String date) {
        String second = date.substring(17,19);
//        System.out.println("second:"+second);
        return second;
    }

}
