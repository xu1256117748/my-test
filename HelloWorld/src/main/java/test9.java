import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-10 20:26
 * @Description
 */
@Slf4j
public class test9 {
    @Test
    public void test01(){
        char x  = 97;
        int y = x;
        char z = (char)(y+1);

        long s1 = 1;
        long s2 = 155555555555L;
        System.out.println(x+","+y+","+z);

    }
    @Test
    public void test02(){
        int a = 0B10101; // OB:二进制的前缀 代表的十进制数字为: 16+4+1=21

//        二进制是Binary，简写为B
//
//        八进制是Octal，简写为O
//
//        十进制为Decimal，简写为D
//
//        十六进制为Hexadecimal，简写为H


    }
}
