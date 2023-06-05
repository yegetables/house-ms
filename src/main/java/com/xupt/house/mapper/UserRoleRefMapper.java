package com.xupt.house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.UserRoleRef;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleRefMapper extends BaseMapper<UserRoleRef> {

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     * @return 影响行数
     */
    Integer deleteByUserId(Long userId);
}