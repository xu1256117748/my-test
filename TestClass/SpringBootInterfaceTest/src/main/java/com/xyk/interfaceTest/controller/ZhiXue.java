package com.xyk.interfaceTest.controller;

import com.xyk.XykApi;
import com.xyk.util.HttpClientUtil;
import com.xyk.vo.JsonResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 徐亚奎
 * @date 2021-10-28 10:49
 * @Description
 */
@RestController
@RequestMapping("/zhixue/")
@CrossOrigin
public class ZhiXue {
    @GetMapping("/loginPage")
    public JsonResponse loginPage() throws ParseException {
//        String url = "https://www.zhixue.com/login.html";
//        String url = "https://www.zhixue.com/weakPwdLogin/?from=web_login";
//        HashMap<String, String> map = new HashMap<>();
//        map.put("loginName", "zxt3012849");
//        map.put("password", "123456");
//        String s = HttpClientUtil.doGet(url,map);
        getTimestamp();
        return JsonResponse.success(null);
    }

    private Long getTimestamp() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long timeInMillis = c.getTimeInMillis();
        System.out.println(timeInMillis);
        return timeInMillis;
    }

}
