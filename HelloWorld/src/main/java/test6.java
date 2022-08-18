import com.xyk.MapUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-09-29 11:30
 * @Description
 */
public class test6 {
    public static void main(String[] args) throws ParseException {
//        HashMap<String, Object> reqMap = new HashMap<>();
//        reqMap.put("id", 4);
//        reqMap.put("class", "高一1班");
//        reqMap.put("schoolId", "6");
//        List<String> keyList = MapUtil.getKeyList(reqMap);
//        System.out.println(keyList);
//        List<Object> valueList = MapUtil.getValueList(reqMap);
//        System.out.println(valueList);
        String time1 = "2021-11-11 10:15:10";
        String time2 = "2021-11-11 10:15:11";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date one = format.parse(time1);
        Date two = format.parse(time2);
        boolean before = one.before(two);
        System.out.println(before);



    }
}
