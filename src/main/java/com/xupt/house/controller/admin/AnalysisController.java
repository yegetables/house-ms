package com.xupt.house.controller.admin;

import com.xupt.house.controller.common.BaseController;
import com.xupt.house.dto.AnalysizesResult;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.entity.Category;
import com.xupt.house.entity.Post;
import com.xupt.house.service.CategoryService;
import com.xupt.house.service.FinanceRecordService;
import com.xupt.house.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/admin/analysis")
public class AnalysisController extends BaseController {

    @Autowired
    public CategoryService categoryService;


    @Autowired
    private FinanceRecordService financeRecordService;
    @Autowired
    private PostService postService;

    @GetMapping("/categorys")
    @ResponseBody
    public JsonResult categorys(@Param("isSale") Boolean isSale) {
        System.out.println("isSale=" + isSale);
        AnalysizesResult analysizesResult = new AnalysizesResult();
        analysizesResult.setCategorys(categoryService.findAll());
        int count = analysizesResult.getCategorys().size();
        List<Double> scores = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            scores.add((double) (100 / count));
//        }
        for (Category category : analysizesResult.getCategorys()) {
            Post post = new Post();
            post.setCateId(category.getId());
            if (isSale) {
                post.setPostStatus(1);//已租出
            } else {
                post.setPostStatus(0);//未租出
            }
            List<Post> posts = postService.findPostByCondition(post);
            scores.add(posts.size() * 1.0);
        }

        analysizesResult.setScores(scores);
        System.out.println(JsonResult.error("ss", analysizesResult));
        return JsonResult.success("success", analysizesResult);
    }
}
