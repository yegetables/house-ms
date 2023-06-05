package com.xupt.house.controller.admin;

import com.xupt.house.controller.common.BaseController;
import com.xupt.house.dto.JsonResult;
import com.xupt.house.entity.Permission;
import com.xupt.house.entity.Role;
import com.xupt.house.entity.User;
import com.xupt.house.service.PermissionService;
import com.xupt.house.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台首页控制器
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 请求后台页面
     *
     * @param model model
     * @return 模板路径admin/admin_index
     */
    @GetMapping
    public String index(Model model) {
        return "admin/admin_index";
    }


    /**
     * 获得当前用户的菜单
     * @return
     */
    @GetMapping(value = "/currentMenus")
    @ResponseBody
    public JsonResult getMenu() {
        Long userId = getLoginUserId();
        List<Permission> permissions = permissionService.findPermissionTreeByUserIdAndResourceType(userId, "menu");
        return JsonResult.success("", permissions);
    }

    /**
     * 获得当前登录用户
     */
    @GetMapping(value = "/currentUser")
    @ResponseBody
    public JsonResult currentUser() {
        User user = getLoginUser();
        if (user != null) {
            return JsonResult.success("", user);
        }
        return JsonResult.error("用户未登录");
    }

    /**
     * 获得当前用户角色编码
     */
    @GetMapping(value = "/currentRole")
    @ResponseBody
    public JsonResult currentRole() {
        Role role = roleService.findByUserId(getLoginUserId());
        if (role == null) {
            return JsonResult.error("用户未登录或无角色");
        }
        return JsonResult.success("", role.getRole());
    }

}