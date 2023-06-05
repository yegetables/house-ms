package com.xupt.house.service;

import com.xupt.house.common.base.BaseService;
import com.xupt.house.entity.UserRoleRef;

public interface UserRoleRefService extends BaseService<UserRoleRef, Long> {

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     */
    void deleteByUserId(Long userId);

}
