package com.xs.sw.veh.module.ias.dal.dataobject.stationinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.xs.sw.veh.framework.mybatis.core.dataobject.BaseDO;

/**
 * 机构 DO
 *
 * @author 芋道源码
 */
@TableName("ias_station_info")
@KeySequence("ias_station_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationInfoDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 机构编号
     */
    private String jyjgbh;
    /**
     * 检验机构位置名称
     */
    private String locationName;
    /**
     * 营业时间
     */
    private String businessHours;
    /**
     * 状态
     */
    private String state;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 经度
     */
    private String latitude;
    /**
     * 纬度
     */
    private String longitude;
    /**
     * 环保检验编号
     */
    private String bhjybh;
    /**
     * 监督电话
     */
    private String supervisePhone;
    /**
     * 机构名称
     */
    private String jyjbmc;
    /**
     * 所属行政区规划
     */
    private String area;
    /**
     * 检测能力
     */
    private String ability;
    /**
     * 在线
     */
    private Boolean online;
    /**
     * 行政区编号
     */
    private String areaId;
    /**
     * 所属城市
     */
    private String city;
    /**
     * 所属城市id
     */
    private String cityId;
    /**
     * 检查车辆类型
     */
    private String checkType;

}