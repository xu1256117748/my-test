import com.xyk.XykApi;

import java.util.Arrays;
import java.util.List;

/**
 * 快速排序
 * @author 徐亚奎
 * @date 22/07/2021 19:39
 */
public class QuickSort {

    public static void main(String[] args){
        List<Integer> list = XykApi.Factory_getRandomIntList(0, 20, 10,true);
        int[] randomArray = new int[10];
        for (int i = 0;i<list.size();i++){
            randomArray[i] = list.get(i);
        }
        System.out.println(Arrays.toString(randomArray));
        int low =0;
        int height=randomArray.length-1;
        QuickSort(randomArray, low, height);
        System.out.println(Arrays.toString(randomArray));
    }

    public static void QuickSort(int[] arr,int low,int height){
        if (low>=height) {
            return;
        }
        int start=low;
        int end=height;
        int temp=arr[low];
        while (start<end){
            while (arr[end]>temp && start<end){
                end--;
            }
            do{
                start++;
            }
            while (arr[start]<temp && start<end);
            if(start<end){
                swap(arr, start, end);
            }

        }
        swap(arr, low, end);
        QuickSort(arr, low, end-1);
        QuickSort(arr, end+1, height);
    }
    public static void swap(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;

    }
}
