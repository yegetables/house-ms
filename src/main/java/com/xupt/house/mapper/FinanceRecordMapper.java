package com.xupt.house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.house.entity.FinanceRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FinanceRecordMapper extends BaseMapper<FinanceRecord> {

    /**
     * 删除用户和预定关联
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 查询所有收支明细
     *
     * @return
     */
    List<FinanceRecord> findAll(@Param("startDate") String startDate,
                                @Param("endDate") String endDate,
                                Page page);


    /**
     * 获得该用户的收支明细
     *
     * @return
     */
    List<FinanceRecord> findByUserId(@Param("startDate") String startDate,
                                     @Param("endDate") String endDate,
                                     @Param("userId") Long userId, Page page);

    /**
     * 根据时间范围查询总金额
     *
     * @param startDate
     * @param endDate
     * @return
     */
    Integer getTotalMoneySum(@Param("startDate") String startDate,
                             @Param("endDate") String endDate);
}