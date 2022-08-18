import com.xyk.pojo.StaticResource;
import com.xyk.pojo.User;
import com.xyk.util.HttpClientUtil;
import com.xyk.util.JsonUtils;
import com.xyk.vo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-21 15:15
 * @Description
 */
@Slf4j
public class ZhiXue {






    public static void main(String[] args) {
        String url = "https://www.zhixue.com/freshprecisionapi/studentLiteracy/getClazzStuDetailsNew?classId=1500000100111795393&subjectId=01&timeId=halfYear&classFlag=1&t=1634804330413";
        String result = HttpClientUtil.doGet(url);
        log.debug("请求结果JSON串:"+result);
//        JsonResponse parse = JsonUtils.parse(result, JsonResponse.class);
//        log.debug("将JSON转化为对象:"+parse);
//        String s = JsonUtils.toJson(parse);
//        log.debug("将对象转化为JSON:"+s);


        String tock = "JSESSIONID=ABC08DC989BDAA53F616D862AACB46DD; deviceId=E360B28F-FB70-47F7-9123-87122C44AEAB; loginUserName=zxt3012849; SSO_CUSTOM_LOGOUT_URL=\"https://www.zhixue.com/login.html\"; tlsysSessionId=64f1266a-ce1b-49a6-95d4-304a202dc50d; isJump=true; ui=4444000020033478175; JSESSIONID=174DB9AE150A05C64D32493E66DC475F";
    }
}
