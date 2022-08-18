package com.xyk.interfaceTest.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-10-17 17:06
 * @Description
 * 当前api大类:
 *      [数字或运算] 相关的api方法 统一以 MathUtils_ 开头命名 方法id以 [01] 开始表示
 *      [字符串] 相关的api方法 统一以 StringUtils_ 开头命名 方法id以 [02] 开始表示
 *      [日期] 相关的api方法 统一以 DateUtils_ 开头命名 方法id以 [03] 开始表示
 *      [集合] 相关的api方法 统一以CollectionUtils_ 开头命名 方法id以 [04] 开始表示
 *      [实体类] 相关的api方法 统一以 BeanUtils_ 开头命名 方法id以 [05] 开始表示
 *      [工厂] 相关的api方法 统一以 Factory_ 开头命名 (工厂方法指获取某些常用的对象,一般为随机的,用于测试其他代码的数据) 方法id以 [06] 开始表示
 *      [系统] 相关的api方法 统一以 SystemUtils_ 开头命名 方法id以 [07] 开始表示
 *      [文件] 相关的api方法 统一以 FileUtils_ 开头命名 方法id以 [08] 开始表示
 */
@Component
public class XykApi2 {
    @Value("${server.port:#{-1}}")
    public Integer port;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * [数字或运算] 相关的api方法 统一以 MathUtils_ 开头 方法id以 [01] 开始表示
     * */

    /**
     * 高精度计算两数之和 num1+num2 示例传参(num1,num2,null)
     * 方法id [01]001
     * @param num1 被加数
     * @param num2 加数
     * @param mathContext  精度格式,传入为null是默认为MathContext.UNLIMITED(无限个数字)
     * MathContext.DECIMAL32（精度:包括小数点前后,共计7个数字）---------------------------------
     *      其精度设置与 IEEE 754R Decimal32 格式（即 7 个数字）匹配，舍入模式为 HALF_EVEN，这是 IEEE 754R 的默认舍入模式。
     *                     ----------------------------------------------------------------------
     * MathContext.DECIMAL64（精度:包括小数点前后,共计16个数字）--------------------------------
     *      其精度设置与 IEEE 754R Decimal64 格式（即 16 个数字）匹配，舍入模式为 HALF_EVEN，这是 IEEE 754R 的默认舍入模式。
     *                     ---------------------------------------------------------------------
     * MathContext.DECIMAL128（精度:包括小数点前后,共计34个数字）-------------------------------
     *      其精度设置与 IEEE 754R Decimal128 格式（即 34 个数字）匹配，舍入模式为 HALF_EVEN，这是 IEEE 754R 的默认舍入模式。
     *                     ---------------------------------------------------------------------
     * MathContext.UNLIMITED（精度:包括小数点前后,共计无限个数字）------------------------------
     *      其设置具有[无限精度]算法所需值的 MathContext 对象。
     * @return 返回BigDecimal类型的相加的结果
     * */
    public static BigDecimal MathUtils_BigDecimal_sum(double num1, double num2, MathContext mathContext) {
        mathContext = Objects.isNull(mathContext) ?  MathContext.UNLIMITED : mathContext;
        return BigDecimal.valueOf(num1).add(BigDecimal.valueOf(num2),mathContext);
    }
    /**
     * 高精度计算两数相减 num1-num2 示例传参(num1,num2,null,null)
     * 方法id [01]002
     * @param num1 被减数
     * @param num2 减数
     * @param mathContext  精度格式,传入为null是默认为MathContext.UNLIMITED(无限个数字) 具体参数说明参见本类的sum方法
     * @return 返回BigDecimal类型的相减的结果
     * */
    public static BigDecimal MathUtils_BigDecimal_subtract(double num1, double num2, MathContext mathContext) {
        mathContext = Objects.isNull(mathContext) ?  MathContext.UNLIMITED : mathContext;
        return BigDecimal.valueOf(num1).subtract(BigDecimal.valueOf(num2),mathContext);
    }
    /**
     * 高精度计算两数相乘 num1*num2 示例传参(num1,num2,null,null)
     * 方法id [01]003
     * @param num1 被乘数
     * @param num2 乘数
     * @param mathContext  精度格式,传入为null是默认为MathContext.UNLIMITED(无限个数字) 具体参数说明参见本类的sum方法
     * @return 返回BigDecimal类型的相乘的结果
     * */
    public static BigDecimal MathUtils_BigDecimal_multiply(double num1, double num2, MathContext mathContext) {
        mathContext = Objects.isNull(mathContext) ?  MathContext.UNLIMITED : mathContext;
        return BigDecimal.valueOf(num1)
                .multiply(BigDecimal.valueOf(num2), mathContext);
    }
    /**
     * 高精度计算两数相除 num1/num2 示例传参(num1,num2,null,null)
     * 方法id [01]004
     * @param num1 被除数
     * @param num2 除数
     * @param scale 保留小数点后x位,传入null时,保留后两位
     * @param roundingMode 舍入方式,传入null时,以四舍五入计算
     * @return 返回BigDecimal类型的相除结果
     * */
    public static BigDecimal MathUtils_BigDecimal_divide(double num1, double num2, Integer scale, RoundingMode roundingMode) {
        scale = scale == null ? 2 : scale;
        roundingMode = Objects.isNull(roundingMode) ?  RoundingMode.HALF_UP : roundingMode;
        return BigDecimal.valueOf(num1)
                .divide(BigDecimal.valueOf(num2), scale, roundingMode);
    }

    /**
     * [字符串]相关的api方法 统一以 StringUtils_ 开头命名 方法id以 [02] 开始表示
     * */

    /**
     * 判断一个字符串是否为回文串
     * 方法id [02]001
     * @param str 需要判断是否为回文串的字符
     * @return true/false
     */
    public static Boolean StringUtils_isPalindrome(String str){
        if (!StringUtils.hasLength(str)) {
            return false;
        }
        // 方法一: 通过for循环判断
        for (int i = 0; i < str.length()/2; i++){
            // 判断首字符和尾字符是否相等
            if (!Objects.equals(str.charAt(i),str.charAt(str.length()-1-i))){
                return false;
            }
        }
        // 方法二: 通过翻转字符判断是否是回文串
//        StringBuilder sb = new StringBuilder(str);
//        sb.reverse();
//        return sb.toString().equals(str);
        return true;
    }
    /**
     * 去除一个字符串中所有重复的字符
     * 方法id [02]002
     * @param str 需要去重的字符串
     * @param flag 去重时,是否区分大小写
     *             null/true : 区分大小写
     *             false : 不区分大小写
     * */
    public static String StringUtils_distance(String str,Boolean flag){
        if (!StringUtils.hasLength(str)){
            return "";
        }
        flag =  flag == null ? true : flag;
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
         for(int i = 0;i<str.length();i++){
             String c = String.valueOf(str.charAt(i));
             if (!list.contains(c)){
                 sb.append(c);
                 if (flag == true){
                     list.add(c.toUpperCase());
                     list.add(c.toLowerCase());
                 }else {
                     list.add(c);
                 }
             }
         }
        return sb.toString();
    }
    /**
     * 截取一个字符串中,两个字符串之间的字符(第一个前缀和第一个后缀(从第一个前缀出现后开始计算)之间的字符)
     * 方法id [02]003
     * @param str 原字符串
     * @param preStr 截取标识符_前缀 以此为截取之始
     * @param sufStr 截取标识符_后缀 以此为截取之终
     * */
    public static String StringUtils_subStrBetween(String str,String preStr,String sufStr){
        if (Strings.isEmpty(str) || Strings.isEmpty(preStr) || Strings.isEmpty(sufStr) ||
                !str.contains(preStr) || !str.contains(sufStr)){
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
        return str.substring(startIndex+preStr.length(),endIndex+1);
    }

    /**
     * [日期]相关的api方法 统一以 DateUtils_ 开头命名 方法id以 [03] 开始表示
     * */

    /**
     * 获取指定时间的时间戳(yyyy-MM-dd HH:mm:ss)
     * 方法id [03]001
     * @param time 日期字符串 示例:2021-03-05 00:05:06 传入为null时,以当前时间计
     * @param pattern 时间模型,推荐使用此类的常量:
     *                DATE_PATTERN = "yyyy-MM-dd" 精度:日期级别
     *                DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss" 精度:毫秒级别
     *                LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss SSS" 微秒/纳秒级别
     *                传入为null时默认为毫秒级别
     * @return long类型的时间戳
     * */
    public static Long DateUtils_getTimeStampByTime(String time,String pattern) throws ParseException {
        time = StringUtils.hasLength(time) ? time : LocalDateTime.now().toString();
        pattern = StringUtils.hasLength(pattern) ? pattern : DATE_TIME_PATTERN;
        Calendar cal = Calendar.getInstance();
        Date parseTime = new SimpleDateFormat(pattern).parse(time);
        cal.setTime(parseTime);
        return cal.getTimeInMillis();
    }

    /**
     * [集合] 相关的api方法 统一以 CollectionUtils_ 开头命名 方法id以 [04] 开始表示
     * */

    /**
     * 深复制一个集合
     * 方法id [04]001
     * @param sourceList 需要被深复制的集合
     * @return 复制的新集合(新地址)
     * */
    public static <T>List<T> CollectionUtils_deepCopyList(List<T> sourceList){
        List<T> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourceList)){
            return resultList;
        }
        for (T t : sourceList){
            resultList.add(t);
        }
        return resultList;
    }

    /**
     * [实体类] 相关的api方法 统一以 BeanUtils_ 开头命名 方法id以 [05] 开始表示
     * */

    /**
     * 对象属性转换为map 默认示例传参(obj,null,null)
     * 方法id [05]001
     * @param obj 原对象
     * @param type 转换时,是转换所有属性,还是只转换值非空的属性
     *             [null]: 转换全部属性,属性为空的置为""
     *             [1]: 转换全部属性,属性为空的置为null
     *             [0/其他]:只转换非空属性
     * @param open 转换时,是否只转换公开属性
     *             [null/false] : 转换所有属性
     *             [true] : 只转换公开属性
     * @return Map<String, Object> 返回该对象属性的k-v的map集合
     * */
    public static Map<String,Object> BeanUtils_objToMap(Object obj,Integer type, Boolean open) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fieldList = Objects.isNull(open) || open==false ?
                obj.getClass().getDeclaredFields() : obj.getClass().getFields();
        for (Field field : fieldList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (type == null){ // null: 转换全部属性,属性为空的置为""
                map.put(fieldName, value == null ? "" : value);
            }else if (type == 1){ // 1: 转换全部属性,属性为空的置为null
                map.put(fieldName, value);
            } else { // 0/其他:只转换非空属性
                if (!Objects.isNull(value)){
                    map.put(fieldName, value);
                }
            }
        }
        return map;
    }
    /**
     * map数据结构转换成Object对象
     * 方法id [05]002
     * 1.静态属性和final修饰的属性不会被赋值
     * 2.赋值时,基本类型会通过对应包装类的valueOf获取值,一定程度上避免了不必要的反射错误
     * @param map 存储属性的map数据结构
     * @param beanClass  Object对象的类型
     * */
    public static <T>T BeanUtils_mapToObject(Map<Object, Object> map, Class<T> beanClass) throws Exception {
        if (map == null || beanClass == null) {
            return null;
        }
        T bean = beanClass.newInstance();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            Object value = map.get(field.getName());
            if (map.containsKey(field.getName())) {
                field.set(bean, XykApi2.BeanUtils_objectValueOf(value, field.getType()));
            }
        }
        return bean;
    }
    /**
     * 对各个valueOf的封装,避免反射注入属性时的简单错误,意在将"3.2"字符串转换成double/float类型时,不会报错
     * (正常来说,通过反射强制注入会报错,因此需要先把"3.2"转换成3.2才可以完成反射注入属性值)
     * 方法id [05]003
     * @param value 八大基本类型/包装类型的值
     * @param type  八大基本类型/包装类型 的 类型
     *              当类型为八大基本类型,且值为null时会出现空指针异常,因此推荐包装类型
     * */
    public static <T>T BeanUtils_objectValueOf(Object value,Class<T> type){
        if (type == null || value == null || value.toString() == ""){
            return null;
        }
        String valueStr = value.toString();
        if (type == Integer.class || type == int.class){
            return (T)Integer.valueOf(valueStr);
        } else if (type == Double.class || type == double.class) {
            return (T)Double.valueOf(valueStr);
        }else if (type == Float.class || type == float.class){
            return (T)Float.valueOf(valueStr);
        }else if (type == Boolean.class || type == boolean.class){
            return (T)Boolean.valueOf(valueStr);
        } else if (type == Byte.class || type == byte.class){
            return (T)Byte.valueOf(valueStr);
        }else if (type == Short.class || type == short.class){
            return (T)Short.valueOf(valueStr);
        }else if (type == Long.class || type == long.class){
            return (T)Long.valueOf(valueStr);
        }else if (type == Character.class || type == char.class) {
            return (T) Character.valueOf(valueStr.charAt(0));
        }else if (type == String.class){
            return (T)valueStr;
        } else {
            return (T) value;
        }
    }

    /**
     * [工厂] 相关的api方法 统一以 Factory_ 开头命名 (工厂方法指获取某些常用的对象) 方法id以 [06] 开始表示
     * */

    /**
     * 获取随机整形值的集合 [min,max] 包含最大最小值
     * 方法id [06]001
     * @param min 整形元素的最小值
     * @param max 整形元素的最大值
     * @param length 整形元素的数量
     * @param flag 是否包含重复数 null/true:允许包含重复 false:不包含重复
     * */
    public static List<Integer> Factory_getRandomIntList(Integer min, Integer max, Integer length,Boolean flag) {
        ArrayList<Integer> list = new ArrayList<>();
        if (min== null || max == null || length == null || max < min || length <= 0){
            return list;
        }
        while (list.size()<length){
            Integer i = new Random().nextInt(max - min + 1) + min;
            if (flag == null || flag == true){
                list.add(i);
            }else {
                if (!list.contains(i)){
                    list.add(i);
                }
            }
        }
        return list;
    }
    /**
     * 获取随机整形值的集合(最小值为0,最大值为100 长度为20)
     * 方法id [06]001
     * @return list<Integer>
     * */
    public static List<Integer> Factory_getRandomIntList() {
        return Factory_getRandomIntList(0,127,20,false);
    }
    /**
     * 获取a-z的随机集合
     * 方法id [06]002
     * @param upOrLow 大写还是小写的英文字母
     *                null: 大写+小写  0: 小写  1/其他: 大写
     * @param length 获取的集合的长度
     * @param flag 是否允许存在重复字符
     *             false/null: 不重复的集合
     *             true: 可重复的集合
     * */
    public static List<Character> Factory_getRandomCharList(Integer upOrLow,Integer length,Boolean flag){
        ArrayList<Character> result = new ArrayList<>();
        if (length == null || length <= 0){
            return result;
        }
        while (result.size()<length){
            Character c = null;
            if (upOrLow == null){ // 既含大写也含小写
                switch  ((new Random().nextInt(2))) {
                    case  0: c=(char)(Math. random ()*26+ 'a' ) ;break;
                    case  1: c=(char)(Math. random ()*26+ 'A' ) ;break;
                    default:break;
                }
            }else {
                Integer start = upOrLow == 1 ? 65 : 97; // 大写或小写
                c = (char)(new Random().nextInt(26)+start);
            }
            if (flag == null || flag == false){ // 去重
                if (!result.contains(c)){
                    result.add(c);
                }
            }else { // 不去重
                result.add(c);
            }
        }
        return result;
    }

    /**
     * [系统] 相关的api方法 统一以 SystemUtils_ 开头命名 方法id以 [07] 开始表示
     * */
    /**
     * 获取操作系统名称 (linux/windows等,一般用于,基于不同的操作系统,适用不同的文件路径)
     * 方法id [07]001
     * */
    public static String SystemUtils_getOSName(){
        return System.getProperty("os.name");
    }
    /**
     * 获取操作系统的版本号
     * 方法id [07]001
     * */
    public static String SystemUtils_getOSVersion(){
        return System.getProperty("os.version");
    }
    /**
     * 获取本机的ip/host,windows和linux通用
     * 运行的端口可以通过本类的属性port获取
     * 方法id [07]001
     * */
    public static String SystemUtils_getHost() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e);
        }
        return "";
    }
    /**
     * [文件] 相关的api方法 统一以 FileUtils_ 开头命名 方法id以 [08] 开始表示
     * */




}
