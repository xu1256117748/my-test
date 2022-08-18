package com.xyk.interfaceTest.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.security.auth.Subject;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-28 18:36
 * @Description
 */
@Data
@Accessors(chain = true)
public class ClassInfo {
    private String classId;
    private String className;
    Boolean check;
    List<SubjectInfo> subjects;

}
