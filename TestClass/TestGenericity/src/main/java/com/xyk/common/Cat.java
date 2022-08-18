package com.xyk.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author 徐亚奎
 * @date 2021-09-06 16:07
 * @Description
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
/**
 * 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * */
public class Cat<V> {
    private V id;
    private String name;
    private Integer age;

    /**
     * 方法的参数如果需要泛型,那么需要在返回值类型前也加上泛型
     * 常用的通配符为： T，E，K，V，？
     * ？ 表示不确定的 java 类型
     * T (type) 表示具体的一个java类型
     * K V (key value) 分别代表java键值中的Key Value
     * E (element) 代表Element
     *
     * T E K V 虽然都可以,但是最好按照规范来,以起到见名知意的效果
     * */
    public static <E> void printArray(E[] array ){
        for(E t:array){
            System.out.print(t+" ");
        }
        System.out.println();
    }
}
