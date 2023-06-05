package com.xupt.house.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.City;
import com.xupt.house.mapper.CityMapper;
import com.xupt.house.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务服务实现类
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;


    @Override
    public BaseMapper<City> getRepository() {
        return cityMapper;
    }

    @Override
    public QueryWrapper<City> getQueryWrapper(City city) {
        //对指定字段查询
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        if (city != null) {
            if (StrUtil.isNotBlank(city.getCityName())) {
                queryWrapper.like("city_name", city.getCityName());
            }
        }
        return queryWrapper;
    }

    @Override
    public List<City> findAll() {
        return cityMapper.findAllWithCount();
    }
}