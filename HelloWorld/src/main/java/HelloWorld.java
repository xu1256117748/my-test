import com.xyk.XykApi;
import com.xyk.pojo.User;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @date 2021-10-16 17:57
 * @Description
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        System.out.println("hello world!");
//        List<Character> randomList = new ArrayList<>();
//        while (randomList.size()<5){
//            char c = (char) (new Random().nextInt(26)+97);
//            if (!randomList.contains(c)){
//                randomList.add(c);
//            }
//        }
//        System.out.println(randomList);
//        System.out.println("请输入:");
//        List<Character> inList = Arrays.stream(new Scanner(System.in).nextLine().split(""))
//                                    .limit(5)
//                                    .map(s->s.charAt(0))
//                                    .collect(Collectors.toList());
//        System.out.println(inList);
//
//        // 计算猜对的个数
//        ArrayList<Character> list1 = new ArrayList<>();
//        for (int i=0;i<randomList.size();i++){
//            list1.add(randomList.get(i));
//        }
//        List<Character> list2 = new ArrayList<>(10); // 猜对的数组
//        Integer count = 0; //
//        for (Character c : inList){
//            if (list1.contains(c)){
//                list1.remove(c);
//                count++;
//            }
//        }
//        System.out.println("1.猜对的个数:"+count);
//        Integer count2 = 0;
//        for (int i=0;i<randomList.size();i++){
//            if (Objects.equals(randomList.get(i),inList.get(i))){
//                list2.add(inList.get(i));
//                count2++;
//            }else{
//                list2.add(null);
//            }
//        }
//        System.out.println("2.位置排列正确的个数:"+count2);
//
////        List<Character> collect = list2.stream().filter(s -> !Objects.isNull(s)).collect(Collectors.toList());
//        System.out.println("3.位置排列正确的:"+list2);


//        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 9, 12,6,5);
//        List<Integer> minList = new ArrayList<>();
//        Integer max = minList.get(0);
//        Integer min = minList.get(0);
//        for ( Integer i : minList){
//            if (i>max){
//                max = i;
//            }
//            if (i<min){
//                min = i;
//            }
//        }
//        Boolean flag = false;//上次插入是否成功
//        ArrayList<Integer> result = new ArrayList<>();
//        List<Integer> indexResult = new ArrayList<>();
//        for (int i =0; i<list.size()-1 ; i++){
//            Integer before = list.get(i);
//            Integer after = list.get(i+1);
//            Integer between = after-before;
//            if (between > min || between < max){
//                if (flag){
//                    result.add(after);
//                }else{
//                    result.add(before);
//                    result.add(after);
//                    flag = true;
//                }
//            }else {
//                flag =false;
//            }
//        }
//        System.out.println(result);
//        System.out.println(indexResult);

//        String s ="[d,d, r, r,d]";
//
////        for (int i =0; i<s.length();i++){
////            Character c = s.charAt(i);
////            boolean matches = c.toString().matches("[\\[, \\]]");
////            System.out.println("字符"+c+"是否符合正则:"+matches);
////        }
//        String str = ".?";
//        boolean matches = str.matches("^.+$");
//        System.out.println(matches);
////        System.out.println(s.replaceAll("[\\[, \\]]", ""));

//        int i = 0;
//        i += (3+5);
//        System.out.println(i);

//        // 模拟一个长度为10的数组
//        int[] ints = new int[10];
//        for (int i = 0; i < ints.length; i++) {
//            ints[i] = 40 + new Random().nextInt(61);
//        }
//        System.out.println("随机数组为:" + Arrays.toString(ints));
//        // 评定等级
//        char[] result = new char[ints.length];
//        for (int i = 0; i < ints.length; i++) {
//            int score = ints[i];
//            char level;
//            if (score >= 90) {
//                level = 'A';
//            } else if (80 <= score && score < 90) {
//                level = 'B';
//            } else if (70 <= score && score < 80) {
//                level = 'C';
//            } else if (60 <= score && score < 70) {
//                level = 'D';
//            } else {
//                level = 'E';
//            }
//            result[i] = level;
//        }
//        System.out.println("对应等级为:" + Arrays.toString(result));


        /**
         * 冒泡排序
         * a、冒泡排序，是通过每一次遍历获取最大/最小值
         * b、将最大值/最小值放在尾部/头部
         * c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
         * d、代码实现:
         */
        // 模拟浮点数集合
        int size = 10;
        BigDecimal[] randomArray = new BigDecimal[size];
        for (int i = 0; i < size;i++){
            //BigDecimal在运行时虽然会比基础类型略慢,但能够完全控制计算精度
            randomArray[i]= new BigDecimal(new Random().nextInt(101))
                            .divide(new BigDecimal(8), 3,RoundingMode.HALF_UP);
        }
        System.out.println("随机浮点数:"+Arrays.toString(randomArray));
        // 冒泡排序
        BigDecimal mid;
        for (int i=0;i<randomArray.length-1;i++){
            for (int j  = 0; j  < randomArray.length-i-1; j ++) {
                if (randomArray[j].compareTo(randomArray[j+1])==1){
                    mid=randomArray[j];
                    randomArray[j]=randomArray[j+1];
                    randomArray[j+1]=mid;
                }
            }
            System.out.println("第"+(i+1)+"轮排序:"+Arrays.toString(randomArray));
        }

    }
}
