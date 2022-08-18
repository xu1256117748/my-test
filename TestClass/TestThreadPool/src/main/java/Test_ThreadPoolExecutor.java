import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐亚奎
 * @date 12/07/2021 11:00
 */
@Slf4j
public class Test_ThreadPoolExecutor {

    @Test
    public  void test1() throws InterruptedException {
        //        核心线程数:10.非核心线程数:20(最大线程数10+20=30),非核心线程存活时间:1天,阻塞队列容量:10
//        执行结果:执行了40个(核心10+阻塞队列10+非核心10)任务,第41个任务拒绝执行
//        执行流程:前10个任务由10核心线程执行-->11-20的任务进入阻塞队列等待执行-->21-40的任务放入阻塞队列失败,而线程数未到最大线程数,创建非核心线程数-->
//        -->创建20个非核心线程,由20个非核心线程执行-->发现第41个任务,核心线程数正在执行,阻塞队列已满,达到最大线程数,拒绝执行第41个任务-->
//        拒绝执行并抛出异常,程序终止-->11-20号任务未被执行

//        --提交优先级和执行优先级--
//        提交优先级:核心线程数-->阻塞队列-->非核心线程数
//        执行优先级:核心线程数-->非核心线程书-->阻塞队列

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 5,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        int count =0;
        for (int i=0;i<40;i++){
            executor.execute(new MyTask(i+1));
//            log.debug("执行了"+count+"个任务");
        }
        System.out.println("执行了"+count+"个任务,拒绝执行第:"+(++count)+"个任务");
        Thread.sleep(6000);
        for (int i=0;i<40;i++){
            executor.execute(new MyTask(i+1));
//            log.debug("执行了"+count+"个任务");
        }
    }
}
@Slf4j
class MyTask implements Runnable{
    private Integer id;
    public MyTask(Integer id){
        this.id=id;
    }

    @Override
    public void run() {
        try {
            log.debug("执行了第"+this.id+"个任务");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
