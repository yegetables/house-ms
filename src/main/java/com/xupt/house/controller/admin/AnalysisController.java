package com.xupt.house.controller.admin;

import com.xupt.house.dto.AnalysizesResult;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/admin/analysis")
@Slf4j
public class AnalysisController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("/categorys")
    public JsonResult categorys() {
        AnalysizesResult analysizesResult = new AnalysizesResult();
        analysizesResult.setCategorys(categoryService.findAll());
        int count = analysizesResult.getCategorys().size();
        List<Double> scores = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            scores.set(i, (double) (100 / count));
        }
        analysizesResult.setScores(scores);
        return JsonResult.success("success", analysizesResult);
    }
}
