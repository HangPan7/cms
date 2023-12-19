package com.xs.sw.veh.module.pay.convert.channel;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import com.xs.sw.veh.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import com.xs.sw.veh.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import com.xs.sw.veh.module.pay.dal.dataobject.channel.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(com.xs.sw.veh.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);

}
