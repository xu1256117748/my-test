package com.xyk.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 徐亚奎
 * @date 2021-10-11 15:34
 * @Description
 */
@Data
@Accessors(chain = true)
@Component
public class Student {
    @ExcelProperty
    private Integer id;
    @ExcelProperty
    private String name;
    @ExcelProperty
    private Integer age;
    private Integer schoolId;
    @ExcelProperty
    private String schoolName;
    private Integer gradeId;
    @ExcelProperty
    private String gradeName;
    private Integer classId;
    @ExcelProperty
    private String className;

}
