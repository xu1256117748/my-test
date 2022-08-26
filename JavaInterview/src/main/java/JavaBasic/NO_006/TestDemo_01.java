package JavaBasic.NO_006;

import JavaBasic.NO_005.Student;
import com.xyk.XykApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-22 14:49
 * @Description
 */
//@Slf4j()
public class TestDemo_01 {



    /**
     * 测试 测试代码
     * */
    @Test
    public void test01() throws Exception {

        Card card = new Card();
        System.out.println(card);
        card.say();

    }

    @Test
    public void test02() throws Exception {

        Thread card_001 = new CreateCard();
        Thread card_002 = new CreateCard();
        Thread card_003 = new CreateCard();
        Thread card_004 = new CreateCard();

        card_001.start();
        card_002.start();
        card_003.start();
        card_004.start();

    }

    /**
     * 售票案例(未同步锁) 会出现多卖,重复卖的情况
     * */
    @Test
    public void test03() throws Exception {

        // 开启多线程 方式一 只创建了一个对象,那么seller类的资源都是共享的,无论加不加static关键字
//        Seller seller = new Seller(100);
//        new Thread(seller,"张三").start();
//        new Thread(seller,"李四").start();
//        new Thread(seller,"王五").start();

        // 开启多线程 方式二(推荐) 创建了多个seller,那么只有被static修饰的属于类资源的属性,才是共享的,更加灵活的控制线程的共享和私有资源
        int ThreadNumber = 3;
        for(int i = 0; i < ThreadNumber;i++){
            new Seller().start();
        }

        Thread.sleep(5000);
        System.out.println("票数最后剩余:"+Seller.tickets);

    }
    /**
     * 售票案例(synchronized)
     * */
    @Test
    public void test04() throws Exception {
        // 开启多线程 方式二
        int ThreadNumber = 3;
        for(int i = 0; i < ThreadNumber;i++){
            new Seller_V2().start();
        }
        Thread.sleep(5000);//描述太短会导致抢票没有全部完成,也就是票没有卖光的情况
        System.out.println("票数最后剩余:"+Seller_V2.tickets);
    }
    /**
     * 售票案例(线程池优化,线程池管理线程,有效地复用线程,避免了线程的反复创建和销毁)
     */

    @Test
    public void test05() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS
                , new ArrayBlockingQueue(5));

        int taskNums = 3;
        for(int i = 0; i < taskNums; i++){
            threadPoolExecutor.execute(new Seller_V2());
        }
        Thread.sleep(5000);//描述太短会导致抢票没有全部完成,也就是票没有卖光的情况
        System.out.println("票数最后剩余:"+Seller_V2.tickets);

    }



}
