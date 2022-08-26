package com.xyk.TestExample;

import com.xyk.pojo.User;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-10-15 13:27
 * @Description
 */
public class ExampleTest_05 {
    public static void main(String[] args) {
//        System.out.println("");
//        User user = new User().setId(1);
//        Map<String, Object> map = new HashMap<>();
//        System.out.println(User.class.getDeclaredFields().length);
//        Field[] fields = User.class.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            field.setAccessible(true);
//            System.out.println(field.getName());
//            try {
//                System.out.println(field.get(user));
//                map.put(field.getName(),field.get(user));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        Arrays.stream(User.class.getDeclaredFields()).map(s->{
//            s.setAccessible(true);
//            System.out.println(s.getName());
//            try {
//                System.out.println(s.get(user));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            try {
//                map.put(s.getName(),s.get(user));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            return map;
//        });

//        System.out.println(map);
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        List<Integer> i2 = Arrays.asList();
//        double num1 = 2.15;
//        double num2 = 1.10;
//        System.out.println(num1-num2);
//        System.out.println(BigDecimal.valueOf(num1).subtract(BigDecimal.valueOf(num2)));
//        System.out.println(new BigDecimal("2.15").subtract(new BigDecimal("1.10")));
//        System.out.println(new BigDecimal(2.15).subtract(new BigDecimal(1.10)));

        int time = 1000000000;
        long l1 = System.currentTimeMillis();
        for (int i = 0 ; i < time ; i++ ){
            mathyunsuan();
        }
        long l2 = System.currentTimeMillis();
        System.out.println("算术运算耗时:"+(l2-l1)+"毫秒");

        long l3 = System.currentTimeMillis();
        for (int i = 0 ; i < time ; i++ ){
            weiyunsuan();
        }
        long l4 = System.currentTimeMillis();
        System.out.println("位运算耗时:"+(l4-l3)+"毫秒");
    }
    public static void weiyunsuan(){
        double a = 10+(10>>1);
        a=a+1;
    }
    public static void mathyunsuan(){
        double a = 10*1.5;
        a=a+1;
    }

}
