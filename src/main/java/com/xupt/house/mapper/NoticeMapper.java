package com.xupt.house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xupt.house.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
}
