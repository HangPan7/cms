package com.xs.sw.veh.module.pay.dal.mysql.demo;

import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDemoTransferMapper extends BaseMapperX<PayDemoTransferDO> {

    default  PageResult<PayDemoTransferDO> selectPage(PageParam pageParam){
        return selectPage(pageParam, new LambdaQueryWrapperX<PayDemoTransferDO>()
                .orderByDesc(PayDemoTransferDO::getId));
    }
}