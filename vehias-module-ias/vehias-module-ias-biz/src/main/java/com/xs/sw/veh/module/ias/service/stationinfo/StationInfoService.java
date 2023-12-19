package com.xs.sw.veh.module.ias.service.stationinfo;

import java.util.*;
import javax.validation.*;
import com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo.*;
import com.xs.sw.veh.module.ias.dal.dataobject.stationinfo.StationInfoDO;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.pojo.PageParam;

/**
 * 机构 Service 接口
 *
 * @author 芋道源码
 */
public interface StationInfoService {

    /**
     * 创建机构
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStationInfo(@Valid StationInfoSaveReqVO createReqVO);

    /**
     * 更新机构
     *
     * @param updateReqVO 更新信息
     */
    void updateStationInfo(@Valid StationInfoSaveReqVO updateReqVO);

    /**
     * 删除机构
     *
     * @param id 编号
     */
    void deleteStationInfo(Long id);

    /**
     * 获得机构
     *
     * @param id 编号
     * @return 机构
     */
    StationInfoDO getStationInfo(Long id);

    /**
     * 获得机构分页
     *
     * @param pageReqVO 分页查询
     * @return 机构分页
     */
    PageResult<StationInfoDO> getStationInfoPage(StationInfoPageReqVO pageReqVO);

}