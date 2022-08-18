package com.xyk;

import com.xyk.pojo.Cat;
import com.xyk.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 25/07/2021 11:31
 */
@Slf4j
public class XykApiTest {
    public static void main(String[] args) throws Exception{
        //-----------
        // MathUtils_
        //-----------
        // 方法id [01]001 高精度计算两数之和 num1+num2 示例传参(num1,num2,null)
//        MathUtils_BigDecimal_sum();
        // 方法id [01]002 高精度计算两数之差 num1-num2 示例传参(num1,num2,null)
//        MathUtils_BigDecimal_subtract();
        // 方法id [01]003 高精度计算两数之积 num1*num2 示例传参(num1,num2,null)
//        MathUtils_BigDecimal_multiply();
        // 方法id [01]004 高精度计算两数之除 num1/num2 示例传参(num1,num2,null)
//        MathUtils_BigDecimal_divide();

        //-------------
        // StringUtils_
        //-------------
        // 方法id [02]001 判断一个字符串是否为回文串
//        StringUtils_isPalindrome();
        // 方法id [02]002 去除一个字符串中所有重复的字符
//        StringUtils_distance();
        // 方法id [02]003 截取一个字符串中,两个字符串之间的字符(第一个前缀和第一个后缀(从第一个前缀出现后开始计算)之间的字符)
//        StringUtils_subStrBetween();

        //------------------
        // XykApi.DateUtils_
        //------------------
        // 方法id [03]001 根据时间获取时间戳的方法 测试
//        DateUtils_getTimeStamp();

        //------------------
        // XykApi.CollectionUtils_
        //------------------
        // 方法id [04]001 深复制一个集合 测试
//        CollectionUtils_deepCopyList();

        //------------------
        // XykApi.BeanUtils_
        //------------------
        // 方法id [05]001 对象属性转换map
//        BeanUtils_objToMap();
        // 方法id [05]002 map转换对象
//        BeanUtils_mapToObject();
        // 方法id [05]003 对各个valueOf的封装,避免反射注入属性时的简单错误
//        BeanUtils_objectValueOf();

        //------------------
        // XykApi.Factory_
        //------------------
        // 方法id [06]001 获取随机整形数组 测试
//        Factory_getRandomIntList();
        // 方法id [06]002 获取随机字母数组 测试
//        Factory_getRandomCharList();

        //------------------
        // XykApi.SystemUtils_
        //------------------
        // 方法id [07]001 获取当前操作系统信息 测试
//        SystemUtils_getOSInfo();

        //------------------
        // XykApi.FileUtils_
        //------------------
        // 方法id [08]001 递归压缩文件夹 测试
        FileUtils_dirToZip();


    }
    /**
     * XykApi.模板 xxx
     * */
    public static void 模板(){
        System.out.println("-------模板 测试--------");
        String str;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        int num = 1;

        str = XykApi.DATE_PATTERN; // 1
        System.out.println("["+num+++"] "+str);

        System.out.println("------------------------------");
    }

    /**
     * [XykApi.MathUtils]
     * */

    /**
     * XykApi.MathUtils_BigDecimal_sum 高精度计算两数之和 num1+num2 的方法 测试
     * */
    public static void MathUtils_BigDecimal_sum() throws ParseException {
        System.out.println("-------高精度计算两数之和 num1+num2 的方法 测试--------");
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        int num = 1;
        double num1 = BigDecimal.valueOf(2.58678683344533455376786).doubleValue();
        double num2 = BigDecimal.valueOf(5242.5867868634343543576786).doubleValue();
        BigDecimal result;
        result = XykApi.MathUtils_BigDecimal_sum(num1,num2, MathContext.DECIMAL32); // 1
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_sum(num1,num2, MathContext.UNLIMITED); // 2
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_sum(num1,num2,null); // 3
        System.out.println("["+num+++"] "+result);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.MathUtils_BigDecimal_subtract 高精度计算两数之差 num1-num2 的方法 测试
     * */
    public static void MathUtils_BigDecimal_subtract() throws ParseException {
        System.out.println("-------高精度计算两数之差 num1-num2 的方法 测试--------");
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        int num = 1;
        double num1 = BigDecimal.valueOf(2.78658678655383344533455376786).doubleValue();
        double num2 = BigDecimal.valueOf(5242.5867868634343543576786).doubleValue();
        BigDecimal result;
        result = XykApi.MathUtils_BigDecimal_subtract(num1,num2, MathContext.DECIMAL32); // 1
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_subtract(num1,num2, MathContext.UNLIMITED); // 2
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_subtract(num1,num2,null); // 3
        System.out.println("["+num+++"] "+result);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.MathUtils_BigDecimal_multiply 高精度计算两数之积 num1*num2 的方法 测试
     * */
    public static void MathUtils_BigDecimal_multiply() throws ParseException {
        System.out.println("-------高精度计算两数之积 num1*num2 的方法 测试--------");
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        int num = 1;
        double num1 = BigDecimal.valueOf(2.78658678655383344533455376786).doubleValue();
        double num2 = BigDecimal.valueOf(5242.5867868634343543576786).doubleValue();
        BigDecimal result;
        result = XykApi.MathUtils_BigDecimal_multiply(num1,num2, MathContext.DECIMAL32); // 1
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_multiply(num1,num2, MathContext.UNLIMITED); // 2
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_multiply(num1,num2,null); // 3
        System.out.println("["+num+++"] "+result);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.MathUtils_BigDecimal_divide 高精度计算两数之商 num1/num2 的方法 测试
     * */
    public static void MathUtils_BigDecimal_divide(){
        System.out.println("-------高精度计算两数之商 num1/num2 的方法 测试--------");
        Double result;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        int num = 1;
        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2,null).doubleValue(); // 1
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2, RoundingMode.UP).doubleValue(); // 2
        System.out.println("["+num+++"] "+result);
        result = XykApi.MathUtils_BigDecimal_divide(1, 3, 2,RoundingMode.DOWN).doubleValue(); // 3
        System.out.println("["+num+++"] "+result);

        System.out.println("------------------------------");
    }

    /**
     * [XykApi.StringUtils]
     * */

    /**
     * XykApi.StringUtils_isPalindrome 判断一个字符串是否为回文串  的方法 测试
     * */
    public static void StringUtils_isPalindrome(){
        System.out.println("-------判断一个字符串是否为回文串 测试--------");
        Boolean flag;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        flag = XykApi.StringUtils_isPalindrome(""); // 1
        System.out.println("["+num+++"] "+flag);
        flag = XykApi.StringUtils_isPalindrome(null); // 2
        System.out.println("["+num+++"] "+flag);
        flag = XykApi.StringUtils_isPalindrome("555"); // 3
        System.out.println("["+num+++"] "+flag);
        flag = XykApi.StringUtils_isPalindrome("22"); // 4
        System.out.println("["+num+++"] "+flag);
        flag = XykApi.StringUtils_isPalindrome("321"); // 5
        System.out.println("["+num+++"] "+flag);
        flag = XykApi.StringUtils_isPalindrome("32123"); // 6
        System.out.println("["+num+++"] "+flag);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.StringUtils_distance 去除一个字符串中所有重复的字符  的方法 测试
     * */
    public static void StringUtils_distance(){
        System.out.println("-------去除一个字符串中所有重复的字符 测试--------");
        String str;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        String s = "abcdABCDabAB";
        str = XykApi.StringUtils_distance(s,true); // 1
        System.out.println("["+num+++"] "+str);
        str = XykApi.StringUtils_distance(s,false); // 2
        System.out.println("["+num+++"] "+str);
        System.out.println("------------------------------");
    }
    /**
     * XykApi.StringUtils_subStrBetween 截取一个字符串中,两个字符串之间的字符(第一个前缀和第一个后缀(从第一个前缀出现后开始计算)之间的字符)  的方法 测试
     * */
    public static void StringUtils_subStrBetween(){
        System.out.println("-------StringUtils_distance 测试--------");
        String result;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        String cookie = "JSESSIONID=6D31081A68E2D3A89AE3B5C0CC0D419C; deviceId=E360B28F-FB70-47F7-9123-87122C44AEAB; " +
                "loginUserName=zxt3012849; Hm_lvt_71f0ed158f554118b01c2f97eac16263=1634868515; " +
                "tlsysSessionId=161401e8-3678-4f55-85b2-7212bce785d6; isJump=true; " +
                "SSO_CUSTOM_LOGOUT_URL=\"https://www.zhixue.com/login.html\"; " +
                "ui=4444000020033478175; JSESSIONID=C59651352617EF07EA080E8F7124B44C";
        String preStr = "ui=";
        String sufStr = ";";
        result = XykApi.StringUtils_subStrBetween(cookie,preStr,sufStr); // 1
        System.out.println("["+num+++"] "+result);
        result = XykApi.StringUtils_subStrBetween(cookie,"loginUserName=",";"); // 2
        System.out.println("["+num+++"] "+result);
        System.out.println("------------------------------");
    }

    /**
     * [XykApi.DateUtils]
     * */

    /**
     * XykApi.DateUtils_getTimeStamp 根据时间获取时间戳 的方法 测试
     * */
    public static void DateUtils_getTimeStamp() throws ParseException {
        System.out.println("-------根据时间获取时间戳的方法 测试--------");
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        Long date;
        date = XykApi.DateUtils_getTimeStampByTime("2021-03-05",XykApi.DATE_PATTERN); // 1
        System.out.println("["+num+++"] "+date);
        date = XykApi.DateUtils_getTimeStampByTime("2021-3-5",XykApi.DATE_PATTERN); // 2
        System.out.println("["+num+++"] "+date);
        date = XykApi.DateUtils_getTimeStampByTime("2021-3-5 2:2:2",XykApi.DATE_TIME_PATTERN); // 3
        System.out.println("["+num+++"] "+date);
        date = XykApi.DateUtils_getTimeStampByTime("2021-3-5 2:2:2",XykApi.DATE_TIME_PATTERN); // 4
        System.out.println("["+num+++"] "+date);
        date = XykApi.DateUtils_getTimeStampByTime("2021-3-5 2:2:2 150",XykApi.LONG_DATE_TIME_PATTERN); // 5
        System.out.println("["+num+++"] "+date);

        System.out.println("------------------------------");
    }

    /**
     * [XykApi.CollectionUtils]
     * */

    /**
     * XykApi.CollectionUtils_deepCopyList 深复制一个集合  的方法 测试
     * */
    public static void CollectionUtils_deepCopyList(){
        System.out.println("-------深复制一个集合 测试--------");
        List<String> sourceList = Arrays.asList("d", "f", "t", "d");
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        // 第一种方法: 推荐 能直观上看出来深复制的意图
        List<String> targetList_1 = XykApi.CollectionUtils_deepCopyList(null); // 1
        System.out.println("["+num+++"] "+targetList_1);

        List<String> targetList_2 = XykApi.CollectionUtils_deepCopyList(sourceList); // 1
        System.out.println("["+num+++"] "+targetList_2);
        // 第一种方法: 不推荐,不能直观上看出来深复制的意图
//        ArrayList<String> targetList2 = new ArrayList<>(sourceList);
//        System.out.println(targetList2);
        // 第一种方法: 不推荐,不能直观上看出来深复制的意图
//        ArrayList<String> targetList3 = new ArrayList<>();
//        targetList3.addAll(sourceList);
//        System.out.println(targetList3);
        System.out.println("------------------------------");
    }

    /**
     * [XykApi.BeanUtils]
     * */

    /**
     * XykApi.BeanUtils_objToMap 对象属性转换map  的方法 测试
     * */
    public static void BeanUtils_objToMap() throws IllegalAccessException {
        System.out.println("-------对象属性转换map 测试--------");
        User user = new User();
        user.setId(1).setName(null).setChildren(new ArrayList<>());
        Map map;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        map = XykApi.BeanUtils_objToMap(user,null,null); // 1
        System.out.println("["+num+++"] "+map);
        map = XykApi.BeanUtils_objToMap(user,null,true); // 2
        System.out.println("["+num+++"] "+map);
        map = XykApi.BeanUtils_objToMap(user,1,null); // 3
        System.out.println("["+num+++"] "+map);
        map = XykApi.BeanUtils_objToMap(user,1,true); // 4
        System.out.println("["+num+++"] "+map);
        map = XykApi.BeanUtils_objToMap(user,0,null); // 5
        System.out.println("["+num+++"] "+map);
        map = XykApi.BeanUtils_objToMap(user,0,true); // 6
        System.out.println("["+num+++"] "+map);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.BeanUtils_mapToObject map转换对象  的方法 测试
     * */
    public static void BeanUtils_mapToObject() throws Exception {
        System.out.println("-------BeanUtils_mapToObject 测试--------");
        Cat cat;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",12789);
        map.put("name", 12.5);
        map.put("age", 13);
        map.put("alive", "true");
        map.put("children", Arrays.asList(new Cat().setId(5L)));
        cat = XykApi.BeanUtils_mapToObject(map, Cat.class);// 1
        System.out.println("["+num+++"] "+cat);
        System.out.println("------------------------------");
    }
    /**
     * XykApi.BeanUtils_objectValueOf 对各个valueOf的封装,避免反射注入属性时的简单错误  的方法 测试
     * */
    public static void BeanUtils_objectValueOf(){
        System.out.println("-------BeanUtils_objectValueOf 测试--------");
        String str;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        Double i1 = XykApi.BeanUtils_objectValueOf(15.1, Double.class);// 1
        System.out.println("["+num+++"] "+i1);
        Float i2 = XykApi.BeanUtils_objectValueOf(15.2, Float.class);// 2
        System.out.println("["+num+++"] "+i2);
        Double i3 = XykApi.BeanUtils_objectValueOf(i2, Double.class);// 3
        System.out.println("["+num+++"] "+i3);
        System.out.println("------------------------------");
    }

    /**
     * [XykApi.Factory]
     * */

    /**
     * XykApi.Factory_getRandomIntList 获取随机整形数组 测试
     * */
    public static void Factory_getRandomIntList(){
        System.out.println("-------获取随机整形数组 测试--------");
        List<Integer> list;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        list = XykApi.Factory_getRandomIntList(); // 1
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomIntList(0, 0, 20,true); // 2
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomIntList(2, 1, 20,true); // 3
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomIntList(-5, 5, 20,true); // 4
        System.out.println("["+num+++"] "+list);

        System.out.println("------------------------------");
    }
    /**
     * XykApi.Factory_getRandomCharList 获取随机字母数组 测试
     * */
    public static void Factory_getRandomCharList(){
        System.out.println("-------获取随机字母数组 测试--------");
        List<Character> list;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        list = XykApi.Factory_getRandomCharList(null, 10, true); // 1
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomCharList(null, 10, false); // 2
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomCharList(0, 10, true); // 3
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomCharList(0, 10, false); // 4
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomCharList(1, 10, true); // 5
        System.out.println("["+num+++"] "+list);
        list = XykApi.Factory_getRandomCharList(1, 10, false); // 6
        System.out.println("["+num+++"] "+list);

        System.out.println("------------------------------");
    }

    /**
     * [XykApi.SystemUtils]
     * */

    /**
     * XykApi.SystemUtils_getOSxx 获取操作系统信息 的方法测试
     * */
    public static void SystemUtils_getOSInfo(){
        System.out.println("-------获取操作系统信息 测试--------");
        Object str;
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;

        str = XykApi.SystemUtils_getOSName(); // 1
        System.out.println("["+num+++"] "+str);
        str = XykApi.SystemUtils_getOSVersion(); // 2
        System.out.println("["+num+++"] "+str);
        str = XykApi.SystemUtils_getHost(); // 3
        System.out.println("["+num+++"] "+str);
        str = new XykApi().port; // 4
        System.out.println("["+num+++"] "+str + " 无法在静态方法中获取端口号,请把XykApi作为bean注入指定的类后,通过bean的port属性获取端口号");

        System.out.println("------------------------------");
    }

    /**
     * [XykApi.FileUtils]
     * */

    /**
     * XykApi.FileUtils_dirToZip 递归压缩文件夹 的方法 测试
     * */
    public static void FileUtils_dirToZip() throws FileNotFoundException {
        System.out.println("-------递归压缩文件夹 的方法 测试--------");
        String str="";
        // 打印编号,用于判断哪一个输出结果对应代码中的哪一行
        Integer num = 1;
        String dirPath = "E:\\single工作区";

        XykApi.FileUtils_dirToZip(dirPath,new FileOutputStream("E:/single工作区压缩.zip"), true); // 1
        System.out.println("["+num+++"] "+str);

        System.out.println("------------------------------");
    }






}
