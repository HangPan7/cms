package com.xs.sw.veh.module.statistics.dal.mysql.pay;

import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.module.statistics.service.pay.bo.RechargeSummaryRespBO;
import com.xs.sw.veh.module.statistics.service.trade.bo.WalletSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 支付钱包的统计 Mapper
 *
 * @author owen
 */
@Mapper
@SuppressWarnings("rawtypes")
public interface PayWalletStatisticsMapper extends BaseMapperX {

    // TODO 芋艿：已经 review；
    WalletSummaryRespBO selectRechargeSummaryByPayTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                              @Param("endTime") LocalDateTime endTime,
                                                              @Param("payStatus") Boolean payStatus);

    // TODO 芋艿：已经 review；
    WalletSummaryRespBO selectRechargeSummaryByRefundTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                                 @Param("endTime") LocalDateTime endTime,
                                                                 @Param("refundStatus") Integer refundStatus);

    // TODO 芋艿：已经 review；
    Integer selectPriceSummaryByBizTypeAndCreateTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                            @Param("endTime") LocalDateTime endTime,
                                                            @Param("bizType") Integer bizType);

    // TODO 芋艿：已经 review；
    RechargeSummaryRespBO selectRechargeSummaryGroupByWalletId(@Param("beginTime") LocalDateTime beginTime,
                                                               @Param("endTime") LocalDateTime endTime,
                                                               @Param("payStatus") Boolean payStatus);

    // TODO 芋艿：已经 review；
    Integer selectRechargePriceSummary(@Param("payStatus") Integer payStatus);

}
