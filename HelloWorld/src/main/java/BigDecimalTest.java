import com.xyk.XykApi;

import java.math.RoundingMode;

/**
 * @author 徐亚奎
 * @date 2021-10-17 15:41
 * @Description
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        Double result;
        String str;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2,null).doubleValue(); // 5
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2,RoundingMode.UP).doubleValue(); // 6
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2,RoundingMode.DOWN).doubleValue(); // 7
        System.out.println("["+num+++"] "+result);
    }

}
