package com.xupt.house.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xupt.house.common.base.BaseEntity;
import lombok.Data;

/**
 * 新闻公告
 */
@Data
@TableName("notice")
public class Notice extends BaseEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String summary;

}