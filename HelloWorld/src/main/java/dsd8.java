import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

/**
 * @author 徐亚奎
 * @date 2021-10-28 17:17
 * @Description
 */
public class dsd8 {
    public static void main(String[] args) {
        String cookie = "JSESSIONID=7A17D36BCF6BE351DC1FE37011DD3090; " +
                "deviceId=E360B28F-FB70-47F7-9123-87122C44AEAB; " +
                "loginUserName=zxt3012849; " +
                "Hm_lvt_71f0ed158f554118b01c2f97eac16263=1634868515; " +
                "SSO_CUSTOM_LOGOUT_URL=\"https://www.zhixue.com/login.html\"; " +
                "tlsysSessionId=0dc71005-63ba-4bfa-9108-cf7384951b4c; isJump=true; " +
                "ui=444400;;; JSESSIONID=39BBDF57B289D32B51BC3C48AD22CA0E";

//        int start = cookie.indexOf("ui=");
//
//        int end = cookie.indexOf(";");
//        String s = cookie.substring(cookie.indexOf("ui="), cookie.indexOf(";"));
//        System.out.println(s);
        String s = subStr(cookie, "ui=", ";;;");
        System.out.println(s);
    }
    /**
     * 截取一个字符串中的,两个指定字符串之间的字符串
     * */
    public static String subStr(String str,String preStr,String sufStr){
        if (Strings.isEmpty(str) || Strings.isEmpty(preStr) || Strings.isEmpty(sufStr) ||
                !str.contains(preStr) || !str.contains(preStr)){
            return "";
        }
        Integer startIndex = str.indexOf(preStr);
        Integer endIndex = startIndex;
        for (int i = startIndex; i < str.length(); i++){
            Integer index = 0;
            while(index<sufStr.length()){
                if (Objects.equals(str.charAt(i), sufStr.charAt(index))){
                    index++;
                    if (index == sufStr.length()){
                        break;
                    }else {
                        i++;
                        continue;
                    }
                }else {
                    i++;
                    index = 0;
                }
            }
            if (index == sufStr.length()){
                endIndex = i-sufStr.length();
                break;
            }
        }
        return str.substring(startIndex,endIndex+1);
    }
}
