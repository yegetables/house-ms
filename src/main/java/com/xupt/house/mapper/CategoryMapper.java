package com.xupt.house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获得子分类Id列表
     *
     * @param  pathTrace /138/ 这种格式
     * @return 子分类Id列表
     */
    List<Long> selectChildCateIds(@Param("pathTrace") String pathTrace);

    /**
     * 根据用户ID删除
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 获取所有分类
     * @return
     */
    List<Category> findAllWithCount();
}