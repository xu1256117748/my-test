import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * @author 徐亚奎
 * @date 22/07/2021 23:15
 */
public class testQuickSort {

    public static void main(String[] args){
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i]=new Random().nextInt(20);
        }
        System.out.println("初始数组 : "+Arrays.toString(array));
        int left=0;
        int right=array.length-1;
        QuickSort(array, left, right);
        System.out.println("快排完成 : "+Arrays.toString(array));
    }
    public static void QuickSort(int[] arr,int left,int right){
        if (left>=right) return;
        int start=left;
        int end=right;
        int temp=arr[left];
        while (start<end){
            while (arr[end]>temp && start<end){
                end--;
            }
            do{
                start++;
            }while (arr[start]<temp && start<end);
            if(start<end){
                swap(arr, start, end);
            }
        }
        swap(arr, left, end);
        if(left<end)
        QuickSort(arr, left, end-1);
        if (end<right)
        QuickSort(arr,end+1,right);
    }
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
