package com.xyk.interfaceTest.controller;

import com.xyk.interfaceTest.domain.entity.WxCreateOrderRequest;
import com.xyk.interfaceTest.utils.FileUtil;
import com.xyk.pojo.StaticResource;
import com.xyk.pojo.User;
import com.xyk.util.CommonStatusEnum;
import com.xyk.vo.JsonResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-08-10 10:26
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
@Api(tags = "j")
public class HelloWorld {
    String content = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\"><mo stretchy=\"false\">(</mo><mn>2</mn><mo>⩽</mo><mi>x</mi><mi>n</mi><mo>⩽</mo><mn>6</mn><mo stretchy=\"false\">)</mo></math>";
//        String path = "E:/Single工作区/export/学生错题集/pdf/test.pdf";
//        String htmlFile = "E:\\single工作区\\export\\学生错题集\\html\\周科宇的错题集.html";
//        String PdfFile = "E:\\single工作区\\export\\学生错题集\\pdf\\周科宇的错题集.pdf";
//        FileUtil.html2pdf(htmlFile,PdfFile);

    @GetMapping("/helloWorld")
    public JsonResponse helloWorld() throws Exception {
        String msg = "Hello World";
//
        return JsonResponse.success(msg)
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMsg(CommonStatusEnum.SUCCESS.getMsg());
    }
    @GetMapping("/getUserList")
    public JsonResponse getUserList(){
        List<User> userList = StaticResource.userList;
        System.out.println(userList);
        return JsonResponse.success(userList)
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMsg(CommonStatusEnum.SUCCESS.getMsg());
    }
    @GetMapping("/createOrder")
    public JsonResponse createOrder(WxCreateOrderRequest request){
        System.out.println(request);
        return JsonResponse.success("hello");
    }


}
