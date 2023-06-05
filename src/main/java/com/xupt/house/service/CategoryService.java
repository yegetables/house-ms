package com.xupt.house.service;

import com.xupt.house.common.base.BaseService;
import com.xupt.house.entity.Category;

import java.util.List;

/**
 * 分类业务逻辑接口
 */
public interface CategoryService extends BaseService<Category, Long> {

    /**
     * 查询所有分类目录,带count和根据level封装name
     *
     * @return 返回List集合
     */
    List<Category> findByUserId(Long userId);

    /**
     * 获得某个分类的所有房屋数
     *
     * @param cateId 分类Id
     * @return 房屋数
     */
    Integer countPostByCateId(Long cateId);

    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 将分类ID列表转成分类
     *
     * @param cateIds
     * @param userId
     * @return
     */
    List<Category> cateIdsToCateList(List<Long> cateIds, Long userId);

}