package com.xs.sw.veh.module.system.convert.logger;

import com.xs.sw.veh.framework.common.util.collection.CollectionUtils;
import com.xs.sw.veh.framework.common.util.collection.MapUtils;
import com.xs.sw.veh.framework.common.util.object.BeanUtils;
import com.xs.sw.veh.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.xs.sw.veh.module.system.dal.dataobject.logger.OperateLogDO;
import com.xs.sw.veh.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperateLogConvert {

    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    default List<OperateLogRespVO> convertList(List<OperateLogDO> list, Map<Long, AdminUserDO> userMap) {
        return CollectionUtils.convertList(list, log -> {
            OperateLogRespVO logVO = BeanUtils.toBean(log, OperateLogRespVO.class);
            MapUtils.findAndThen(userMap, log.getUserId(), user -> logVO.setUserNickname(user.getNickname()));
            return logVO;
        });
    }

}
