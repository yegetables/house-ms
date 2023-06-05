package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

/**
 * 房屋分类
 */
@Data
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 分类排序号
     */
    private Integer cateSort;

    /**
     * 分类描述
     */
    private String cateDesc;

    /**
     * 房屋数量
     */
    @TableField(exist = false)
    private Integer count;
}