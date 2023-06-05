package com.xupt.house.controller.home;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.controller.common.BaseController;
import com.xupt.house.entity.Category;
import com.xupt.house.entity.City;
import com.xupt.house.entity.Notice;
import com.xupt.house.entity.Post;
import com.xupt.house.service.CategoryService;
import com.xupt.house.service.CityService;
import com.xupt.house.service.NoticeService;
import com.xupt.house.service.PostService;
import com.xupt.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);


        City city = (City) session.getAttribute("city");
        Long cityId = city == null ? null : city.getId();
        List<Post> latestPostList = postService.getLatestPost(cityId, 6);
        model.addAttribute("latestPostList", latestPostList);

        return "home/index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);

        return "home/contact";
    }


    /**
     * 新闻列表
     *
     * @param model
     * @return
     */
    @GetMapping("/notice")
    public String noticeList(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        Page<Notice> postPage = noticeService.findAll(page);
        model.addAttribute("page", postPage);
        model.addAttribute("noticeList", postPage.getRecords());

        return "home/noticeList";
    }


    /**
     * 新闻详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/notice/{id}")
    public String noticeDetails(@PathVariable("id") Long id,
                                @RequestParam(value = "startDate", required = false) String start,
                                @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                                Model model) {

        // 新闻
        Notice notice = noticeService.get(id);
        if (notice == null) {
            return renderNotFound();
        }

        model.addAttribute("notice", notice);

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);
        return "home/notice";
    }


}