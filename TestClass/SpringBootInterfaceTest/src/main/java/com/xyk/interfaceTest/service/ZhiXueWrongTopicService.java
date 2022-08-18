package com.xyk.interfaceTest.service;

import com.xyk.interfaceTest.domain.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author 徐亚奎
 * @date 2021-10-29 08:20
 * @Description
 */
public interface ZhiXueWrongTopicService {
    TeachingInfo getTeachingInfo(String cookie, TeachingInfoReq req) throws ParseException, Exception;

    List<Map<String, List<StudentInfo>>> getStudentList(String cookie, List<PhaseInfo> phaseInfoList) throws Exception;

    List<ExportWrongTopicVO> exportStudentWrongTopic(String cookie, TeachingInfoReq req) throws Exception;
}
