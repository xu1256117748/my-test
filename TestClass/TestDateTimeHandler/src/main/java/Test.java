import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 徐亚奎
 * @date 2021-09-10 15:09
 * @Description
 */
public class Test {
    // 将文本类型的时间转化为localdatetime类型
    @org.junit.Test
    public void test01(){
        String str = "2021-03-05 21:05:08";
        String format =  "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parse = LocalDateTime.parse(str, dateTimeFormatter);
        System.out.println(parse);
    }
    // 将localdatetime类型的时间转化为文本类型(实现方式一)
    @org.junit.Test
    public void  test02(){
        long start = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒"); // 50毫秒
    }
    // 将localdatetime类型的时间转化为文本类型(实现方式二)
    @org.junit.Test
    public void  test03(){
        long start = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String replace = now.toString().replace('T', ' ');
        String substring = replace.substring(0, replace.indexOf('.'));
        System.out.println(substring);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒"); // 40毫秒
    }
}
