package com.xupt.house.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.UserRoleRef;
import com.xupt.house.mapper.UserRoleRefMapper;
import com.xupt.house.service.UserRoleRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleRefServiceImpl implements UserRoleRefService {

    @Autowired
    private UserRoleRefMapper roleRefMapper;


    @Override
    public void deleteByUserId(Long userId) {
        roleRefMapper.deleteByUserId(userId);
    }

    @Override
    public BaseMapper<UserRoleRef> getRepository() {
        return roleRefMapper;
    }

    @Override
    public QueryWrapper<UserRoleRef> getQueryWrapper(UserRoleRef userRoleRef) {
        //对指定字段查询
        QueryWrapper<UserRoleRef> queryWrapper = new QueryWrapper<>();
        if (userRoleRef != null) {
            if (userRoleRef.getUserId() != null) {
                queryWrapper.eq("user_id", userRoleRef.getUserId());
            }
            if (userRoleRef.getRoleId() != null) {
                queryWrapper.eq("role_id", userRoleRef.getRoleId());
            }
        }
        return queryWrapper;
    }

}
