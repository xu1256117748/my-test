package JavaBasic.NO_006;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-23 10:50
 * @Description 售票员类, 共同卖固定票(如果值创建一个seller类开启多线程, 那么static关键字可以省却)
 */
public class Seller_V2 extends Thread{

    public static Integer tickets = 100; // static修饰的属性为所有线程的共有资源,,记录总票数
    int num = 0; // 每个线程的私有资源,记录每个售票员自己卖掉的票数

    public Seller_V2(){

    }
    public Seller_V2(Integer tickets){
        this.tickets = tickets;
    }



    @Override
    public void run() {

            while (true){ // 这里应当是true而不是this.tickets>0 ,因为this.tickets>0需要被synchronized包裹起来
                synchronized (Seller_V2.class){ // 继承Thread类是,这里应该是Seller_V2.class,实现runable接口时,这里应该是this
                    try {
                        Thread.sleep(10);// 压力测试用,否则很难出现多卖重复卖的情况
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if (tickets>0){
                        --tickets;
                        this.num++;
                        System.out.println(Thread.currentThread().getName()+"卖出了第 "+(100-tickets)+" 张票");
                    }else {
                        System.out.println("票已售罄!");
                        break;
                    }
            }
        }
        System.out.println(Thread.currentThread().getName()+"共计卖出了 "+this.num+" 张票");

    }
}
