package com.xs.sw.veh.module.crm.convert.receivable;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableCreateReqVO;
import com.xs.sw.veh.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableRespVO;
import com.xs.sw.veh.module.crm.controller.admin.receivable.vo.receivable.CrmReceivableUpdateReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.contract.CrmContractDO;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerDO;
import com.xs.sw.veh.module.crm.dal.dataobject.receivable.CrmReceivableDO;
import com.xs.sw.veh.module.system.api.user.dto.AdminUserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static com.xs.sw.veh.framework.common.util.collection.CollectionUtils.convertMap;
import static com.xs.sw.veh.framework.common.util.collection.MapUtils.findAndThen;

/**
 * 回款 Convert
 *
 * @author 赤焰
 */
@Mapper
public interface CrmReceivableConvert {

    CrmReceivableConvert INSTANCE = Mappers.getMapper(CrmReceivableConvert.class);

    CrmReceivableDO convert(CrmReceivableCreateReqVO bean);

    CrmReceivableDO convert(CrmReceivableUpdateReqVO bean);

    CrmReceivableRespVO convert(CrmReceivableDO bean);

    List<CrmReceivableRespVO> convertList(List<CrmReceivableDO> list);

    default PageResult<CrmReceivableRespVO> convertPage(PageResult<CrmReceivableDO> pageResult, Map<Long, AdminUserRespDTO> userMap,
                                                        List<CrmCustomerDO> customerList, List<CrmContractDO> contractList) {
        return new PageResult<>(converList(pageResult.getList(), userMap, customerList, contractList), pageResult.getTotal());
    }

    default List<CrmReceivableRespVO> converList(List<CrmReceivableDO> receivableList, Map<Long, AdminUserRespDTO> userMap,
                                                 List<CrmCustomerDO> customerList, List<CrmContractDO> contractList) {
        List<CrmReceivableRespVO> result = convertList(receivableList);
        Map<Long, CrmCustomerDO> customerMap = convertMap(customerList, CrmCustomerDO::getId);
        Map<Long, CrmContractDO> contractMap = convertMap(contractList, CrmContractDO::getId);
        result.forEach(item -> {
            setUserInfo(item, userMap);
            findAndThen(customerMap, item.getCustomerId(), customer -> item.setCustomerName(customer.getName()));
            findAndThen(contractMap, item.getContractId(), contract -> item.setContractNo(contract.getNo()));
        });
        return result;
    }

    static void setUserInfo(CrmReceivableRespVO receivable, Map<Long, AdminUserRespDTO> userMap) {
        findAndThen(userMap, receivable.getOwnerUserId(), user -> receivable.setOwnerUserName(user.getNickname()));
        findAndThen(userMap, Long.parseLong(receivable.getCreator()), user -> receivable.setCreatorName(user.getNickname()));
    }

}
