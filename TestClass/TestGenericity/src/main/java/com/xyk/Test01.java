package com.xyk;

import com.xyk.common.Cat;
import com.xyk.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author 徐亚奎
 * @date 2021-09-06 15:54
 * @Description
 * Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，
 * 该机制允许程序员在编译时检测到非法的类型。泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。
 * Java的泛型是伪泛型，这是因为Java在编译期间，所有的泛型信息都会被擦掉，这也就是通常所说类型擦除(语法糖)。
 */
@Slf4j
public class Test01 {
    @Test
    public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        // 在编译器就会报错
        // ids.add("p");
        // 但是可以通过反射的方式强行把一个p字符存入到ids集合中
        Class<? extends ArrayList> idsClass = ids.getClass();
        Method add = idsClass.getDeclaredMethod("add", Object.class);
        add.invoke(ids, "p");
        // 打印校验现在ids里的数据
        log.debug(ids.toString());
    }
    /**
     * 泛型一般有三种使用方式:泛型类、泛型接口、泛型方法。
     * */
    /**
     * 1.泛型类Cat类：
     * */
    @Test
    public void test02(){
        //在实例化泛型类时，必须指定T的具体类型
        Cat<Object> cat = new Cat<>();
        cat.setId(1).setName("cat").setAge(5);
        cat.setId("2").setName("cat").setAge(5);
        log.debug(cat.toString());

        //

    }
    @Test
    public void test03(){
        Integer[] intArray = {1,3,5,7,9};
        String[] strArray = {"h","e","l","l","o"};
        Cat.printArray(intArray);
        Cat.printArray(strArray);

    }

}
