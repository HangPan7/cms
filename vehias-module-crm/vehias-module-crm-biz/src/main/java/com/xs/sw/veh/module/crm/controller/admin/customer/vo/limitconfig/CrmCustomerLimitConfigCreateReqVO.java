package com.xs.sw.veh.module.crm.controller.admin.customer.vo.limitconfig;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 客户限制配置创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerLimitConfigCreateReqVO extends CrmCustomerLimitConfigBaseVO {

}
