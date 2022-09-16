import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-09-16 12:21
 * @Description 根据学科成绩和对应学分,计算最终成绩
 * 计算公式:
 *  学科  分数  学分
 *  语文  97  3
 *  数学  96  2.5
 *  英语  95  2
 *  最终成绩 = (97*3+96*2.5+95*2)/(3+2.5+2)
 */
public class Main {
    public static void main(String[] args) {
        // 终止输入符号,可任意设置,区分大小写(下面已设置成不区分大小写)
        String fin = "OK";
        System.out.println("输入终止符:"+fin);
        Scanner scanner = new Scanner(System.in);
        ArrayList<BigDecimal> scores = new ArrayList<>();
        ArrayList<BigDecimal> decimals = new ArrayList<>();
        while (true){
            String next = scanner.next();
            String s1 = next.toUpperCase();
            String s2 = fin.toUpperCase();
            if (s1.equals(s2)){
                break;
            }
            try {
                BigDecimal s = new BigDecimal(next);
                BigDecimal decimal = scanner.nextBigDecimal();
                scores.add(s);
                decimals.add(decimal);
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
        BigDecimal total = new BigDecimal("0");
        for ( int i = 0; i < scores.size();i++ ){
            total = total.add(scores.get(i).multiply(decimals.get(i)));
        }

        BigDecimal divided = new BigDecimal("0");
        for ( int i = 0; i < decimals.size();i++){
            divided = divided.add(decimals.get(i));
        }
        // 除法运算时如果不保留小数,一旦出现无限小数,就会报错
        Double result = total.divide(divided,5, RoundingMode.HALF_UP).doubleValue();
        System.out.println("最终得分:"+result);
    }
}
