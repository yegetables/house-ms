package com.xupt.house.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.Report;
import com.xupt.house.mapper.ReportMapper;
import com.xupt.house.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;


    @Override
    public BaseMapper<Report> getRepository() {
        return reportMapper;
    }

    @Override
    public QueryWrapper<Report> getQueryWrapper(Report report) {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        return queryWrapper;
    }

    @Override
    public Report getNotCheckReport(Long postId, Long userId) {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("status", 0);
        List<Report> reports = reportMapper.selectList(queryWrapper);
        if (reports.size() > 0) {
            return reports.get(0);
        }
        return null;
    }
}
