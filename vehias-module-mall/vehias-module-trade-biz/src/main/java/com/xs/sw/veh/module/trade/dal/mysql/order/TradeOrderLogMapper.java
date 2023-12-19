package com.xs.sw.veh.module.trade.dal.mysql.order;

import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.module.trade.dal.dataobject.order.TradeOrderLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeOrderLogMapper extends BaseMapperX<TradeOrderLogDO> {

    default List<TradeOrderLogDO> selectListByOrderId(Long orderId) {
        return selectList(TradeOrderLogDO::getOrderId, orderId);
    }

}
