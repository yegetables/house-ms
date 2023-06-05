package com.xupt.house.service;

import com.xupt.house.common.base.BaseService;
import com.xupt.house.entity.Report;

/**
 * 举报业务逻辑接口
 */
public interface ReportService extends BaseService<Report, Long> {

    Report getNotCheckReport(Long postId, Long userId);
}
