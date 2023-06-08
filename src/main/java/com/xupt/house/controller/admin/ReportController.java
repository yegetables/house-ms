package com.xupt.house.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.controller.common.BaseController;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.entity.Post;
import com.xupt.house.entity.Report;
import com.xupt.house.entity.User;
import com.xupt.house.service.PostService;
import com.xupt.house.service.ReportService;
import com.xupt.house.service.UserService;
import com.xupt.house.utils.PageUtil;
import com.xupt.house.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/admin/report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /**
     * 处理后台获取举报列表的请求
     *
     * @param model model
     * @return 模板路径admin/admin_report
     */
    @GetMapping
    public String reports(Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                          @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                          @RequestParam(value = "order", defaultValue = "desc") String order,
                          @ModelAttribute SearchVo searchVo) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        Page<Report> reportPage = reportService.findAll(page);
        List<Report> reportList = reportPage.getRecords();
        for (Report report : reportList) {
            User user = userService.get(report.getUserId());
            Post post = postService.get(report.getPostId());
            report.setUser(user == null ? new User() : user);
            report.setPost(post == null ? new Post() : post);
        }

        model.addAttribute("reports", reportList);
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("order", order);
        model.addAttribute("sort", sort);

        return "admin/admin_report";
    }


    /**
     * 查看举报信息
     *
     * @param reportId 举报编号
     * @return 重定向到/admin/report
     */
    @GetMapping(value = "/info")
    public String reportInfo(@RequestParam("id") Long reportId, Model model) {
        Report report = reportService.get(reportId);
        User user = userService.get(report.getUserId());
        Post post = postService.get(report.getPostId());
        report.setUser(user == null ? new User() : user);
        report.setPost(post == null ? new Post() : post);
        model.addAttribute("report", report);
        return "admin/admin_report_info";
    }


    /**
     * 处理删除举报的请求
     *
     * @param reportId 举报编号
     * @return 重定向到/admin/report
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeReport(@RequestParam("id") Long reportId) {
        reportService.delete(reportId);
        return JsonResult.success("删除成功");
    }

    /**
     * 处理审核举报的请求
     *
     * @param reportId 举报编号
     * @return 重定向到/admin/report
     */
    @PostMapping(value = "/examine")
    @ResponseBody
    public JsonResult passReport(@RequestParam("id") Long reportId,
                                 @RequestParam("status") Integer status) {
        Report report = reportService.get(reportId);
        if (report != null) {
            report.setStatus(status);
            reportService.update(report);

//            if(status == 1) {
//                // 删除房屋
//                postService.delete(report.getPostId());
//            }
        }
        return JsonResult.success("操作成功");
    }
}