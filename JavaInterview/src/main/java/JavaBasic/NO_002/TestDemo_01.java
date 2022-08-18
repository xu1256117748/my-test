package JavaBasic.NO_002;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-10 21:17
 * @Description
 */
@Slf4j
public class TestDemo_01 {
    @Test
    public void test01(){
//        各种常见进制的前缀介绍,数字都以101进行计算

//        二进制(Binary):前缀为0B
        int a = 0B101; // OB:二进制的前缀 代表的十进制数字为: 1*2*2+1=5
        System.out.println("二进制数字"+"0B101"+",代表的十进制数值为: a="+a);

//        八进制(Octal):前缀为数字0,这里要注意,正常十进制的数字前面如果加个数字0,标志着这其实是一个八进制的数字
        int b = 0101; //0,八进制的前缀,代表的十进制数字为:1*8*8+1=65
        System.out.println("八进制数字"+"0101"+",代表的十进制数值为: b="+b);

//        十进制(Decimal):无前缀无后缀
        int c = 101; // 代表的十进制数字为:101
        System.out.println("十进制数字"+"101"+",代表的十进制数值为: c="+c);

//        十六进制(Hexadecimal):前缀为数字0
        int d = 0x101; //ox:十六进制的前缀,代表的十进制数字为:1*16*16+1=257
        System.out.println("十六进制数字"+"0x101"+",代表的十进制数值为: d="+d);



    }

//    类型转换
    @Test
    public void test02(){
        int e1 = -2147483648;
//        int e2 = -2147483649; 超出取值范围
        int f1 = 2147483647;
//        int f2 = 2147483648; 超出取值范围

//      小到大,隐性转,自动转
        byte a = 127;
        short b = a;
//      大到小,显性转,强制转
        byte c = 127;
        int d =127;
        c = (byte) d;
//      大到小,显性转,强制转的例外情况
        byte e = 127; // 这个公式里,右边的127类型为int,左边的为byte,但是因为右边的是固定值而不是变量,并且在byte的取值范围内,因此不需要强制转

        // 强制转换可能造成数值溢出的情况(数值溢出时,可以理解为环形数据存储,继续往下走)
        byte h = 127;
        byte h1 = (byte)128;
        byte h2 = (byte)129;
        System.out.println("h="+h);
        System.out.println("h1="+h1);
        System.out.println("h2="+h2);

    }
}
