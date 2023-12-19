package com.xs.sw.veh.module.statistics.service.trade;

import com.xs.sw.veh.module.statistics.dal.mysql.trade.AfterSaleStatisticsMapper;
import com.xs.sw.veh.module.statistics.service.trade.bo.AfterSaleSummaryRespBO;
import com.xs.sw.veh.module.trade.enums.aftersale.AfterSaleStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 售后统计 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
public class AfterSaleStatisticsServiceImpl implements AfterSaleStatisticsService {

    @Resource
    private AfterSaleStatisticsMapper afterSaleStatisticsMapper;

    @Override
    public AfterSaleSummaryRespBO getAfterSaleSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        return afterSaleStatisticsMapper.selectSummaryByRefundTimeBetween(beginTime, endTime);
    }

    @Override
    public Long getCountByStatus(AfterSaleStatusEnum status) {
        return afterSaleStatisticsMapper.selectCountByStatus(status.getStatus());
    }

}
