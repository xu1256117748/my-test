package JavaBasic.NO_006;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-23 10:50
 * @Description 售票员类,共同卖固定票(如果值创建一个seller类开启多线程,那么static关键字可以省却)
 */
public class Seller extends Thread{

    public static Integer tickets = 100;

    public Seller(){

    }
    public Seller(Integer tickets){
        this.tickets = tickets;
    }



    @Override
    public void run() {

        while (tickets>0){
            try {
                Thread.sleep(10);// 压力测试用,否则很难出现多卖情况
            }catch (Exception e){
                e.printStackTrace();
            }

            --this.tickets;
            System.out.println(Thread.currentThread().getName()+"卖出了第 "+(100-this.tickets)+" 张票");
        }
    }
}
