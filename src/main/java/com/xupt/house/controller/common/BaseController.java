package com.xupt.house.controller.common;

import com.xupt.house.config.mybatisplus.MybatisPlusConfig;
import com.xupt.house.entity.User;
import com.xupt.house.enums.RoleEnum;
import com.xupt.house.utils.AESUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller抽象类
 */
public abstract class BaseController {

    /**
     * 渲染404页面
     *
     * @return redirect:/404
     */
    public String renderNotFound() {
        return "forward:/404";
    }


    /**
     * 渲染404页面
     *
     * @return redirect:/404
     */
    public String renderNotAllowAccess() {
        return "redirect:/403";
    }

    /**
     * 当前登录用户
     *
     * @return
     */
    public User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return (User) subject.getPrincipal();
        }
        return null;
    }

    /**
     * 当前用户ID
     *
     * @return
     */
    public Long getLoginUserId() {
        return getLoginUser().getId();
    }

    /**
     * 当前用户是管理员
     *
     * @return
     */
    public Boolean loginUserIsAdmin() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.ADMIN.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }

    /**
     * 当前用户是租客
     *
     * @return
     */
    public Boolean loginUserIsCustomer() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.CUSTOMER.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }

    /**
     * 当前用户是房东
     *
     * @return
     */
    public Boolean loginUserIsOwner() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.OWNER.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }


    /**
     * 当前用户是中介
     * @return
     */
    public Boolean loginUserIsAgency() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.AGENCY.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }


}