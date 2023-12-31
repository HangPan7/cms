package com.xs.sw.veh.module.crm.convert.contactbusinessslink;

import com.xs.sw.veh.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkSaveReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.contactbusinesslink.CrmContactBusinessLinkDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

// TODO @zyna：使用 BeanUtils 慢慢替代现有的 mapstruct 哈
@Mapper
public interface CrmContactBusinessLinkConvert {
    CrmContactBusinessLinkConvert INSTANCE = Mappers.getMapper(CrmContactBusinessLinkConvert.class);
    CrmContactBusinessLinkDO convert(CrmContactBusinessLinkSaveReqVO bean);
    List<CrmContactBusinessLinkDO> convert(List<CrmContactBusinessLinkSaveReqVO> bean);
}
