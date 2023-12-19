package com.xs.sw.veh.module.crm.controller.admin.customer;

import com.xs.sw.veh.framework.common.pojo.CommonResult;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.poolconfig.CrmCustomerPoolConfigRespVO;
import com.xs.sw.veh.module.crm.controller.admin.customer.vo.poolconfig.CrmCustomerPoolConfigSaveReqVO;
import com.xs.sw.veh.module.crm.convert.customer.CrmCustomerConvert;
import com.xs.sw.veh.module.crm.dal.dataobject.customer.CrmCustomerPoolConfigDO;
import com.xs.sw.veh.module.crm.service.customer.CrmCustomerPoolConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.xs.sw.veh.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - CRM 客户公海配置")
@RestController
@RequestMapping("/crm/customer-pool-config")
@Validated
public class CrmCustomerPoolConfigController {

    @Resource
    private CrmCustomerPoolConfigService customerPoolConfigService;

    @GetMapping("/get")
    @Operation(summary = "获取客户公海规则设置")
    @PreAuthorize("@ss.hasPermission('crm:customer-pool-config:query')")
    public CommonResult<CrmCustomerPoolConfigRespVO> getCustomerPoolConfig() {
        CrmCustomerPoolConfigDO customerPoolConfig = customerPoolConfigService.getCustomerPoolConfig();
        return success(CrmCustomerConvert.INSTANCE.convert(customerPoolConfig));
    }

    @PutMapping("/save")
    @Operation(summary = "更新客户公海规则设置")
    @PreAuthorize("@ss.hasPermission('crm:customer-pool-config:update')")
    public CommonResult<Boolean> saveCustomerPoolConfig(@Valid @RequestBody CrmCustomerPoolConfigSaveReqVO updateReqVO) {
        customerPoolConfigService.saveCustomerPoolConfig(updateReqVO);
        return success(true);
    }

}
