package nowcoder.huawei;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.xyk.TimeOutComparent.list;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-24 11:08
 * @Description
 */
@Slf4j
public class NO_001_005 {
    /**
     * HJ1 字符串最后一个单词的长度
     *
     * 描述
     * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
     * 输入描述：
     * 输入一行，代表要计算的字符串，非空，长度小于5000。
     *
     * 输出描述：
     * 输出一个整数，表示输入字符串最后一个单词的长度。
     *
     * 示例1
     * 输入：
     * hello nowcoder
     * 复制
     * 输出：
     * 8
     * 复制
     * 说明：
     * 最后一个单词为nowcoder，长度为8
     * */
    @Test
    public void Test_001(){

//        String input = new Scanner(System.in).nextLine();
//        String input = "Hello World!";
//
//        int index = input.lastIndexOf(" ");
//        String subStr = input.substring(index+1);
//        System.out.println(subStr+"  "+subStr.length());


        System.out.println("请输入单词串:");
        String input = new Scanner(System.in).nextLine();
        System.out.println(input.length()-1-input.lastIndexOf(" "));

    }

    /**
     * HJ2 计算某字符出现次数
     *
     *描述
     * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
     *
     * 数据范围： 1 \le n \le 1000 \1≤n≤1000
     * 输入描述：
     * 第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。
     *
     * 输出描述：
     * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
     *
     * 示例1
     * 输入：
     * ABCabc
     * A
     * 复制
     * 输出：
     * 2
     * 复制
     * */
    @Test
    public void test_002(){
//        String inputStr = "ABCABCDabbccdd";
//        char inputChar = 'a';


        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine().toLowerCase();
        char inputChar = new Scanner(System.in).nextLine().toLowerCase().charAt(0);
        int num = 0;
        char[] chars = inputStr.toCharArray();
        for (Character c : chars) {
            if (c == inputChar){
                num++;
            }
        }
        System.out.println(num);


    }
    /**
     * HJ3 明明的随机数
     *
     * 描述
     * 明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
     *
     * 数据范围： 1 \le n \le 1000 \1≤n≤1000  ，输入的数字大小满足 1 \le val \le 500 \1≤val≤500
     * 输入描述：
     * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
     * 输出描述：
     * 输出多行，表示输入数据处理后的结果
     *
     * 示例1
     * 输入：
     * 3
     * 2
     * 2
     * 1
     * 复制
     * 输出：
     * 1
     * 2
     * 复制
     * 说明：
     * 输入解释：
     * 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
     * 2
     * 2
     * 1
     * 所以样例的输出为：
     * 1
     * 2
     * */
    @Test
    public void test_003(){
        Scanner scanner = new Scanner(System.in);
        int nums = scanner.nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<nums; i++){
            set.add(scanner.nextInt());
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        set.stream().forEach(System.out::println);

    }

    /**
     * HJ4 字符串分隔
     * 描述:
     *•输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
     *
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * 输入描述：
     * 连续输入字符串(每个字符串长度小于等于100)
     *
     * 输出描述：
     * 依次输出所有分割后的长度为8的新字符串
     *
     * 示例1
     * 输入：
     * abc
     * 复制
     * 输出：
     * abc00000
     * 复制
     * */

    @Test
    public void test_004(){

        String inputStr = "Hello Worldfdthfhddddddd!";
//        String inputStr = new Scanner(System.in).nextLine();

        int length = inputStr.length(); // 输入的字符串长度
        int num = length / 8 + (length%8>0?1:0); // 输出的字符串的个数
        for(int i = 1;i <= num; i++ ){ // 最终字符串的个数,每次遍历打印一次
            StringBuilder sb = new StringBuilder("");
            for (int j = 1;j<=8;j++){
                int  index = (i-1)*8+j-1; // 在原字符串中的下标
                if (index<length){
                    sb.append(inputStr.charAt(index));
                }else{
                    sb.append("0");
                }
            }
            System.out.println(sb);
        }

    }

    /**
     * HJ5 进制转换
     *
     * warning 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
     * 描述
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     *
     * 数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
     * 31
     *  −1
     * 输入描述：
     * 输入一个十六进制的数值字符串。
     *
     * 输出描述：
     * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
     *
     * 示例1
     * 输入：
     * 0xAA
     * 复制
     * 输出：
     * 170
     * 复制
     * */
    @Test
    public void test_005(){

                String inputNumStr = "100";
        int i = Integer.parseInt(inputNumStr, 16);
        System.out.println(i);


//        String inputNumStr = "100";
//        String inputNumStr = new Scanner(System.in).nextLine();
//        HashMap<String, Integer> numMap = new HashMap<>();
//        numMap.put("0", 0);
//        numMap.put("1", 1);
//        numMap.put("2", 2);
//        numMap.put("3", 3);
//        numMap.put("4", 4);
//        numMap.put("5", 5);
//        numMap.put("6", 6);
//        numMap.put("7", 7);
//        numMap.put("8", 8);
//        numMap.put("9", 9);
//        numMap.put("a", 10);
//        numMap.put("b", 11);
//        numMap.put("c", 12);
//        numMap.put("d", 13);
//        numMap.put("e", 14);
//        numMap.put("f", 15);
//        numMap.put("A", 10);
//        numMap.put("B", 11);
//        numMap.put("C", 12);
//        numMap.put("D", 13);
//        numMap.put("E", 14);
//        numMap.put("F", 15);
//        StringBuilder reverse = new StringBuilder(inputNumStr).reverse();
//        char[] chars = reverse.toString().toCharArray();
//        Integer num = 0;
//        for (int i=0;i<chars.length;i++) {
//            Integer num1 = numMap.get(String.valueOf(chars[i]));
//            Integer num2 = getFullNum(i,16);
//            num+=num1*num2;
//        }
//        System.out.println(num);


    }
    /**
     * 计算n个num相乘的结果
     * @param n 示例:计算3个5相乘,即5的3次方,n就是其中的3
     * @param num 示例:计算3个5相乘,即5的3次方,num就是其中的5
     * */
    private Integer getFullNum(Integer n,Integer num){
        if (n<=0){
            return 1;
        }else if (n==1){
            return num;
        }
        Integer result = 1;
        for (int i=0;i<n;i++){
            result*=num;
        }
        return result;

    }


}
