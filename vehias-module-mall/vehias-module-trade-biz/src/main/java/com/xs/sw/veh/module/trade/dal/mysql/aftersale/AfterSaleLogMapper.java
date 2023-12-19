package com.xs.sw.veh.module.trade.dal.mysql.aftersale;

import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.module.trade.dal.dataobject.aftersale.AfterSaleLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AfterSaleLogMapper extends BaseMapperX<AfterSaleLogDO> {

    default List<AfterSaleLogDO> selectListByAfterSaleId(Long afterSaleId) {
        return selectList(AfterSaleLogDO::getAfterSaleId, afterSaleId);
    }

}
