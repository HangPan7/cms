package com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 机构 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StationInfoRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "6974")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("机构编号")
    private String jyjgbh;

    @Schema(description = "检验机构位置名称", example = "张三")
    @ExcelProperty("检验机构位置名称")
    private String locationName;

    @Schema(description = "营业时间")
    @ExcelProperty("营业时间")
    private String businessHours;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String state;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系方式")
    private String phone;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经度")
    private String latitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纬度")
    private String longitude;

    @Schema(description = "环保检验编号")
    @ExcelProperty("环保检验编号")
    private String bhjybh;

    @Schema(description = "监督电话")
    @ExcelProperty("监督电话")
    private String supervisePhone;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "机构名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("机构名称")
    private String jyjbmc;

    @Schema(description = "所属行政区规划", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属行政区规划")
    private String area;

    @Schema(description = "检测能力")
    @ExcelProperty("检测能力")
    private String ability;

    @Schema(description = "在线")
    @ExcelProperty("在线")
    private Boolean online;

    @Schema(description = "行政区编号", example = "2183")
    @ExcelProperty("行政区编号")
    private String areaId;

    @Schema(description = "所属城市")
    @ExcelProperty("所属城市")
    private String city;

    @Schema(description = "所属城市id", example = "29942")
    @ExcelProperty("所属城市id")
    private String cityId;

    @Schema(description = "检查车辆类型", example = "2")
    @ExcelProperty("检查车辆类型")
    private String checkType;

}