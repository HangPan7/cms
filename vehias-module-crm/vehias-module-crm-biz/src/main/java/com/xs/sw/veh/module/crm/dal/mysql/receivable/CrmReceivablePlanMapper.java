package com.xs.sw.veh.module.crm.dal.mysql.receivable;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.crm.controller.admin.receivable.vo.plan.CrmReceivablePlanPageReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.receivable.CrmReceivablePlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回款计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CrmReceivablePlanMapper extends BaseMapperX<CrmReceivablePlanDO> {

    default PageResult<CrmReceivablePlanDO> selectPage(CrmReceivablePlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivablePlanDO>()
                .eqIfPresent(CrmReceivablePlanDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CrmReceivablePlanDO::getContractId, reqVO.getContractId())
                .orderByDesc(CrmReceivablePlanDO::getId));
    }

    default PageResult<CrmReceivablePlanDO> selectPageByCustomer(CrmReceivablePlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmReceivablePlanDO>()
                .eq(CrmReceivablePlanDO::getCustomerId, reqVO.getCustomerId()) // 必须传递
                .eqIfPresent(CrmReceivablePlanDO::getContractId, reqVO.getContractId())
                .orderByDesc(CrmReceivablePlanDO::getId));
    }

}
