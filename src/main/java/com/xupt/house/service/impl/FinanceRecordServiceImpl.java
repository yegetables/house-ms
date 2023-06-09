package com.xupt.house.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.entity.FinanceRecord;
import com.xupt.house.mapper.FinanceRecordMapper;
import com.xupt.house.service.FinanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceRecordServiceImpl implements FinanceRecordService {

    @Autowired
    private FinanceRecordMapper financeRecordMapper;

    @Override
    public BaseMapper<FinanceRecord> getRepository() {
        return financeRecordMapper;
    }

    @Override
    public QueryWrapper<FinanceRecord> getQueryWrapper(FinanceRecord financeRecord) {
        //对指定字段查询
        QueryWrapper<FinanceRecord> queryWrapper = new QueryWrapper<>();
        if (financeRecord != null) {
            if (financeRecord.getUserId() != null) {
                queryWrapper.eq("user_id", financeRecord.getUserId());
            }
        }
        return queryWrapper;
    }

    @Override
    public Page<FinanceRecord> findAll(String startDate, String endDate, Page<FinanceRecord> page) {
        return page.setRecords(financeRecordMapper.findAll(startDate, endDate, page));
    }

    @Override
    public Page<FinanceRecord> findByUserId(String startDate, String endDate, Long userId, Page<FinanceRecord> page) {
        return page.setRecords(financeRecordMapper.findByUserId(startDate, endDate, userId, page));
    }

    @Override
    public Integer getTotalMoneySum(String startDate, String endDate) {
        return financeRecordMapper.getTotalMoneySum(startDate, endDate);
    }
}