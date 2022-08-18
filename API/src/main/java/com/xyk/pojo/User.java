package com.xyk.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;
import java.lang.annotation.Annotation;

/**
 * @author 徐亚奎
 * @date 2021/7/9 9:27
 */
@Data
@Accessors(chain = true)
public class User  {
    public Integer id;
    private String name;
    private List<User> children;
}
