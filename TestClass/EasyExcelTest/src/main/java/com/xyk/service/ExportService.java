package com.xyk.service;

import com.xyk.entity.Student;
import com.xyk.vo.FileExportVO;

import java.util.List;

/**
* @author 徐亚奎
* @date 2021-10-11 15:30
* @Description
*/public interface ExportService {
    FileExportVO exportStudentList(List<Student> studentList);
}
