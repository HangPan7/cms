package com.xs.sw.veh.module.pay.convert.transfer;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.pay.core.client.dto.transfer.PayTransferUnifiedReqDTO;
import com.xs.sw.veh.module.pay.api.transfer.dto.PayTransferCreateReqDTO;
import com.xs.sw.veh.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import com.xs.sw.veh.module.pay.controller.admin.transfer.vo.PayTransferCreateReqVO;
import com.xs.sw.veh.module.pay.controller.admin.transfer.vo.PayTransferPageItemRespVO;
import com.xs.sw.veh.module.pay.controller.admin.transfer.vo.PayTransferRespVO;
import com.xs.sw.veh.module.pay.dal.dataobject.transfer.PayTransferDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransferConvert {

    PayTransferConvert INSTANCE = Mappers.getMapper(PayTransferConvert.class);

    PayTransferDO convert(PayTransferCreateReqDTO dto);

    PayTransferUnifiedReqDTO convert2(PayTransferDO dto);

    PayTransferCreateReqDTO convert(PayTransferCreateReqVO vo);

    PayTransferCreateReqDTO convert(PayDemoTransferCreateReqVO vo);

    PayTransferRespVO  convert(PayTransferDO bean);

    PageResult<PayTransferPageItemRespVO> convertPage(PageResult<PayTransferDO> pageResult);
}
