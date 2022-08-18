import com.xyk.XykApi;
import com.xyk.pojo.User;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

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

    }




}
