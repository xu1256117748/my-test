import com.xyk.XykApi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 插入排序
 * @author 徐亚奎
 * @date 22/07/2021 18:52
 */
public class InsertionSort {
    /**
     * 插入排序
     * a、默认从第二个数据开始比较。
     * b、如果第二个数据比第一个小，则交换。然后在用第三个数据比较，如果比前面小，则插入（狡猾）。否则，退出循环
     * c、说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出。
     * d、代码实现
     * */
    @Test
    public void  InsertionSort(){
        List<Integer> list = XykApi.Factory_getRandomIntList(0, 20, 10,true);
        int[] randomArray = new int[10];
        for (int i = 0;i<list.size();i++){
            randomArray[i] = list.get(i);
        }
        System.out.println(Arrays.toString(randomArray));
        int temp=0;
        for (int i = 1; i <randomArray.length ; i++) {
            for (int j = i; j >0; j--) {
                if(randomArray[j]<randomArray[j-1]){
                   temp=randomArray[j];
                   randomArray[j]=randomArray[j-1];
                   randomArray[j-1]=temp;
                }else {
                    break;
                }
            }
            System.out.println("第"+i+"轮插入排序成功:"+ Arrays.toString(randomArray));
        }
    }
}
