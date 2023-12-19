package com.xs.sw.veh.module.crm.framework.vo;

import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.validation.InEnum;
import com.xs.sw.veh.module.crm.enums.common.CrmSceneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - CRM 分页 Base Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CrmBasePageReqVO extends PageParam {

    /**
     * 场景类型，为 null 时则表示全部
     */
    @Schema(description = "场景类型", example = "1")
    @InEnum(CrmSceneEnum.class)
    private Integer sceneType;

    @Schema(description = "是否为公海数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean pool; // null 则表示为不是公海数据

}
