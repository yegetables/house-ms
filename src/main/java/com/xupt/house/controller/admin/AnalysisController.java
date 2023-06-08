package com.xupt.house.controller.admin;

import com.xupt.house.controller.common.BaseController;
import com.xupt.house.dto.AnalysizesResult;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/admin/analysis")
public class AnalysisController extends BaseController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("/categorys")
    @ResponseBody
    public JsonResult categorys() {
        System.out.println("hello world");
        AnalysizesResult analysizesResult = new AnalysizesResult();
        analysizesResult.setCategorys(categoryService.findAll());
        int count = analysizesResult.getCategorys().size();
        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            scores.add((double) (100 / count));
        }
        analysizesResult.setScores(scores);
        return JsonResult.success("success", analysizesResult);
    }
}
