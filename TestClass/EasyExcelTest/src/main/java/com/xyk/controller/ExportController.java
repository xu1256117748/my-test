package com.xyk.controller;

import com.xyk.entity.Student;
import com.xyk.service.ExportService;
import com.xyk.vo.JsonResponse;
import com.xyk.vo.FileExportVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 徐亚奎
 * @date 2021-10-11 15:19
 * @Description
 */
@RestController
@RequestMapping("/export/")
@Component
public class ExportController {
    @Resource
    private ExportService exportService;
    private final static List<Student> STUDENT_LIST = new ArrayList<>();
    @GetMapping("1")
    public JsonResponse exportStudentList(){
        FileExportVO excelVO = null;
        try {
            excelVO = exportService.exportStudentList(STUDENT_LIST);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResponse.fail(null);
        }
        return JsonResponse.success(excelVO);
    }











    static {
        Integer firstStudentId = new Random().nextInt(10000)+1000;
        Integer schoolId = 1;
        Integer gradeNum = 3;
        Integer classNum = 10;
        Integer studentNum = 20;
        for (int i = 0; i< gradeNum; i++){
            Integer gradeId = 666666+i;
            String gradeName;
            if (i==0){
                 gradeName = "高一";
            }else if (i==1){
                 gradeName = "高二";
            }else {
                 gradeName = "高三";
            }
            for (int j = 0; j < classNum; j++){
                Integer classId = 6666666+i;
                String className = (i+1)+"班";
                for (int k = 0; k < studentNum; k++){
                    Student student = new Student();
                    student
                            .setId(firstStudentId++)
                            .setName(UUID.randomUUID().toString())
                            .setAge(new Random().nextInt(5)+12)
                            .setSchoolId(schoolId)
                            .setSchoolName("永城市第一高级中学")
                            .setGradeId(gradeId)
                            .setGradeName(gradeName)
                            .setClassId(classId)
                            .setClassName(className);
                    STUDENT_LIST.add(student);
                }
            }
        }
    }
}
