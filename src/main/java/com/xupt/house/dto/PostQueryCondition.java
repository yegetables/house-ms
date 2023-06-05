package com.xupt.house.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostQueryCondition {

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 分类ID
     */
    private Long cateId;

    /**
     * 预订的日期集合
     */
    private List<String> dateList;
}
