package com.xs.sw.veh.module.ias.dal.mysql.stationinfo;

import java.util.*;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.module.ias.dal.dataobject.stationinfo.StationInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo.*;

/**
 * 机构 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StationInfoMapper extends BaseMapperX<StationInfoDO> {

    default PageResult<StationInfoDO> selectPage(StationInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StationInfoDO>()
                .eqIfPresent(StationInfoDO::getJyjgbh, reqVO.getJyjgbh())
                .likeIfPresent(StationInfoDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(StationInfoDO::getBusinessHours, reqVO.getBusinessHours())
                .eqIfPresent(StationInfoDO::getState, reqVO.getState())
                .eqIfPresent(StationInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(StationInfoDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(StationInfoDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(StationInfoDO::getBhjybh, reqVO.getBhjybh())
                .eqIfPresent(StationInfoDO::getSupervisePhone, reqVO.getSupervisePhone())
                .betweenIfPresent(StationInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(StationInfoDO::getJyjbmc, reqVO.getJyjbmc())
                .eqIfPresent(StationInfoDO::getArea, reqVO.getArea())
                .eqIfPresent(StationInfoDO::getAbility, reqVO.getAbility())
                .eqIfPresent(StationInfoDO::getOnline, reqVO.getOnline())
                .eqIfPresent(StationInfoDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(StationInfoDO::getCity, reqVO.getCity())
                .eqIfPresent(StationInfoDO::getCityId, reqVO.getCityId())
                .eqIfPresent(StationInfoDO::getCheckType, reqVO.getCheckType())
                .orderByDesc(StationInfoDO::getId));
    }

}