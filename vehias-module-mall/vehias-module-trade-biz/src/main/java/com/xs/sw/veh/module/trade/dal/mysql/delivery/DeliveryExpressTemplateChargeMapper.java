package com.xs.sw.veh.module.trade.dal.mysql.delivery;


import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.module.trade.dal.dataobject.delivery.DeliveryExpressTemplateChargeDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DeliveryExpressTemplateChargeMapper extends BaseMapperX<DeliveryExpressTemplateChargeDO> {

    default List<DeliveryExpressTemplateChargeDO> selectListByTemplateId(Long templateId){
        return selectList(new LambdaQueryWrapper<DeliveryExpressTemplateChargeDO>()
                .eq(DeliveryExpressTemplateChargeDO::getTemplateId, templateId));
    }

    default int deleteByTemplateId(Long templateId){
       return delete(new LambdaQueryWrapper<DeliveryExpressTemplateChargeDO>()
               .eq(DeliveryExpressTemplateChargeDO::getTemplateId, templateId));
    }

    default List<DeliveryExpressTemplateChargeDO> selectByTemplateIds(Collection<Long> templateIds) {
        return selectList(DeliveryExpressTemplateChargeDO::getTemplateId, templateIds);
    }

}




