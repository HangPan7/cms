package com.xs.sw.veh.module.crm.service.customer;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigCreateReqVO;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigPageReqVO;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig.CrmCustomerLimitConfigUpdateReqVO;
import com.xs.sw.veh.module.crm.convert.customer.CrmCustomerLimitConfigConvert;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerLimitConfigDO;
import com.xs.sw.veh.module.crm.dal.mysql.customer.CrmCustomerLimitConfigMapper;
import com.xs.sw.veh.module.system.api.dept.DeptApi;
import com.xs.sw.veh.module.system.api.user.AdminUserApi;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.Collection;

import static com.xs.sw.veh.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.xs.sw.veh.module.crm.enums.ErrorCodeConstants.CUSTOMER_LIMIT_CONFIG_NOT_EXISTS;

/**
 * 客户限制配置 Service 实现类
 *
 * @author Wanwan
 */
@Service
@Validated
public class CrmCustomerLimitConfigServiceImpl implements CrmCustomerLimitConfigService {

    @Resource
    private CrmCustomerLimitConfigMapper customerLimitConfigMapper;
    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public Long createCustomerLimitConfig(CrmCustomerLimitConfigCreateReqVO createReqVO) {
        validateUserAndDept(createReqVO.getUserIds(), createReqVO.getDeptIds());
        // 插入
        CrmCustomerLimitConfigDO customerLimitConfig = CrmCustomerLimitConfigConvert.INSTANCE.convert(createReqVO);
        customerLimitConfigMapper.insert(customerLimitConfig);
        // 返回
        return customerLimitConfig.getId();
    }

    @Override
    public void updateCustomerLimitConfig(CrmCustomerLimitConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateCustomerLimitConfigExists(updateReqVO.getId());
        validateUserAndDept(updateReqVO.getUserIds(), updateReqVO.getDeptIds());
        // 更新
        CrmCustomerLimitConfigDO updateObj = CrmCustomerLimitConfigConvert.INSTANCE.convert(updateReqVO);
        customerLimitConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerLimitConfig(Long id) {
        // 校验存在
        validateCustomerLimitConfigExists(id);
        // 删除
        customerLimitConfigMapper.deleteById(id);
    }

    @Override
    public CrmCustomerLimitConfigDO getCustomerLimitConfig(Long id) {
        return customerLimitConfigMapper.selectById(id);
    }

    @Override
    public PageResult<CrmCustomerLimitConfigDO> getCustomerLimitConfigPage(CrmCustomerLimitConfigPageReqVO pageReqVO) {
        return customerLimitConfigMapper.selectPage(pageReqVO);
    }

    private void validateCustomerLimitConfigExists(Long id) {
        if (customerLimitConfigMapper.selectById(id) == null) {
            throw exception(CUSTOMER_LIMIT_CONFIG_NOT_EXISTS);
        }
    }

    /**
     * 校验入参的用户和部门
     *
     * @param userIds 用户 ids
     * @param deptIds 部门 ids
     */
    private void validateUserAndDept(Collection<Long> userIds, Collection<Long> deptIds) {
        deptApi.validateDeptList(deptIds);
        adminUserApi.validateUserList(userIds);
    }

}
