
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author 徐亚奎
 * @date 22/07/2021 11:06
 */
public class test4 {
    public static void main(String[] args) throws IllegalAccessException {
//        通过暴力反射改变string的值
        String s="hello world";
        String s1="hello world";
        Field[] declaredFields = s.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        Field value = declaredFields[0];
        System.out.println(value);
        value.setAccessible(true);
        char[]  a = (char[]) value.get(s);
        System.out.println(a);
        a[2]='D';
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s==s1);
    }
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
/*  我们都知道String是java中一个不可变类，因为String内部是一个final修饰的char数组：
    private final char value[];
    由于value是私有的final变量，String中也没有提供get和set方法，使得value无法改变。
    但是value是一个引用，就像c++中的指针一样，指向一个数组内存的地址，被final修饰无法改变他的引用指向，
    但是我们可以直接改变内存数组中的数据，value是私有的，获取就需要用到反射：
* */
        HashSet<String> hashSet = new HashSet<>();
//      暴力反射改变String的值
//      因为在java中,String类无法直接打印地址值,因此此处通过定义s1和s2用以比较暴力反射的效果
        String s1="hello";
        String s2="hello";
        String s3="hAllo";
//        hashSet.add(s1);
//        hashSet.add(s2);
        hashSet.add("hello");
        hashSet.add(s3);
        System.out.println("hashSet:"+hashSet);
        System.out.println(s1==s2);//true
        //获取s1的私有属性--char数组value
        Field value = s1.getClass().getDeclaredField("value");
        System.out.println("value数组:"+value);
        //操作私有属性需要设置可见
        value.setAccessible(true);
//        获取s1的value的地址值,并交给target保存
        char[] target= (char[]) value.get(s1);
        System.out.println("target:"+target);
//      改变value的值
        System.out.println(target[1]='A');
//      打印暴力反射的结果
        System.out.println(s1);//hAllo
        System.out.println(s2);//hAllo
        System.out.println(s1==s2);//true
//      这就破环了hashSet的值的唯一性
        System.out.println(hashSet);
    }
}
