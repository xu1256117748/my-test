import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 徐亚奎
 * @date 2021-08-05 16:45
 */
public class test4 {
    public static void main(String[] args) throws ParseException {
//        System.out.println("当前时间戳:"+System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = dateFormat.parse("2021-1-1");
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse);
        long timetamp = cal.getTimeInMillis();
        System.out.println("2021-1-1 的时间戳:"+
                timetamp);
        System.out.println("当前时间的时间戳:"+System.currentTimeMillis());


    }
}
