package com.xyk.testspring.controller;

import com.xyk.testspring.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-23 14:07
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/spring/hello/")
@Api(tags = "HelloController")
public class HelloController {
    @Resource
    HelloService helloService;

    @Value("${server.port:#{-1}}")
    public Integer port;

    @GetMapping
    public String Hello(){
        return helloService.hello();
    }
    @GetMapping("/repeat/{content}")
    public String Repeat(@PathVariable String content){

        String repeat = helloService.repeat(content);

        return repeat;
    }

}
