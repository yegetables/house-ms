package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

/**
 * 城市
 */
@Data
@TableName("city")
public class City extends BaseEntity {

    /**
     * 分类名称
     */
    private String cityName;

    /**
     * 房屋数
     */
    @TableField(exist = false)
    private Integer count;
}