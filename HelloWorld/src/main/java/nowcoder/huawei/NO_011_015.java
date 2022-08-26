package nowcoder.huawei;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-25 13:31
 * @Description
 */
@Slf4j
public class NO_011_015 {
    /**
     * HJ11 数字颠倒
     * 描述
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     *
     *
     * 数据范围： 0 \le n \le 2^{30}-1 \0≤n≤2
     * 30
     *  −1
     * 输入描述：
     * 输入一个int整数
     *
     * 输出描述：
     * 将这个整数以字符串的形式逆序输出
     *
     * 示例1
     * 输入：
     * 1516000
     * 复制
     * 输出：
     * 0006151
     * 复制
     * 示例2
     * 输入：
     * 0
     * 复制
     * 输出：
     * 0
     * 复制
     * */
    @Test
    public void test_011(){
        String inputNum = new Scanner(System.in).nextLine();
        System.out.println(new StringBuilder(inputNum).reverse());


    }
    /**
     * HJ12 字符串反转
     *
     * 描述
     * 接受一个只包含小写字母的字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     *
     * 输入描述：
     * 输入一行，为一个只包含小写字母的字符串。
     *
     * 输出描述：
     * 输出该字符串反转后的字符串。
     *
     * 示例1
     * 输入：
     * abcd
     * 复制
     * 输出：
     * dcba
     * */
    @Test
    public void test_012(){

        String inputStr = new Scanner(System.in).nextLine();
        System.out.println(new StringBuilder(inputStr).reverse());

    }
    /**
     * HJ13 句子逆序
     *
     * 描述
     * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
     *
     * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
     *
     * 数据范围：输入的字符串长度满足 1 \le n \le 1000 \1≤n≤1000
     *
     * 注意本题有多组输入
     * 输入描述：
     * 输入一个英文语句，每个单词用空格隔开。保证输入只包含空格和字母。
     *
     * 输出描述：
     * 得到逆序的句子
     *
     * 示例1
     * 输入：
     * I am a boy
     * 复制
     * 输出：
     * boy a am I
     * 复制
     * 示例2
     * 输入：
     * nowcoder
     * 输出：
     * nowcoder
     * */
    @Test
    public void test_013(){
        String inputStr = new Scanner(System.in).nextLine();
        String[] split = inputStr.split(" ");
        for (int i = split.length-1; i>=0;i--){
            System.out.print(split[i]+" ");
        }

    }

    /**
     *HJ14 字符串排序
     *
     * 描述
     * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
     *
     * 数据范围： 1 \le n \le 1000 \1≤n≤1000  ，字符串长度满足 1 \le len \le 100 \1≤len≤100
     * 输入描述：
     * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     * 输出描述：
     * 数据输出n行，输出结果为按照字典序排列的字符串。
     * 示例1
     * 输入：
     * 9
     * cap
     * to
     * cat
     * card
     * two
     * too
     * up
     * boat
     * boot
     * 复制
     * 输出：
     * boat
     * boot
     * cap
     * card
     * cat
     * to
     * too
     * two
     * up
     * */
    @Test
    public void test_014(){
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i< num;i++){
            String word = scanner.nextLine();
            list.add(word);
        }
        list.sort((w1,w2)->{
            int i = 0;
            while(i<w1.length() && i<w2.length()){

                if (w1.charAt(i) > w2.charAt(i)){
                    return 1;
                }else if (w1.charAt(i) < w2.charAt(i)){
                    return -1;
                }else {
                    i++;
                }
            }
            return w1.length()-w2.length();
        });
        list.forEach(System.out::println);


        // 方法2 list的steam流,原生排序api就是这种排序,优点:常用,符合常识 缺点:不灵活,遇到特殊的还是要手写
//        Scanner scanner = new Scanner(System.in);
//        int num = Integer.parseInt(scanner.nextLine());
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i< num;i++){
//            String word = scanner.nextLine();
//            list.add(word);
//        }
//        list.stream().sorted().forEach(System.out::println);

    }
    /**
     *HJ15 求int型正整数在内存中存储时1的个数(转换成二进制,计算1的数量)
     *
     * 描述
     * 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
     *
     * 数据范围：保证在 32 位整型数字范围内
     * 输入描述：
     *  输入一个整数（int类型）
     *
     * 输出描述：
     *  这个数转换成2进制后，输出1的个数
     *
     * 示例1
     * 输入：
     * 5
     * 复制
     * 输出：
     * 2
     * 复制
     * 示例2
     * 输入：
     * 0
     * 复制
     * 输出：
     * 0
     * */
    @Test
    public void test_015(){
        Scanner scanner = new Scanner(System.in);
        Integer inputNum = scanner.nextInt();
        Integer time = 0; // 记录1的次数
        while (inputNum != 0){

            if (inputNum%2==1){
                time++;
            }
            inputNum /= 2;
        }
        System.out.println(time);



    }


}
