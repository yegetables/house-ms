package com.xupt.house.controller.home;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.controller.common.BaseController;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.entity.*;
import com.xupt.house.enums.PostStatusEnum;
import com.xupt.house.service.*;
import com.xupt.house.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class FrontPostController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @Autowired
    private CityService cityService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    /**
     * 房屋列表
     *
     * @param model
     * @return
     */
    @GetMapping("/post")
    public String postList(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                           @RequestParam(value = "size", defaultValue = "6") Integer pageSize,
                           @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                           @RequestParam(value = "order", defaultValue = "desc") String order,
                           @RequestParam(value = "postTitle", defaultValue = "") String postTitle,
                           @RequestParam(value = "cityId", defaultValue = "-1") Long cityId,
                           @RequestParam(value = "cateId", defaultValue = "-1") Long cateId,
                           @RequestParam(value = "area", defaultValue = "") String area,
                           @RequestParam(value = "price", defaultValue = "") String price,
                           @RequestParam(value = "status", defaultValue = "-1") Integer status,
                           HttpSession session,
                           Model model) {
        Post condition = new Post();

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);


        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);

        model.addAttribute("postCount", postService.count(null));

        try {
            if (StringUtils.isNotEmpty(price)) {
                String[] priceArr = price.split("-");
                if (priceArr.length == 2) {
                    condition.setMinPrice(Integer.valueOf(priceArr[0]));
                    condition.setMaxPrice(Integer.valueOf(priceArr[1]));
                }
            }
            if (StringUtils.isNotEmpty(area)) {
                String[] areaArr = area.split("-");
                if (areaArr.length == 2) {
                    condition.setMinArea(Integer.valueOf(areaArr[0]));
                    condition.setMaxArea(Integer.valueOf(areaArr[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 查询日期列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        condition.setPostTitle(postTitle);
        condition.setPostStatus(status);
        condition.setCateId(cateId);
        condition.setCityId(cityId);

        Page<Post> postPage = postService.findPostByCondition(condition, page);
        model.addAttribute("page", postPage);
        model.addAttribute("postTitle", postTitle);
        model.addAttribute("cityId", cityId);
        model.addAttribute("cateId", cateId);
        model.addAttribute("status", status);
        model.addAttribute("area", area);
        model.addAttribute("price", price);

        // 侧边栏
        model.addAttribute("onCount", postService.countByStatus(PostStatusEnum.ON_SALE.getCode()));
        model.addAttribute("offCount", postService.countByStatus(PostStatusEnum.OFF_SALE.getCode()));

        if (cityId != null && cityId != -1) {
            City city = cityService.get(cityId);
            if (city != null) {
                session.setAttribute("city", city);
            }
        } else {
            session.removeAttribute("city");
        }
        return "home/postList";
    }


    /**
     * 房屋详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/post/{id}")
    public String postDetails(@PathVariable("id") Long id,
                              @RequestParam(value = "startDate", required = false) String start,
                              @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                              HttpSession session,
                              Model model) {

        // 房屋
        Post post = postService.get(id);
        if (post == null) {
            return renderNotFound();
        }
        // 分类和城市
        Category category = categoryService.get(post.getCateId());
        City city = cityService.get(post.getCityId());
        User user = userService.get(post.getUserId());

        post.setCategory(category);
        post.setCity(city);
        post.setUser(user);
        model.addAttribute("post", post);

        boolean allowEdit = getLoginUser() != null && (loginUserIsAdmin() || Objects.equals(post.getUserId(), getLoginUserId()));
        model.addAttribute("allowEdit", allowEdit);

        String[] imgUrlList = post.getImgUrl().split(",");
        model.addAttribute("imgUrlList", imgUrlList);

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);

        City citySession = (City) session.getAttribute("city");
        Long cityId = citySession == null ? null : citySession.getId();
        List<Post> latestPostList = postService.getLatestPost(cityId, 6);
        model.addAttribute("latestPostList", latestPostList);

        // 可以考虑优化下，暂时没有时间优化
        List<Post> unionRentPost = postService.getUnionRentPost(post);
        List<Order> orderList = new ArrayList<>();
        for (Post temp : unionRentPost) {
            Order order = orderService.findByPostId(temp.getId());
            if (order == null) {
                order = new Order();
            } else {
                order.setUser(userService.get(order.getUserId()));
            }
            order.setPost(temp);
            orderList.add(order);
        }
        model.addAttribute("orderList", orderList);
        return "home/post";
    }


    /**
     * 处理审核举报的请求
     *
     * @param postId 举报编号
     * @return 重定向到/admin/report
     */
    @PostMapping(value = "/report")
    @ResponseBody
    public JsonResult report(@RequestParam("postId") Long postId,
                             @RequestParam("type") String type,
                             @RequestParam("content") String content) {
        User user = getLoginUser();
        if (user == null) {
            return JsonResult.error("用户未登录");
        }

        Post post = postService.get(postId);
        if (post == null) {
            return JsonResult.error("房屋不存在");
        }
        if (Objects.equals(post.getUserId(), user.getId())) {
            return JsonResult.error("不能举报自己发布的房屋");
        }

        Report checkReport = reportService.getNotCheckReport(postId, user.getId());
        if (checkReport != null) {
            return JsonResult.error("您已经举报过了，请耐心等待管理员处理");
        }

        Report report = new Report();
        report.setStatus(0);
        report.setType(type);
        report.setContent(content);
        report.setPostId(postId);
        report.setUserId(user.getId());
        report.setCreateTime(new Date());
        report.setUpdateTime(new Date());
        report.setCreateBy(user.getUserName());
        report.setUpdateBy(user.getUserName());
        reportService.insert(report);
        return JsonResult.success("举报成功，请耐心等待管理员处理");
    }

}
