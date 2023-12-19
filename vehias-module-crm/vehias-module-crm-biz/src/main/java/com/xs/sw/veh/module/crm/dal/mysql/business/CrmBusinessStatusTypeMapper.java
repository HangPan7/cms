package com.xs.sw.veh.module.crm.dal.mysql.business;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypePageReqVO;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeQueryVO;
import com.xs.sw.veh.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商机状态类型 Mapper
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusTypeMapper extends BaseMapperX<CrmBusinessStatusTypeDO> {

    default PageResult<CrmBusinessStatusTypeDO> selectPage(CrmBusinessStatusTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmBusinessStatusTypeDO>()
                .orderByDesc(CrmBusinessStatusTypeDO::getId));
    }

    default List<CrmBusinessStatusTypeDO> selectList(CrmBusinessStatusTypeQueryVO queryVO) {
        return selectList(new LambdaQueryWrapperX<CrmBusinessStatusTypeDO>()
                .eqIfPresent(CrmBusinessStatusTypeDO::getStatus, queryVO.getStatus())
                .inIfPresent(CrmBusinessStatusTypeDO::getId, queryVO.getIdList()));
    }
}
