package com.xs.sw.veh.module.pay.dal.mysql.notify;

import com.xs.sw.veh.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
