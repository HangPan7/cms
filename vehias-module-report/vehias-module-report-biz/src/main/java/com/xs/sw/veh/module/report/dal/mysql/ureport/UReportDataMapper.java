package com.xs.sw.veh.module.report.dal.mysql.ureport;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.report.controller.admin.ureport.vo.UReportDataPageReqVO;
import com.xs.sw.veh.module.report.dal.dataobject.ureport.UReportDataDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Ureport2报表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UReportDataMapper extends BaseMapperX<UReportDataDO> {

    default PageResult<UReportDataDO> selectPage(UReportDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UReportDataDO>()
                .likeIfPresent(UReportDataDO::getName, reqVO.getName())
                .eqIfPresent(UReportDataDO::getStatus, reqVO.getStatus())
                .eqIfPresent(UReportDataDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(UReportDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UReportDataDO::getId));
    }

    default List<UReportDataDO> selectListByName(String name) {
        return selectList(new LambdaQueryWrapperX<UReportDataDO>()
                .eqIfPresent(UReportDataDO::getName,name));
    }

    default UReportDataDO selectByName(String name){
        return selectOne(new LambdaQueryWrapperX<UReportDataDO>()
                .eqIfPresent(UReportDataDO::getName,name));
    }

    default int deleteByName(String name) {
        return delete(new LambdaQueryWrapperX<UReportDataDO>()
                .eqIfPresent(UReportDataDO::getName,name));
    }

}
