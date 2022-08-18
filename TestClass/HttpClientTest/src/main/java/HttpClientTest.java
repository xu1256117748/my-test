import com.xyk.pojo.StaticResource;
import com.xyk.pojo.User;
import com.xyk.util.HttpClientUtil;
import com.xyk.util.JsonUtils;
import com.xyk.vo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-08-10 16:34
 */
@Slf4j
public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:6666/test/getUserList";
        String result = HttpClientUtil.doGet(url);
        log.debug("请求结果JSON串:"+result);
        JsonResponse parse = JsonUtils.parse(result, JsonResponse.class);
        log.debug("将JSON转化为对象:"+parse);
        String s = JsonUtils.toJson(parse);
        log.debug("将对象转化为JSON:"+s);

        String s1="";
        List<User> userList = StaticResource.userList;


        System.out.println((int)21.56);
    }
}
