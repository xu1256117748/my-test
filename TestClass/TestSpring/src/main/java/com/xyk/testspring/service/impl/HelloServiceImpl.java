package com.xyk.testspring.service.impl;

import com.xyk.testspring.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-23 15:21
 * @Description
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Value("${server.port:#{-1}}")
    public Integer port;

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String repeat(String content) {
        return port + " repeat say " + content;
    }


}
