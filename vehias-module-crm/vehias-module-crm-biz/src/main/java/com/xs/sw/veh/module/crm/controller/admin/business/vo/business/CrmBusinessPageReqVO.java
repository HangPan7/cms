package com.xs.sw.veh.module.crm.controller.admin.business.vo.business;

import com.xs.sw.veh.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 商机分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessPageReqVO extends PageParam {

    @Schema(description = "商机名称", example = "李四")
    private String name;

    @Schema(description = "客户编号", example = "10795")
    private Long customerId;

}
