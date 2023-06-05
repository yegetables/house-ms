package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("user")
public class User extends BaseEntity {

    /**
     * 账号
     */
    private String userName;

    /**
     * 姓名称
     */
    private String userDisplayName;

    /**
     * 密码
     */
    @JsonIgnore
    private String userPass;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 说明
     */
    private String userDesc;

    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    private Integer status = 0;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private Role role;

}
