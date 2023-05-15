import com.xyk.XykApi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 选择排序
 * @author 徐亚奎
 * @date 22/07/2021 18:21
 */
public class SelectionSort {
    @Test
    public void SelectionSort(){
        /**
         * 选择排序
         * a、将第一个值看成最小值
         * b、然后和后续的比较找出最小值和下标
         * c、交换本次遍历的起始值和最小值
         * d、说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，然后每次遍历无序列表找出最小值。
         * e、代码实现
         * */
        List<Integer> list = XykApi.Factory_getRandomIntList(0, 20, 10,true);
        int[] randomArray = new int[10];
        for (int i = 0;i<list.size();i++){
            randomArray[i] = list.get(i);
        }
        int min;
        int index;
        int temp;
        for (int i=0;i<randomArray.length-1;i++){
            min=randomArray[i];
            index=i;
            for (int j=i+1;j<randomArray.length;j++){
                if (randomArray[j]<min){
                    min=randomArray[j];
                    index=j;
                }
            }
            if(index!=i){
                temp=randomArray[i];
                randomArray[i]=randomArray[index];
                randomArray[index]=temp;
            }
            System.out.println("第"+(i+1)+"轮选择排序成功,第"+(i+1)+"个数字:"+min+"已放入正确位置"+":"+ Arrays.toString(randomArray));
        }
    }
}
