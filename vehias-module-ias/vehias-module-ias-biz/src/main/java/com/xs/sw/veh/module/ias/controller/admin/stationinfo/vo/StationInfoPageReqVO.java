package com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.xs.sw.veh.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.xs.sw.veh.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 机构分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StationInfoPageReqVO extends PageParam {

    @Schema(description = "机构编号")
    private String jyjgbh;

    @Schema(description = "检验机构位置名称", example = "张三")
    private String locationName;

    @Schema(description = "营业时间")
    private String businessHours;

    @Schema(description = "状态")
    private String state;

    @Schema(description = "联系方式")
    private String phone;

    @Schema(description = "经度")
    private String latitude;

    @Schema(description = "纬度")
    private String longitude;

    @Schema(description = "环保检验编号")
    private String bhjybh;

    @Schema(description = "监督电话")
    private String supervisePhone;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "机构名称")
    private String jyjbmc;

    @Schema(description = "所属行政区规划")
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
    private String checkType;

}