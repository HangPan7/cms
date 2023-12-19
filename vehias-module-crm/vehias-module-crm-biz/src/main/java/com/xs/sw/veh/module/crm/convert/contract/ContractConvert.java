package com.xs.sw.veh.module.crm.convert.contract;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.crm.controller.admin.contract.vo.*;
import com.xs.sw.veh.module.crm.dal.dataobject.contract.CrmContractDO;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.xs.sw.veh.module.crm.service.permission.bo.CrmPermissionTransferReqBO;
import com.xs.sw.veh.module.system.api.user.dto.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertMap;
import static com.xs.sw.veh.framework.common.util.collection.MapUtils.findAndThen;

/**
 * 合同 Convert
 *
 * @author dhb52
 */
@Mapper
public interface ContractConvert {

    ContractConvert INSTANCE = Mappers.getMapper(ContractConvert.class);

    CrmContractDO convert(CrmContractCreateReqVO bean);

    CrmContractDO convert(CrmContractUpdateReqVO bean);

    ContractRespVO convert(CrmContractDO bean);

    List<ContractRespVO> convertList(List<CrmContractDO> list);

    PageResult<ContractRespVO> convertPage(PageResult<CrmContractDO> page);

    List<CrmContractExcelVO> convertList02(List<CrmContractDO> list);

    @Mappings({
            @Mapping(target = "bizId", source = "reqVO.id"),
            @Mapping(target = "newOwnerUserId", source = "reqVO.id")
    })
    CrmPermissionTransferReqBO convert(CrmContractTransferReqVO reqVO, Long userId);

    default PageResult<ContractRespVO> convertPage(PageResult<CrmContractDO> pageResult, Map<Long, AdminUserRespDTO> userMap,
                                                     List<CrmCustomerDO> customerList) {
        return new PageResult<>(converList(pageResult.getList(), userMap, customerList), pageResult.getTotal());
    }

    default List<ContractRespVO> converList(List<CrmContractDO> contractList, Map<Long, AdminUserRespDTO> userMap,
                                            List<CrmCustomerDO> customerList) {
        List<ContractRespVO> result = convertList(contractList);
        Map<Long, CrmCustomerDO> customerMap = convertMap(customerList, CrmCustomerDO::getId);
        result.forEach(item -> {
            setUserInfo(item, userMap);
            findAndThen(customerMap, item.getCustomerId(), customer -> item.setCustomerName(customer.getName()));
        });
        return result;
    }

    static void setUserInfo(ContractRespVO contract, Map<Long, AdminUserRespDTO> userMap) {
        findAndThen(userMap, contract.getOwnerUserId(), user -> contract.setOwnerUserName(user.getNickname()));
        findAndThen(userMap, Long.parseLong(contract.getCreator()), user -> contract.setCreatorName(user.getNickname()));
    }

}
