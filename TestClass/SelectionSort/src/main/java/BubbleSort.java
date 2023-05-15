import com.xyk.XykApi;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 冒泡排序
 * @author 徐亚奎
 * @date 22/07/2021 18:20
 */
public class BubbleSort {
    @Test
    public void BubbleSort_Integer(){
        /**
         * 冒泡排序
         * a、冒泡排序，是通过每一次遍历获取最大/最小值
         * b、将最大值/最小值放在尾部/头部
         * c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
         * d、代码实现(将最大值放在尾部):
         */
        // 模拟随机数集合
        int size=10;
        int[] randomArray = new int[size];
        for (int i = 0;i<size;i++){
            randomArray[i] = new Random().nextInt(101);
        }
        System.out.println("获得的随机数组:"+Arrays.toString(randomArray));
        // 冒泡排序
        int mid;
        for (int i=0;i<randomArray.length-1;i++){
            for (int j  = 0; j  < randomArray.length-i-1; j ++) {
                if(randomArray[j] >randomArray[j+1] ){
                    mid=randomArray[j];
                    randomArray[j]=randomArray[j+1];
                    randomArray[j+1]=mid;
                }
            }
            System.out.println("第0"+(i+1)+"轮冒泡排序:"+Arrays.toString(randomArray));
        }
//        System.out.println(Arrays.toString(randomArray));
    }
    @Test
    public void BubbleSort_BigDecimal(){
        /**
         * 冒泡排序
         * a、冒泡排序，是通过每一次遍历获取最大/最小值
         * b、将最大值/最小值放在尾部/头部
         * c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
         * d、代码实现(将最小值放在尾部):
         */
        // 模拟浮点数集合
        int size = 10;
        BigDecimal[] randomArray = new BigDecimal[size];
        for (int i = 0; i < size;i++){
            //BigDecimal在运行时虽然会比基础类型略慢,但能够完全控制计算精度
            randomArray[i]= new BigDecimal(new Random().nextInt(101))
                    .divide(new BigDecimal(8), 3, RoundingMode.HALF_UP);
        }
        System.out.println("随机浮点数:"+Arrays.toString(randomArray));
        // 冒泡排序
        BigDecimal mid;
        for (int i=0;i<randomArray.length-1;i++){
            for (int j  = 0; j  < randomArray.length-i-1; j ++) {
                if (randomArray[j].compareTo(randomArray[j+1])==-1){
                    mid=randomArray[j];
                    randomArray[j]=randomArray[j+1];
                    randomArray[j+1]=mid;
                }
            }
            System.out.println("第0"+(i+1)+"轮冒泡排序:"+Arrays.toString(randomArray));
        }
    }
}
