package JavaBasic.NO_003;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-11 15:04
 * @Description
 */
@Slf4j
public class TestDemo_01 {
    @Test
    public void Test_01(){
        Student student_1 = new Student();
        Student student_2 = new Student("00001", "张三", 17);
        System.out.println(student_1);
        System.out.println(student_2);
    }
    @Test
    public void test_02(){
        Student_V2 student_v2 = new Student_V2();
        student_v2.eat();
        student_v2.study();
        student_v2.sleep();
    }

    /**
     * 类的继承中,方法的重载/重写,以及向上造型的特点
     * */
    @Test
    public void test_03(){
        Student student = new Student_V2();
        student.eat();
        student.study();
        student.sleep();
//        student.say();// 因为是用的Student来接的,所以在编译期间,看不到Student_V2扩展的方法
        System.out.println(student.getGender());
        System.out.println(student.sleep(3600)); // 因为重写了父类的sleep方法,因此无论是用Student接还是Student_V2接,调用的都是子类重写的方法
        // student.say();和student.sleep(3600);两个结果体现了向上造型的特点:
        // 编译看左(能不能看到子类的方法取决于父类中有没有这个方法,没有这个方法就会报错)
        // 执行看右(实际调用的是子类的方法)
        System.out.println(student); // 这里没有打印出gender,是因为子类重写了toString方法,否则也有gender属性
        System.out.println("------------------");


        Student student1 = new Student();
        System.out.println(student1.sleep(3600));
        System.out.println(student1.getGender());
        System.out.println(student1);


    }

    /**
     * 字符串的api之字符串的截取
     *
     * 浮点数高精度运算(double本身就是不精确的)
     * */
    @Test
    public void test_04(){
        String str = "abcdefghijklmn";
        System.out.println(str.substring(2, 5));//获取到下标为[2,5)的元素
        System.out.println(str.substring(2));// 截掉前2个元素,保留剩下的

        double num_1 = 6.2;
        String num_2 = "6.2";
        int num_3 = 3;
//        String result_1 = new BigDecimal(num_1).divide(new BigDecimal(num_3)).toString();
//        String result_2 = new BigDecimal(num_2).divide(new BigDecimal(num_3)).toString();
        // 使用divide时,如果除不尽会报错,解决办法:使用三参的divide方法,另外两个参数为:保留小数位数,取舍方式
        String result_1 = new BigDecimal(num_1).divide(new BigDecimal(num_3),2, RoundingMode.HALF_UP).toString();
        String result_2 = new BigDecimal(num_2).divide(new BigDecimal(num_3),2, RoundingMode.HALF_UP).toString();
        System.out.println(result_1);
        System.out.println(result_2);
        // 计算结果不同是因为,double本身就是不精确的,建议通过字符串解析成BigDecimal

    }


}
