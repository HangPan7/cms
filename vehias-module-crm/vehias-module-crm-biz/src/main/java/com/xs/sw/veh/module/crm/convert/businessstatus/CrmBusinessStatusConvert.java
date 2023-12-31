package com.xs.sw.veh.module.crm.convert.businessstatus;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.status.CrmBusinessStatusRespVO;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.status.CrmBusinessStatusSaveReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商机状态 Convert
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusConvert {

    CrmBusinessStatusConvert INSTANCE = Mappers.getMapper(CrmBusinessStatusConvert.class);

    CrmBusinessStatusDO convert(CrmBusinessStatusSaveReqVO bean);

    CrmBusinessStatusRespVO convert(CrmBusinessStatusDO bean);

    List<CrmBusinessStatusRespVO> convertList(List<CrmBusinessStatusDO> list);

    PageResult<CrmBusinessStatusRespVO> convertPage(PageResult<CrmBusinessStatusDO> page);

}
