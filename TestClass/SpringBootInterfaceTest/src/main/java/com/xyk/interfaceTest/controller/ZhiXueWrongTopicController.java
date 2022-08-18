package com.xyk.interfaceTest.controller;

import com.xyk.interfaceTest.domain.*;
import com.xyk.interfaceTest.service.impl.ZhiXueWrongTopicServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-10-21 16:30
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/wrongTopic")
@Slf4j
@Api(tags = "智学网错题导出")
public class ZhiXueWrongTopicController {
    @Resource
    private ZhiXueWrongTopicServiceImpl zhiXueWrongTopicService;

    @ApiOperation("获取授课班级")
    @GetMapping("/getTeachingInfo")
    public ZhiXueCommonRes getTeachingInfo(String cookie,TeachingInfoReq req){
        TeachingInfo teachingInfo = null;
        try {
             teachingInfo =  zhiXueWrongTopicService.getTeachingInfo(cookie,req);
        }catch (SocketTimeoutException e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!cookie已失效,请重新输入!").setResult("cookie已失效,请重新输入!");
        }catch (Exception e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!").setResult(e.toString());
        }
        return new ZhiXueCommonRes().setResult(teachingInfo).setCode(200).setInfo("查询账户授课班级成功!");
    }

    /**
     * 获取学生列表
     * */
    @GetMapping("/getStudentList")
    public ZhiXueCommonRes getStudentList(String cookie){
        List<Map<String, List<StudentInfo>>> studentList = null;
        try {
            // 当前先定为查询授课班级(不加行政班级)所有学生的信息
            List<PhaseInfo> phaseInfoList = zhiXueWrongTopicService.getTeachingInfo(cookie,null).getTeachingClass();
            studentList = zhiXueWrongTopicService.getStudentList(cookie, phaseInfoList);
        }catch (SocketTimeoutException e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!cookie已失效,请重新输入!").setResult("cookie已失效,请重新输入!");
        }catch (Exception e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!").setResult(e.toString().substring(e.toString().indexOf(":")+2));
        }
        return new ZhiXueCommonRes().setResult(studentList).setCode(200).setInfo("查询班级学生成功!");
    }

    /**
     * 导出学生错题
     * */
    @GetMapping("/exportStudentWrongTopic")
    public ZhiXueCommonRes exportStudentWrongTopic(String cookie,TeachingInfoReq req) throws Exception {
        List<ExportWrongTopicVO> vos = null;
        try {
            vos =  zhiXueWrongTopicService.exportStudentWrongTopic(cookie,req);
        }catch (SocketTimeoutException e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!cookie已失效,请重新输入!").setResult("cookie已失效,请重新输入!");
        }catch (Exception e){
            return new ZhiXueCommonRes().setCode(-1).
                    setInfo("查询账户授课班级失败!").setResult(e.toString().substring(e.toString().indexOf(":")+2));
        }
        return new ZhiXueCommonRes().setResult(vos).setCode(200).setInfo("导出学生错题成功!");
    }
}
