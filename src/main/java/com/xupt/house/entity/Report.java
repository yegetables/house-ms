package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

/**
 * 举报信息
 */
@Data
@TableName("report")
public class Report extends BaseEntity {

    /**
     * 类型
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房屋id
     */
    private Long postId;

    /**
     * 状态：0待审核，1通过，2不通过
     */
    private Integer status;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Post post;


}