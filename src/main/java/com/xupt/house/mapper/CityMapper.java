package com.xupt.house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CityMapper extends BaseMapper<City> {

    /**
     * 获取所有城市
     * @return
     */
    List<City> findAllWithCount();
}
