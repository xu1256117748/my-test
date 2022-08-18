import java.util.Map;
import java.util.Objects;

/**
 * @author 徐亚奎
 * @date 2021-08-10 13:03
 */
public class testInterface {
    public static void main(String[] args) {
        String num = "200";
        Integer value = 200;
        boolean flag = Objects.equals(num, "200");
        System.out.println(flag);
        //        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(url);
//            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            if (statusCode != 200){
//                return new CommonDeviceRecordBO(recordData.getDeviceId()
//                        , recordData.getSerialNumber()
//                        , DeviceRecordStateEnum.ERR_DISTRIBUTE.getState());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new CommonDeviceRecordBO(recordData.getDeviceId()
//                    , recordData.getSerialNumber()
//                    , DeviceRecordStateEnum.ERR_DISTRIBUTE.getState());
//        }
    }
}
