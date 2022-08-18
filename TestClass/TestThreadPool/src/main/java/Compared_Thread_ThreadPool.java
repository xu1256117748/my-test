import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐亚奎
 * @date 12/07/2021 09:40
 * 此类用于测试线程和线程池的区别-->效率比线程高300倍-->因为不用频繁创建线程
 */
@Slf4j
public class Compared_Thread_ThreadPool {
    public static void main(String[] args) throws InterruptedException {

        testThread();
//        testThreadPool();
    }
//通过Thread创建10万个线程-->10800毫秒
    @Test
    public static void testThread() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> list=new ArrayList<>();
        for (int i =0;i<100000;i++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    list.add(new Random().nextInt(100));
                }
            };
            thread.start();
            thread.join();

        }
        long end = System.currentTimeMillis();
        System.out.println("运行耗时:"+(end-start)+"毫秒");
        System.out.println("数组大小:"+list.size());
    }
//    通过线程池创建10万个线程-->36毫秒
    public static void testThreadPool() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> list=new ArrayList<>();
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i =0;i<100000;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(new Random().nextInt(100));
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        long end = System.currentTimeMillis();
        System.out.println("运行耗时:"+(end-start)+"毫秒");
        System.out.println("数组大小:"+list.size());
    }
}
