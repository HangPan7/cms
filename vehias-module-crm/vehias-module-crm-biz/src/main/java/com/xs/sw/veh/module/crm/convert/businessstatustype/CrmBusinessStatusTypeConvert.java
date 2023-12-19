package com.xs.sw.veh.module.crm.convert.businessstatustype;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeRespVO;
import com.xs.sw.veh.module.crm.controller.admin.business.vo.type.CrmBusinessStatusTypeSaveReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.business.CrmBusinessStatusDO;
import com.xs.sw.veh.module.crm.dal.dataobject.business.CrmBusinessStatusTypeDO;
import com.xs.sw.veh.module.system.api.dept.dto.DeptRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertList;
import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertMap;

/**
 * 商机状态类型 Convert
 *
 * @author ljlleo
 */
@Mapper
public interface CrmBusinessStatusTypeConvert {

    CrmBusinessStatusTypeConvert INSTANCE = Mappers.getMapper(CrmBusinessStatusTypeConvert.class);

    CrmBusinessStatusTypeDO convert(CrmBusinessStatusTypeSaveReqVO bean);

    CrmBusinessStatusTypeRespVO convert(CrmBusinessStatusTypeDO bean);

    PageResult<CrmBusinessStatusTypeRespVO> convertPage(PageResult<CrmBusinessStatusTypeDO> page);

    default PageResult<CrmBusinessStatusTypeRespVO> convertPage(PageResult<CrmBusinessStatusTypeDO> page, List<DeptRespDTO> deptList) {
        PageResult<CrmBusinessStatusTypeRespVO> pageResult = convertPage(page);
        // 拼接关联字段
        Map<Long, String> deptMap = convertMap(deptList, DeptRespDTO::getId, DeptRespDTO::getName);
        pageResult.getList().forEach(type -> type.setDeptNames(convertList(type.getDeptIds(), deptMap::get)));
        return pageResult;
    }

    default CrmBusinessStatusTypeRespVO convert(CrmBusinessStatusTypeDO bean, List<CrmBusinessStatusDO> statusList) {
        // TODO @ljlleo 可以链式赋值，简化成一行；
        CrmBusinessStatusTypeRespVO result = convert(bean);
        result.setStatusList(statusList);
        return result;
    }

}
