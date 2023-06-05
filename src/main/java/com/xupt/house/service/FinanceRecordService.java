package com.xupt.house.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.common.base.BaseService;
import com.xupt.house.entity.FinanceRecord;

/**
 *
 */
public interface FinanceRecordService extends BaseService<FinanceRecord, Long> {

    Page<FinanceRecord> findAll(String startDate, String endDate, Page<FinanceRecord> page);

    /**
     * 根据用户ID获得预定列表
     *
     * @param userId
     * @param page
     * @return
     */
    Page<FinanceRecord> findByUserId(String startDate, String endDate, Long userId, Page<FinanceRecord> page);


    /**
     * 根据时间范围查询总金额
     *
     * @param startDate
     * @param endDate
     * @return
     */
    Integer getTotalMoneySum(String startDate, String endDate);
}