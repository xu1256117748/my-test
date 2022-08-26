package nowcoder.huawei;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-25 13:31
 * @Description
 */
@Slf4j
public class NO_006_010 {
    /**
     * HJ6 质数因子HJ6 质数因子
     *
     * 描述
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
     *
     *
     * 数据范围： 1 \le n \le 2 \times 10^{9} + 14 \1≤n≤2×10
     * 9
     *  +14
     * 输入描述：
     * 输入一个整数
     *
     * 输出描述：
     * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
     *
     * 示例1
     * 输入：
     * 180
     * 复制
     * 输出：
     * 2 2 3 3 5
     * */
    @Test
    public void test_006(){

//        Long inputNum = 125L;
        Long inputNum = new Scanner(System.in).nextLong();

        StringBuilder sb = new StringBuilder("");

//        Boolean isContinue = true;
        long sqrt = (long) Math.sqrt(inputNum); // 一个合数的质因数,最大不会超过他的平方根,否则就是质数,他的质因数就只有1和他自己

        for(int i = 2;i <= sqrt; i++){ // 遍历他的质因子的可能性
            while (inputNum%i==0){
                sb.append(i+" ");
                inputNum/=i;
            }
        }
        if (inputNum!=1){
            sb.append(inputNum);
        }
        System.out.println(sb);


    }
    /**
     * HJ7 取近似值
     *
     * 描述
     * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
     *
     * 数据范围：保证输入的数字在 32 位浮点数范围内
     * 输入描述：
     * 输入一个正浮点数值
     *
     * 输出描述：
     * 输出该数值的近似整数值
     *
     * 示例1
     * 输入：
     * 5.5
     * 复制
     * 输出：
     * 6
     * 复制
     * 说明：
     * 0.5>=0.5，所以5.5需要向上取整为6
     * 示例2
     * 输入：
     * 2.499
     * 复制
     * 输出：
     * 2
     * 复制
     * 说明：
     * 0.499<0.5，2.499向下取整为2
     * */
    @Test
    public void test_007(){
//        BigDecimal bigDecimal = new BigDecimal("14685.5656464");
        System.out.println("请输入需要四舍五入数字:");
        BigDecimal bigDecimal = new Scanner(System.in).nextBigDecimal();

        String result = bigDecimal.setScale(0, RoundingMode.HALF_UP).toString();
        System.out.println(result);


        // 牛客网其他人答案:
//        Scanner in = new Scanner(System.in);
//        double number = in.nextDouble();
//        System.out.println((int)(number + 0.5));

    }
    /**
     * HJ8 合并表记录
     *
     * 描述
     * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
     *
     *
     * 提示:
     * 0 <= index <= 11111111
     * 1 <= value <= 100000
     *
     * 输入描述：
     * 先输入键值对的个数n（1 <= n <= 500）
     * 接下来n行每行输入成对的index和value值，以空格隔开
     *
     * 输出描述：
     * 输出合并后的键值对（多行）
     *
     * 示例1
     * 输入：
     * 4
     * 0 1
     * 0 2
     * 1 2
     * 3 4
     * 复制
     * 输出：
     * 0 3
     * 1 2
     * 3 4
     * 复制
     * 示例2
     * 输入：
     * 3
     * 0 1
     * 0 2
     * 8 9
     * 复制
     * 输出：
     * 0 3
     * 8 9
     * */
    @Test
    public void test_008(){

        Scanner scanner =new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine()); // 解析第一行,一共有多少条表数据
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < rows; i++){
            String row = scanner.nextLine();
            String[] split = row.split(" ");
            Integer key = Integer.parseInt(split[0]);
            Integer value = Integer.parseInt(split[split.length-1]);
            if (!treeMap.containsKey(key)){
                treeMap.put(key, value);
            }else {
                treeMap.put(key,treeMap.get(key)+value);
            }
        }
        Set<Integer> keyList = treeMap.keySet();
        for (Integer key : keyList){
            System.out.println(key+" "+treeMap.get(key));
        }

    }

    /**
     *HJ9 提取不重复的整数
     *
     * 描述
     * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
     * 保证输入的整数最后一位不是 0 。
     *
     * 数据范围： 1 \le n \le 10^{8} \1≤n≤10
     * 8
     *
     * 输入描述：
     * 输入一个int型整数
     *
     * 输出描述：
     * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
     *
     * 示例1
     * 输入：
     * 9876673
     * 复制
     * 输出：
     * 37689
     * 复制
     * */
    @Test
    public void test_009(){
        String inputNum = new Scanner(System.in).nextLine();
        List<Character> list = new ArrayList<>();
        for (int i = inputNum.length()-1; i>=0; i--){
            Character c = inputNum.charAt(i);
            if (!list.contains(c)){
                list.add(c);
            }
        }
        Iterator<Character> iterator = list.iterator();
        while(iterator.hasNext()){
            Character character = iterator.next();
            System.out.print(character);
        }
        // 牛客网思路:不使用list,使用向hashset添加元素判断是否重复


    }
    /**
     *HJ10 字符个数统计(不计算重复的个数)
     *
     * 描述
     * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
     * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
     *
     * 数据范围： 1 \le n \le 500 \1≤n≤500
     * 输入描述：
     * 输入一行没有空格的字符串。
     *
     * 输出描述：
     * 输出 输入字符串 中范围在(0~127，包括0和127)字符的种数。
     *
     * 示例1
     * 输入：
     * abc
     * 复制
     * 输出：
     * 3
     * 复制
     * 示例2
     * 输入：
     * aaa
     * 复制
     * 输出：
     * 1
     * 复制
     * 相似企业真题
     * */
    @Test
    public void test_010(){
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        if (inputStr.length()==0){
            System.out.println(0);
            return ;
        }
        HashSet<Character> set = new HashSet<>();
        for (char c : inputStr.toCharArray()){
            byte b = (byte) c;
            if (b>=0 && b<=127){
                set.add(c);
            }
        }
        System.out.println(set.size());

    }


}
