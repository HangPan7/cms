package com.xs.sw.veh.module.ias.controller.admin.stationinfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class StationInfoSaveReqDTO {
    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "6974")
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "机构编号不能为空")
    private String jyjgbh;

    @Schema(description = "检验机构位置名称", example = "张三")
    private String locationName;

    @Schema(description = "营业时间")
    private String businessHours;

    @Schema(description = "状态")
    private String state;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系方式不能为空")
    private String phone;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经度不能为空")
    private String latitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纬度不能为空")
    private String longitude;

    @Schema(description = "环保检验编号")
    private String bhjybh;

    @Schema(description = "监督电话")
    private String supervisePhone;

    @Schema(description = "机构名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "机构名称不能为空")
    private String jyjbmc;

    @Schema(description = "所属行政区规划", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属行政区规划不能为空")
    private String area;

    @Schema(description = "检测能力")
    private String ability;

    @Schema(description = "在线")
    private Boolean online;

    @Schema(description = "行政区编号", example = "2183")
    private String areaId;

    @Schema(description = "所属城市")
    private String city;

    @Schema(description = "所属城市id", example = "29942")
    private String cityId;

    @Schema(description = "检查车辆类型", example = "2")
    private List<String> checkType;
}
