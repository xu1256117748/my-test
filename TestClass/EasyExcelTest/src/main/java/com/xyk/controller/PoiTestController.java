package com.xyk.controller;

import com.xyk.service.PoiTestService;
import com.xyk.vo.JsonResponse;
import com.xyk.vo.excel.ExcelExportRes;
import com.xyk.vo.excel.PoiExportVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 徐亚奎
 * @date 2021-10-19 11:01
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/poi/style/")
public class PoiTestController {
     @Resource
     private PoiTestService poiService;

     /**
      * 生成[颜色&填充模式]的交叉的样式表,导出路径为PoiTool的默认路径
      * */
    @GetMapping("color")
    public JsonResponse color() throws IOException {
        ExcelExportRes color;
        try {
             color = poiService.colorTest();
        }catch (Exception e){
            return JsonResponse.fail(e.getMessage());
        }
        return JsonResponse.success(color);
    }
    /**
     * 已固定使用中型线条
     * */
    @GetMapping("border")
    public JsonResponse border(@RequestParam Integer fillcolorIndex, @RequestParam Integer bordercolorIndex) throws IOException {
        ExcelExportRes border;
        try {
            System.out.println("fillcolorIndex:"+fillcolorIndex);
            System.out.println("bordercolorIndex:"+bordercolorIndex);
            System.out.println();
            border = poiService.borderTest(fillcolorIndex, bordercolorIndex);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResponse.fail(e);
        }
        return JsonResponse.success(border);
    }
}
