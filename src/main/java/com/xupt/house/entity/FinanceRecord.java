package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

/**
 * 收支记录
 */
@TableName("finance_record")
@Data
public class FinanceRecord extends BaseEntity {

    /**
     * 类型：租金收入/押金收入/租金退回收入/押金退回收入
     */
    private String type;

    /**
     * 详细描述
     */
    private String content;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金额
     */
    private Integer money;

    /**
     * 用户
     */
    @TableField(exist = false)
    private User user;
}

