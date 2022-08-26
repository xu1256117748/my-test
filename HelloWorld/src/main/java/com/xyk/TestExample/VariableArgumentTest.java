package com.xyk.TestExample;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-06-29 17:45
 * @Description 测试可变参数
 *
 * 在 Java 5 中提供了变长参数，允许在调用方法时传入不定长度的参数。变长参数是 Java 的一个语法糖，本质上还是基于数组的实现：
 * void foo(String... args);
 * void foo(String[] args);
 *
 * 定义方法
 * 在定义方法时，在最后一个形参后加上三点 …，就表示该形参可以接受多个参数值，**多个参数值被当成数组传入**。上述定义有几个要点需要注意：
 * 1.可变参数只能作为函数的**最后一个参数**，但其前面可以有也可以没有任何其他参数
 *
 * 2.由于可变参数必须是最后一个参数，所以一个函数最多只能有一个可变参数
 *
 * 3.Java的可变参数，会被编译器转型为一个数组
 *
 * 4.变长参数在编译为字节码后，在方法签名中就是以数组形态出现的。这两个方法的签名是一致的，不能作为方法的重载。如果同时出现，是不能编译通过的。可变参数可以兼容数组，反之则不成立
 */
@Slf4j
public class VariableArgumentTest {

    @Test
    /**
     * 可变参数,可以视作为对应类型的数组,如 String...members为String [],
     * 优点:
     *      在引用可变参数的方法时,上可以直接以可变参数入参,也可以直接以数组入参,较为灵活
     * */
    static String storageElement(String...members){
        // 直接打印该数组时,打印结果是一个地址

        System.out.println("将元素转换为数组,传入可变参数函数后,打印传入的参数:");
        System.out.println(members);
        System.out.println("表明传进来的元素或者数组,传进后都是转变成了数组保存");
        // 常规的将数组转化为集合
        List<String> list = Arrays.asList(members);
        System.out.println("数组转换为集合后打印:"+list);
        return list.toString();
    }

    @Test
    static String storageElement(Integer...members){
        // 直接打印该数组时,打印结果是一个地址
        System.out.println(members);
        // 常规的将数组转化为集合
        List<Integer> list = Arrays.asList(members);
        System.out.println(list);
        return list.toString();
    }

//    @Test
//    static String getArrayString(String[] members){
//
//        System.out.println(Arrays.asList(members));
//        return null;
//    }

    public static void main(String[] args) {
        System.out.println("请输入三个(任意个数)姓名,以空格隔开");
        String str = new Scanner(System.in).nextLine();
        System.out.println("用户输入的内容为:"+str);

        String[] names = str.split(" ");
        String es = storageElement(names);

//        storageElement("张三","李四","王五");
    }

}
