package com.xyk.interfaceTest.service.impl;

import com.xyk.XykApi;
import com.xyk.interfaceTest.constant.ZhiXueWrongTopicConstant;
import com.xyk.interfaceTest.domain.*;
import com.xyk.interfaceTest.service.ZhiXueWrongTopicService;
import com.xyk.interfaceTest.utils.FileUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-10-29 08:20
 * @Description
 */
@Service
public class ZhiXueWrongTopicServiceImpl implements ZhiXueWrongTopicService {
    @Resource
    RestTemplate restTemplate;
    /**
     * 查询账户任教班级
     * @param cookie
     * @param req 请求参数,用于指定条件查询
     * */
    @Override
    public TeachingInfo getTeachingInfo(String cookie,TeachingInfoReq req) throws Exception {
        req = req == null ? new TeachingInfoReq() : req;
        // step1 拼接链接,获取相应结果
        String userId = getAccountId(cookie);
        if (!StringUtils.hasLength(userId)){
            try {
                throw new Exception("cookie未输入或格式错误,请重新输入!");
            }catch (Exception e){
                throw new Exception("cookie未输入或格式错误,请重新输入!");
            }

        }
        String url = ZhiXueWrongTopicConstant.GET_ACCOUNT_TEACHING_INFO_URL
                +"?userId="+userId+"&t="+getTimestamp();
        HttpHeaders headers = getHttpHeaders(cookie);
        ZhiXueCommonRes commonRes = restTemplate.exchange(url,
                HttpMethod.GET, new HttpEntity<>(null,headers), ZhiXueCommonRes.class).getBody();
        // step2 处理相应结果,返回前段
            // 获取账户班级信息(包括行政班级和任课班级)
        Map<String,Object> teachingInfoMap = (Map<String,Object>) commonRes.getResult();
            // 创建响应体对象,处理结果赋值到响应体中
        TeachingInfo teachingInfo = new TeachingInfo();
        if (req.getTeachingOrAdministrative() == null ||req.getTeachingOrAdministrative() == 0
                || req.getTeachingOrAdministrative() == 2){
            teachingInfo.setTeachingClass(getPhaseInfoList((List<Map<String,Object>>)
                    teachingInfoMap.get(ZhiXueWrongTopicConstant.TEACHING_CLASS),req));
        }
        if (req.getTeachingOrAdministrative() == 1 || req.getTeachingOrAdministrative() == 2){
            teachingInfo.setAdministrativeClass(getPhaseInfoList((List<Map<String,Object>>)
                    teachingInfoMap.get(ZhiXueWrongTopicConstant.ADMINISTRATIVE_CLASS),req));
        }
        return teachingInfo;
    }

    @Override
    public List<Map<String, List<StudentInfo>>> getStudentList(String cookie, List<PhaseInfo> phaseInfoList) throws Exception {
        List<Map<String, List<StudentInfo>>> studentInfoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(phaseInfoList)){
            // 用于测试,实际直接return
            phaseInfoList = getTeachingInfo(cookie, new TeachingInfoReq()).getTeachingClass();
//            return studentInfoList;
        }
        for(int i = 0;i<phaseInfoList.size();i++){
            PhaseInfo phaseInfo = phaseInfoList.get(i);
            List<GradeInfo> gradeInfoList = phaseInfo.getGrades();
            if(CollectionUtils.isEmpty(gradeInfoList)){
                continue;
            }
            for (int j = 0;j<gradeInfoList.size();j++){
                GradeInfo gradeInfo = gradeInfoList.get(j);
                List<ClassInfo> classInfoList = gradeInfo.getClasses();
                if(CollectionUtils.isEmpty(classInfoList)){
                    continue;
                }
                for (int k = 0;k<classInfoList.size(); k++){
                    ClassInfo classInfo = classInfoList.get(k);
                    List<SubjectInfo> subjectInfoList = classInfo.getSubjects();
                    if(CollectionUtils.isEmpty(subjectInfoList)){
                        continue;
                    }
                    for(int m = 0;m<subjectInfoList.size();m++){
                        SubjectInfo subjectInfo = subjectInfoList.get(m);
                        // 根据参数进行查询
                        List<StudentInfo> studentInfos = getStudentInfoList(cookie,
                                classInfo.getClassId(),subjectInfo.getSubjectId());
                        Map<String, List<StudentInfo>> map = new HashMap<>();
                        map.put(phaseInfo.getPhaseName()+"-"
                                +gradeInfo.getGradeId()+"-"
                                +classInfo.getClassId()+"-"
                                +subjectInfo.getSubjectId()+"-"
                                +";"
                                +phaseInfo.getPhaseName()+"-"
                                +gradeInfo.getGradeName()+"-"
                                +classInfo.getClassName()+"-"
                                +subjectInfo.getSubjectName()+"-"
                                ,studentInfos);
                        studentInfoList.add(map);
                    }
                }
            }
        }
        return studentInfoList;
    }

    @Override
    public List<ExportWrongTopicVO> exportStudentWrongTopic(String cookie, TeachingInfoReq req) throws Exception {
        req = req == null ? new TeachingInfoReq() : req;
        if (!StringUtils.hasLength(req.getRecentTime())){
            req.setRecentTime(ZhiXueWrongTopicConstant.RECENT_MONTH);// 默认导出最近一周的错题
        }
        // 根据cookie和req查询要导出的任教班级
        List<PhaseInfo> teachingClass = getTeachingInfo(cookie,req).getTeachingClass();
        // 根据要导出的任教班级查询要导出的学生
        List<Map<String, List<StudentInfo>>> studentList = getStudentList(cookie, teachingClass);
        // 根据学生,获取其错题集并导出
        List<ExportWrongTopicVO> vos = new ArrayList<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        for(int i = 0;i<studentList.size();i++){
            Map<String, List<StudentInfo>> map = studentList.get(i);
            String key = map.keySet().toArray()[0].toString();
            String[] IdStr = key.split(";")[0].split("-");
            List<StudentInfo> studentInfos = (List<StudentInfo>) map.values().toArray()[0];
            String teacherId = getAccountId(cookie);
            for(StudentInfo studentInfo : studentInfos){
                // 如果用户选中了导出学生则导出指定学生,否则导出全部学生
                if (!CollectionUtils.isEmpty(req.getStudentIdList()) &&
                        !req.getStudentIdList().contains(studentInfo.getStuId())){
                    continue;
                }
                // 封装查询错题所需的参数
                studentInfo.setTeacherId(teacherId);
                studentInfo.setClassId(IdStr[2]);
                studentInfo.setSubjectCode(IdStr[3]);
                if (StringUtils.hasLength(req.getStartTime())){
                    studentInfo.setStartTime(req.getStartTime());
                    studentInfo.setEndTime(req.getEndTime());
                }else{
                    Map<String, String> timeMap = getStartTimeAndEndTime(cookie, req.getRecentTime());
                    studentInfo.setStartTime(timeMap.get("startTime"));
                    studentInfo.setEndTime(timeMap.get("endTime"));
                }
                // 获取一个学生的错题集
                List<WrongTopic> wrongTopicList = getWrongTopicList(cookie, studentInfo);
                if (!CollectionUtils.isEmpty(wrongTopicList)){
                   String path = key.split(";")[1]+studentInfo.getStuName();
                    // 导出一个学生的错题集
                    vos.add(exportWrongTopicListHtml(uuid+"-"+date+"-"+path, wrongTopicList));
                }
            }
        }

        String zipFileSaveAllPath = ZhiXueWrongTopicConstant.exportFilePreDir+"/"+uuid+"/"+date+"错题集.zip";
        String zipFileDownloadUrl = ZhiXueWrongTopicConstant.exportFilePreDir+"/"+uuid+"/"+date+"错题集.zip";
        File zipFile = new File(zipFileSaveAllPath);
        if (!zipFile.getParentFile().exists()) {
            zipFile.getParentFile().mkdirs();
        }
        // 对导出的错题集进行压缩
        XykApi.FileUtils_dirToZip(ZhiXueWrongTopicConstant.exportFilePreDir+"/"+uuid+"/"+date, new FileOutputStream(zipFile), true);
        vos.add(0,new ExportWrongTopicVO().setFileExportPath(zipFileDownloadUrl));
        return vos;
    }

    /**
     * 获取字符串中的账户id
     * */
    private static String getAccountId(String cookie){
        if (cookie==null || !cookie.contains("ui=")){
            return "";
        }
        return subStr(cookie.endsWith(";") ? cookie : cookie+";","ui=",";");
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
        return str.substring(startIndex+preStr.length(),endIndex+1);
    }
    /**
     * 获取当前时间的时间戳
     * */
    private Long getTimestamp() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long timeInMillis = c.getTimeInMillis();
        return timeInMillis;
    }
    /**
     * 获取http请求的通用请求头
     * */
    private HttpHeaders getHttpHeaders(String cookie){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie",cookie);
        headers.add("Accept","application/json, text/plain, */*");
        headers.add("Accept-Encoding","gzip, deflate, br");
        headers.add("Accept-Language","zh-CN,zh;q=0.9");
        headers.add("Authorization","freshprecision");
        headers.add("Cache-Control","no-cache");
        headers.add("Connection","keep-alive");
        headers.add("Host","www.zhixue.com");
        headers.add("Pragma","no-cache");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        return headers;
    }
    /**
     * 将多层map结构的学段班级集合,转换为List<PhaseInfo>学段集合
     * 仅适用于行政班级/授课班级下的学段集合
     * @param phaseInfos 学段集合(内封装[年级-班级-学科]结构)
     * @param req 请求参数(指定年级,班级,学科,学生进行查询)
     * */
    private List<PhaseInfo> getPhaseInfoList(List<Map<String,Object>> phaseInfos,TeachingInfoReq req){
        List<PhaseInfo> phaseInfoList = new ArrayList<>(); // 用于存储学段集合
        if (CollectionUtils.isEmpty(phaseInfos)){
            return phaseInfoList;
        }
        for(int i =0; i<phaseInfos.size();i++){
            
            Map<String,Object> phaseInfoMap = phaseInfos.get(i);
            String phaseName = phaseInfoMap.get("phaseName").toString();
            if (!CollectionUtils.isEmpty(req.getPhaseList())
                   && !req.getPhaseList().contains(phaseName)){ // 只查询指定学段,为null时查询全部学段
                continue;
            }
            PhaseInfo phaseInfo = new PhaseInfo();
            phaseInfo.setPhaseCode(phaseInfoMap.get("phaseCode").toString());
            phaseInfo.setPhaseName(phaseInfoMap.get("phaseName").toString());
            List<Map<String,Object>> gradeInfos = (List<Map<String,Object>>)phaseInfoMap.get("grades");
            List<GradeInfo> gradeInfoList = new ArrayList<>(); // 用于存储年级集合
            if (CollectionUtils.isEmpty(gradeInfos)){
                phaseInfo.setGrades(null);
                continue;
            }
            for (int j = 0; j <gradeInfos.size(); j++){
                Map<String,Object> gradeInfoMap = gradeInfos.get(j);
                String gradeId = gradeInfoMap.get("gradeId").toString();
                if(!CollectionUtils.isEmpty(req.getGradeIdList())
                        && !req.getGradeIdList().contains(gradeId)){ // 只查询指定年级,为null时查询全部年级
                    continue;
                }
                GradeInfo gradeInfo = new GradeInfo();
                gradeInfo.setGradeId(gradeId);
                gradeInfo.setGradeName(gradeInfoMap.get("gradeName").toString());
                gradeInfo.setCheck(Boolean.valueOf(gradeInfoMap.get("check").toString()));
                List<Map<String,Object>> classInfos = (List<Map<String,Object>>)gradeInfoMap.get("classes");
                List<ClassInfo> classInfoList = new ArrayList<>(); // 用于存储班级集合
                if (CollectionUtils.isEmpty(gradeInfos)){
                    gradeInfo.setClasses(null);
                    continue;
                }
                for(int k = 0; k< classInfos.size(); k++){
                    Map<String,Object> classInfoMap = classInfos.get(k);
                    String classId = classInfoMap.get("classId").toString();
                    if(!CollectionUtils.isEmpty(req.getClassIdList())
                            && !req.getClassIdList().contains(classId)){ // 只查询指定班级,为null时查询全部班级
                        continue;
                    }
                    ClassInfo classInfo = new ClassInfo();
                    classInfo.setClassId(classId);
                    classInfo.setClassName(classInfoMap.get("className").toString());
                    classInfo.setCheck(Boolean.valueOf(classInfoMap.get("check").toString()));
                    List<Map<String,Object>> subjectInfos = (List<Map<String,Object>>) classInfoMap.get("subjects");
                    List<SubjectInfo> subjectInfoList = new ArrayList<>();
                    if (CollectionUtils.isEmpty(subjectInfos)){
                        classInfo.setSubjects(null);
                        continue;
                    }
                    for(int m = 0;m<subjectInfos.size();m++){
                        Map<String,Object> subjectInfoMap = subjectInfos.get(m);
                        String subjectId = subjectInfoMap.get("subjectId").toString();
                        if(!CollectionUtils.isEmpty(req.getSubjectIdList())
                                && !req.getSubjectIdList().contains(subjectId)){ // 只查询指定班级,为null时查询全部班级
                            continue;
                        }
                        SubjectInfo subjectInfo = new SubjectInfo();
                        subjectInfo.setSubjectId(subjectId);
                        subjectInfo.setSubjectName(subjectInfoMap.get("subjectName").toString());
                        subjectInfo.setCheck(Boolean.valueOf(subjectInfoMap.get("check").toString()));
                        subjectInfoList.add(subjectInfo);
                    }
                    classInfo.setSubjects(subjectInfoList);
                    classInfoList.add(classInfo);
                }
                gradeInfo.setClasses(classInfoList);
                gradeInfoList.add(gradeInfo);
            }
            phaseInfo.setGrades(gradeInfoList);
            phaseInfoList.add(phaseInfo);
        }
        return phaseInfoList;
    }
    /**
     * 获取学生列表
     * @param cookie
     * @param classId
     * @param subjectId
     * */
    private List<StudentInfo> getStudentInfoList(String cookie, String classId, String subjectId) throws Exception {
        String url=ZhiXueWrongTopicConstant.GET_STUDENT_LIST_URL +
                "?classId="+classId+"&subjectId="+subjectId+
                "&timeId=halfYear&classFlag=1&t="+getTimestamp();
        HttpHeaders headers = getHttpHeaders(cookie);
        HttpEntity httpEntity=new HttpEntity<>(null,headers);
        ResponseEntity<ZhiXueCommonRes> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ZhiXueCommonRes.class);
        ZhiXueCommonRes zhiXueCommonRes = exchange.getBody();
        List<Map<Object,Object>> studentInfoList = (List<Map<Object,Object>>) zhiXueCommonRes.getResult();
        ArrayList<StudentInfo> studentList = new ArrayList<>();
        for (int i = 0;i<studentInfoList.size();i++) {
            Map<Object, Object> map = studentInfoList.get(i);
            StudentInfo studentInfo = XykApi.BeanUtils_mapToObject(map, StudentInfo.class);
            studentList.add(studentInfo);
        }
        return studentList;
    }
    /**
     * 获取一个学生的错题集
     * @param cookie cookie
     * @param student 学生信息
     * */
    private List<WrongTopic> getWrongTopicList(String cookie, StudentInfo student) throws Exception {

        // step1 拼接请求路径
        StringBuilder url = new StringBuilder(ZhiXueWrongTopicConstant.GET_WRONG_TOPIC_LIST_URL);
        url.append("?teacherId="+student.getTeacherId());
        url.append("&studentId="+student.getStuId()); // 1500000100111786668
        url.append("&studentName="+student.getStuName()); // %E6%9C%B1%E5%98%89%E7%90%A6
        url.append("&pageSize="+100);
        url.append("&pageNo="+0);
        url.append("&beginTime="+student.getStartTime());
        url.append("&endTime="+student.getEndTime());
        url.append("&subjectCode="+student.getSubjectCode());
        url.append("&checkCorrected="+"all");
        url.append("&rate="+"0-1");
        url.append("&topicSetIdStr="+"");// a90dbab1-1091-4849-b48a-1656a34b59b5,2773358f-fe44-49be-80f1-559d71206fc4
        url.append("&classId="+student.getClassId());
        url.append("&t="+getTimestamp());
        // step2 发起请求,获取响应体
        HttpHeaders headers = getHttpHeaders(cookie);
        HttpEntity httpEntity=new HttpEntity<>(null,headers);
        ResponseEntity<ZhiXueCommonRes> exchange = restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, ZhiXueCommonRes.class);
        ZhiXueCommonRes zhiXueCommonRes = exchange.getBody();
        // step3 处理响应体数据
        Map<String,Object> result = (Map<String, Object>) zhiXueCommonRes.getResult();
        List<Map<Object, Object>> valueList = (List<Map<Object, Object>>) result.get("list");
        List<WrongTopic> wrongTopicList = new ArrayList<>();
        for (int i =0;i<valueList.size();i++){
            Map<Object, Object> map = valueList.get(i);
            WrongTopic wrongTopic = XykApi.BeanUtils_mapToObject(map, WrongTopic.class);
            wrongTopicList.add(wrongTopic);
        }
        return wrongTopicList;
    }
    /**
     * 导出一个学生的错题集,生成html页面
     * */
    private ExportWrongTopicVO exportWrongTopicListHtml(String path, List<WrongTopic> wrongTopicList) throws Exception {
        ExportWrongTopicVO vo = new ExportWrongTopicVO();
        String[] split = path.split("-");
        String fileName = split[6]+"的错题集";
        String fileType = ".html";
        String fileAllName = fileName+fileType;
        String fileExportDir = ZhiXueWrongTopicConstant.exportFilePreDir+split[0]+"/"+split[1]+"/"+
                split[2]+"/"+split[3]+"/"+split[4]+"/"+split[5]+"/";
        String fileExportAllPath = fileExportDir + fileAllName;

        StringBuilder sb = new StringBuilder("");
        for(int i =0;i<wrongTopicList.size();i++){
            sb.append(wrongTopicList.get(i).getContentHtml());
            sb.append("<br/>");
        }
        // 拼接完整的html页面代码
        String htmlContent = htmlAppend(sb.toString(), fileName);
        // 导出html
        FileUtil.htmlContentToHtml(htmlContent,fileExportAllPath);
        return vo.setFileAllName(fileAllName).setFileName(fileName)
                .setFileType(fileType).setFileExportPath(fileExportAllPath);
    }
    /**
     * html片段拼接为完整的html
     * */
    private String htmlAppend(String htmlBody,String htmlTitle){
        String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        StringBuilder sb = new StringBuilder(docType);
        sb.append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">");
        sb.append("<head>");
        sb.append("<title>"+htmlTitle+"</title>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></meta>");
        sb.append("<script type=\"text/javascript\" src=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>");
        sb.append("<script type=\"text/x-mathjax-config\">\n" +
                "MathJax.Hub.Config({\n" +
                "    tex2jax: {\n" +
                "        inlineMath: [ ['$','$'], [\"\\\\(\",\"\\\\)\"] ],\n" +
                "        displayMath: [ ['$$','$$'], [\"\\\\[\",\"\\\\]\"] ]\n" +
                "    }\n" +
                "});\n" +
                "</script>\n");
        // inlineMath识别的单行内的数学公式,我们可以通过$ ... $或\( ... \)去识别这种数学公式
        //displayMath就是识别整个独立段落的数学公式并且居中显示,我们可以通过$$ ... $$或\[ ... ])去识别这种数学公式
        sb.append("</head>");
        sb.append("<body lang=\"ZH-CN\" style=\"text-justify-trim:punctuation; " +
                "font-family: SimSun; font-size:small;\" >");
        sb.append("<br/>");

        sb.append(htmlBody);

        sb.append("</body></html>");
        return sb.toString();
    }
    /**
     * 查询最近一段时间的开始和结束时间
     * */
    public Map<String, String> getStartTimeAndEndTime(String cookie,String recentTime) throws ParseException {
        StringBuilder url = new StringBuilder("https://www.zhixue.com/freshprecisionapi/subjectSituation/getTime?");
        url.append("timeArgs="+recentTime);
        url.append("&t="+getTimestamp());
        // step2 发起请求,获取响应体
        HttpHeaders headers = getHttpHeaders(cookie);
        HttpEntity httpEntity=new HttpEntity<>(null,headers);
        ResponseEntity<ZhiXueCommonRes> exchange = restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, ZhiXueCommonRes.class);
        ZhiXueCommonRes zhiXueCommonRes = exchange.getBody();
        // step3 处理响应体数据
        Map<String,Object> result = (Map<String, Object>) zhiXueCommonRes.getResult();
        Map<String, String> map = new HashMap<>();
        map.put("startTime",result.get("year")+"-"+result.get("month")+"-"+result.get("day"));
        map.put("endTime",result.get("currentYear")+"-"+result.get("currentMonth")+"-"+result.get("currentDay"));
        return map;

    }
}
