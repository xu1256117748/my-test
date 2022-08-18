import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-10 19:22
 * @Description
 */
public class PowerCompute {
    /**
     * 一个数的n次方计算
//     * */
    public static void main(String[] args) {
        System.out.println("---------一个数的n次方计算---------");
        System.out.println("---------介绍:如m^n,表示n个m相乘,其中,m为底数,n为指数---------");
        System.out.println("---------请依次输入底数m和指数n的值,两值以空格隔开---------");
        String input = "";

        do {
            input = new Scanner(System.in).nextLine();

            if (StringUtils.isBlank(input)){
                System.out.println("尚未输入任何值,请重新输入!");
            }
        }while (StringUtils.isBlank(input));

        // 将用户输入的字符串,转换成数字数组
        List<Integer> list = Arrays.stream(input.split(" "))
                .filter(s-> !StringUtils.isBlank(s)) // 删除集合中的所有空格元素
                .map(Integer::valueOf) // 将集合中的字母转换成数字
                .collect(Collectors.toList());


        int m=list.stream().findFirst().get(); // 得到第一个数字,即底数
        int result = 1;
        Collections.reverse(list);
        int n = list.stream().findFirst().get(); // 得到最后一个数字,即指数

        if (m==0){
            result = 0;
        }else if (n>0){
            for (int i = 0; i < n ; i++){
                result*=m;
            }
        }else if (n == 0){

            result = 1;
        }else{
            for (int i = 0; i > n ; i--){
                result*=m;
            }
        }

        String resultStr = n>=0 ? result+"" : "1/"+result;

        System.out.println( m+"的" + n + "次方为:"+ resultStr);
    }

}
