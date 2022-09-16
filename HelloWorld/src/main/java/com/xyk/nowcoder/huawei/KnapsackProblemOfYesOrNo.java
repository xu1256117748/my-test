package com.xyk.nowcoder.huawei;

import com.xyk.XykApi;

import java.util.Arrays;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-26 16:13
 * @Description 最简单的动态规划之 01背包问题 & 回溯
 *
 * 问题描述:
 * 有四个物品,背包总容量为8,背包最多能装入价值为多少的物品?
 *
 * 物品编号: 1 2 3 4
 * 物品体积: 2 3 4 5
 * 物品价值: 3 4 5 6
 * 输出结果: 10(2号和4号物品)
 * 参考链接:
 * https://www.bilibili.com/video/BV1K4411X766?spm_id_from=333.337.search-card.all.click&vd_source=3a08aaad615a4cc9f91a76e72073d5d5
 *
 */
public class KnapsackProblemOfYesOrNo {
    public static void main(String[] args) {
        int[] volume = {0,2,3,4,5,6}; // 物品的体积数组,为了计算方便,增加一个前置0
        int[] value = {0,3,4,5,6,7}; // 物品的价值数组,为了计算方便,增加一个前置0
        int maxVolume = 8;// 背包最大容量为8
        int itemNum = 4;// 物品数量共计为5

        // 方法一
//        int[][] maxValue = new int[itemNum+1][maxVolume + 1];
//        for (int i=1; i<=itemNum;i++){
//            int v1 = volume[i];// 遍历到的物品的体积
//            int v2 = value[i];// 遍历到的物品的价值
//            for (int j = 1; j<= maxVolume; j++){
//                if (v1 > j){ // 放不下
//                    maxValue[i][j] = maxValue[i-1][j]; // 就和遍历到的上一个物品的最大价值一样(因为都没放这个物品)
//                }else {
//                    maxValue[i][j]= Math.max(maxValue[i-1][j],maxValue[i-1][j-v1]+v2);// 分两种情况,不放进去和放进去,两者比较取最大值
//                }
//            }
//        }
//        XykApi.print2VArray(maxValue);

        // 方法二(方法一的优化,将二维数组优化为普通数组,节省了空间和运算效率)

        int[] maxValue = new int[maxVolume+1]; // 存储背包和物品对应的最大价值
        for (int i=1;i<=itemNum;i++){ // 遍历物品
            int v1 = volume[i]; // 该物品的体积
            int v2 = value[i]; // 该物品的价值
            for (int j=maxVolume;j>=v1;j--){ // 遍历背包容量(最小到v1即可,不需要到1,提高了运算效率)
                maxValue[j] = Math.max(maxValue[j],maxValue[j-v1]+v2);
            }
        }
        System.out.println(Arrays.toString(maxValue));
        System.out.println(maxValue[maxVolume]);





    }


}
