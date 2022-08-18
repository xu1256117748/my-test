package com.xyk.interfaceTest.constant;

import com.xyk.interfaceTest.utils.FileUtil;

/**
 * @author 徐亚奎
 * @date 2021-10-28 09:33
 * @Description
 */
public class ZhiXueWrongTopicConstant {
    /**导出文件的默认前缀路径*/
    public static final String exportFilePreDir =
            "linux".equals(System.getProperty("os.name").toLowerCase()) ?
            "data/static/download/"+"学生错题集/" : "E:/Single工作区/export/学生错题集/";

    /**
     * 智学网请求路径
     * */
    /**
     *  1.根据[账户/用户id]获取该账户下的任教[学段-年级-班级-学科]信息
     */
    public static final String GET_ACCOUNT_TEACHING_INFO_URL =
            "https://www.zhixue.com/freshprecisionapi/studentLiteracy/newUnivDataByUserId";
    /**
     *  2.根据[班级id/学科id]获取该班级的学生列表
     */
    public static final String GET_STUDENT_LIST_URL =
            "https://www.zhixue.com/freshprecisionapi/studentLiteracy/getClazzStuDetailsNew";
    /**
     * 3.根据学生id和其他参数,获取该学生的错题集
     * */
    public static final String GET_WRONG_TOPIC_LIST_URL =
            "https://www.zhixue.com/freshprecisionapi/studentLiteracy/getWrongtopic";


    /**
     * 账户教授学科的linkedHashMap字段
     * */
    /**
     * [任教班级]字段名
     * */
    public static final String TEACHING_CLASS = "teachingClass";
    /**
     * [行政班级]字段名
     * */
    public static final String ADMINISTRATIVE_CLASS = "administrativeClass";

    /**
     * 导出最近多久时间的错题
     * */
    /**
    * 导出最近一周的错题
    * */
    public static final String RECENT_WEEK = "week";
    /**
     * 导出最近一月的错题
     * */
    public static final String RECENT_MONTH = "month";



}
