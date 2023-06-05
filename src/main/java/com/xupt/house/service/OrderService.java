package com.xupt.house.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.common.base.BaseService;
import com.xupt.house.entity.Order;

/**
 * 订单
 */
public interface OrderService extends BaseService<Order, Long> {

    /**
     * 查询总金额
     *
     * @return
     */
    Integer getTotalPriceSum(Order condition);

    /**
     * 根据条件查询
     *
     * @param condition
     * @param page
     * @return
     */
    Page<Order> findAll(Order condition, Page<Order> page);

    /**
     * 查询有效订单
     * @param postId
     * @return
     */
    Order findByPostId(Long postId);

}