import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author 徐亚奎
 * @date 21/07/2021 18:11
 */
public class test2 {
    public static void main(String[] args) {
        char[] chars = new char[3];
        chars[0]='A';
        char[] a=chars;
        chars[1]='B';
        char[] b=chars;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a==b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));



    }
    public void sayHello(){
        System.out.println("test2 say hello");
    }
}
