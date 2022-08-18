import com.xyk.XykApi;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 冒泡排序
 * @author 徐亚奎
 * @date 22/07/2021 18:20
 */
public class BubbleSort {
    @Test
    public void BubbleSort(){
        /**
         * 冒泡排序
         * a、冒泡排序，是通过每一次遍历获取最大/最小值
         * b、将最大值/最小值放在尾部/头部
         * c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
         * d、代码实现:
         */
        List<Integer> list = XykApi.Factory_getRandomIntList(0, 20, 10,true);
        int[] randomArray = new int[10];
        for (int i = 0;i<list.size();i++){
            randomArray[i] = list.get(i);
        }
        System.out.println(Arrays.toString(randomArray));
        int mid;
        for (int i=0;i<randomArray.length-1;i++){
            for (int j  = 0; j  < randomArray.length-i-1; j ++) {
                if(randomArray[j] >randomArray[j+1] ){
                    mid=randomArray[j];
                    randomArray[j]=randomArray[j+1];
                    randomArray[j+1]=mid;
                }
            }
            System.out.println(Arrays.toString(randomArray));
        }
//        System.out.println(Arrays.toString(randomArray));
    }
}
