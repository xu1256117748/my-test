
import org.junit.jupiter.api.Test;

/**
 * @author 徐亚奎
 * @date 22/07/2021 13:08
 */
public class test5 {



    @Test
    public void test1(){
//      StringBuffer是可变的
        StringBuffer hello = new StringBuffer("Hello");
        System.out.println(hello);//Hello
        StringBuffer world = new StringBuffer("World");
        hello.append(world);
        System.out.println(hello);//HelloWorld
    }
    @Test
    public void test2(){
//      String拼接10万次--耗时244886毫秒
        String target="";
        String hello="Hello";
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            target=target+hello;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒");//耗时:24486毫秒
    }
    @Test
    public void test3(){
//      StringBuffer拼接100万次,耗时-->69毫秒
        StringBuffer target = new StringBuffer();
        StringBuffer hello = new StringBuffer("Hello");
        long start = System.currentTimeMillis();
        for (int i=0;i<1000000;i++){
            target.append(hello);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒");//耗时:69毫秒
    }
    @Test
    public void test4(){
 //     StringBuilder拼接100万次,耗时-->27毫秒
        StringBuilder target = new StringBuilder();
        StringBuilder hello = new StringBuilder("Hello");
        long start = System.currentTimeMillis();
        for (int i=0;i<1000000;i++){
            target.append(hello);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"毫秒");//耗时:27毫秒
    }

    public void test(){

    }
}
