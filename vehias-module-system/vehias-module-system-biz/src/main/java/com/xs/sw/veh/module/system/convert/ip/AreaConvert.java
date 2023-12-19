package com.xs.sw.veh.module.system.convert.ip;

import com.xs.sw.veh.framework.ip.core.Area;
import com.xs.sw.veh.module.system.controller.admin.ip.vo.AreaNodeRespVO;
import com.xs.sw.veh.module.system.controller.app.ip.vo.AppAreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AreaNodeRespVO> convertList(List<Area> list);

    List<AppAreaNodeRespVO> convertList3(List<Area> list);

}
