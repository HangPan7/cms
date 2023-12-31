package com.xs.sw.veh.module.crm.dal.mysql.contract;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.crm.controller.admin.contract.vo.CrmContractPageReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.contract.CrmContractDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * CRM 合同 Mapper
 *
 * @author dhb52
 */
@Mapper
public interface CrmContractMapper extends BaseMapperX<CrmContractDO> {

    default PageResult<CrmContractDO> selectPage(CrmContractPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmContractDO>()
            .likeIfPresent(CrmContractDO::getNo, reqVO.getNo())
            .likeIfPresent(CrmContractDO::getName, reqVO.getName())
            .eqIfPresent(CrmContractDO::getCustomerId, reqVO.getCustomerId())
            .eqIfPresent(CrmContractDO::getBusinessId, reqVO.getBusinessId())
            .orderByDesc(CrmContractDO::getId));
    }

    default PageResult<CrmContractDO> selectPageByCustomer(CrmContractPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmContractDO>()
                .eq(CrmContractDO::getCustomerId, reqVO.getCustomerId()) // 必须传递
                .likeIfPresent(CrmContractDO::getNo, reqVO.getNo())
                .likeIfPresent(CrmContractDO::getName, reqVO.getName())
                .eqIfPresent(CrmContractDO::getBusinessId, reqVO.getBusinessId())
                .orderByDesc(CrmContractDO::getId));
    }

}
