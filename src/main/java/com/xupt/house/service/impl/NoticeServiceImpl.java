package com.xupt.house.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.Notice;
import com.xupt.house.mapper.NoticeMapper;
import com.xupt.house.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public BaseMapper<Notice> getRepository() {
        return noticeMapper;
    }

    @Override
    public QueryWrapper<Notice> getQueryWrapper(Notice notice) {
        //对指定字段查询
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        return queryWrapper;
    }

}
