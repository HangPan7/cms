package com.xs.sw.veh.module.crm.convert.customer;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.util.collection.CollectionUtils;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigCreateReqVO;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigRespVO;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigUpdateReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerLimitConfigDO;
import com.xs.sw.veh.module.system.api.dept.dto.DeptRespDTO;
import com.xs.sw.veh.module.system.api.user.dto.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 客户限制配置 Convert
 *
 * @author Wanwan
 */
@Mapper
public interface CrmCustomerLimitConfigConvert {

    CrmCustomerLimitConfigConvert INSTANCE = Mappers.getMapper(CrmCustomerLimitConfigConvert.class);

    CrmCustomerLimitConfigDO convert(CrmCustomerLimitConfigCreateReqVO bean);

    CrmCustomerLimitConfigDO convert(CrmCustomerLimitConfigUpdateReqVO bean);

    CrmCustomerLimitConfigRespVO convert(CrmCustomerLimitConfigDO bean);

    List<CrmCustomerLimitConfigRespVO> convertList(List<CrmCustomerLimitConfigDO> list);

    PageResult<CrmCustomerLimitConfigRespVO> convertPage(PageResult<CrmCustomerLimitConfigDO> page);

    default PageResult<CrmCustomerLimitConfigRespVO> convertPage(PageResult<CrmCustomerLimitConfigDO> pageResult,
                                                                 Map<Long, AdminUserRespDTO> userMap, Map<Long, DeptRespDTO> deptMap) {
        PageResult<CrmCustomerLimitConfigRespVO> result = convertPage(pageResult);
        result.getList().forEach(respVo -> fillNameField(userMap, deptMap, respVo));
        return result;
    }

    default CrmCustomerLimitConfigRespVO convert(CrmCustomerLimitConfigDO customerLimitConfig,
                                                 Map<Long, AdminUserRespDTO> userMap, Map<Long, DeptRespDTO> deptMap) {
        CrmCustomerLimitConfigRespVO respVo = convert(customerLimitConfig);
        fillNameField(userMap, deptMap, respVo);
        return respVo;
    }

    /**
     * 填充名称字段
     *
     * @param userMap 用户映射
     * @param deptMap 部门映射
     * @param respVo  响应实体
     */
    static void fillNameField(Map<Long, AdminUserRespDTO> userMap, Map<Long, DeptRespDTO> deptMap, CrmCustomerLimitConfigRespVO respVo) {
        respVo.setUsers(CollectionUtils.convertList(respVo.getUserIds(), userMap::get));
        respVo.setDepts(CollectionUtils.convertList(respVo.getDeptIds(), deptMap::get));
    }

}
