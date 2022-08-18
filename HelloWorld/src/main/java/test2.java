import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐亚奎
 * @date 13/07/2021 10:25
 */
public class test2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        温度范围
        int a1=0,a2=100;
//        压力
        int b1=0,b2=100;
//        原料状态
        int c1=0,c2=100;
//        空速
        int d1=0,d2=100;
//        封装数据
        HashMap<Integer, String> map = new HashMap<>();
        int count =0;
//        for (int a=a1;a<=a2;a++){
//            for (int b=b1;b<=b2;b++){
//                for (int c=c1;c<=c2;c++){
//                    for (int d=d1;d<=d2;d++){
//                        count++;
//                        map.put("第"+count+"条数据","温度:"+a1+";压力:"+b1+";空速:"+c1+";原料状态:"+d1+";");
//                    }
//                }
//            }
//        }
//        [a,b,c,d]


        int b=b1,c=c1,d=d1;
        for (int a=a1;a<a2+1;d++){
            count++;
           if (d>d2){
                d=d1;
                c++;
                if (c>c2){
                    c=c1;
                    b++;
                    if (b>b2){
                        b=b1;
                        a++;
                    }
                }
            }
            System.out.println("第"+count+"条数据:"+"温度:"+a+";压力:"+b+";空速:"+c+";原料状态:"+d+";");
//            map.put(count,"温度:"+a+";压力:"+b+";空速:"+c+";原料状态:"+d+";");
        }
        System.out.println("获取所有数据成功,数量为:"+count);
        long end = System.currentTimeMillis();
        System.out.println("执行耗时:"+(end-start)+"毫秒");
//        for (int i=0;i<count;i=i+100){
//            System.out.println("正在打印"+i+"到"+(i+100)+"的数据");
//            for (int k=0;k<100;k++){
//                System.out.println(map.get(i+k));
//            }
//        }
//
////        System.out.println(map);
//        long end2 = System.currentTimeMillis();
//        System.out.println("全部执行耗时:"+(end-start)+"毫秒");
    }
}
