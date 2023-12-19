package com.xs.sw.veh.module.pay.convert.demo;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.xs.sw.veh.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import com.xs.sw.veh.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jason
 */
@Mapper
public interface PayDemoTransferConvert {

    PayDemoTransferConvert INSTANCE = Mappers.getMapper(PayDemoTransferConvert.class);

    PayDemoTransferDO convert(PayDemoTransferCreateReqVO bean);

    PageResult<PayDemoTransferRespVO> convertPage(PageResult<PayDemoTransferDO> pageResult);
}
