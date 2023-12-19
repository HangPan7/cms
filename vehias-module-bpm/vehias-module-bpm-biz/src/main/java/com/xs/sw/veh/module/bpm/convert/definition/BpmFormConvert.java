package com.xs.sw.veh.module.bpm.convert.definition;

import com.xs.sw.veh.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import com.xs.sw.veh.module.bpm.controller.admin.definition.vo.form.BpmFormRespVO;
import com.xs.sw.veh.module.bpm.controller.admin.definition.vo.form.BpmFormSimpleRespVO;
import com.xs.sw.veh.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import com.xs.sw.veh.module.bpm.dal.dataobject.definition.BpmFormDO;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动态表单 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface BpmFormConvert {

    BpmFormConvert INSTANCE = Mappers.getMapper(BpmFormConvert.class);

    BpmFormDO convert(BpmFormCreateReqVO bean);

    BpmFormDO convert(BpmFormUpdateReqVO bean);

    BpmFormRespVO convert(BpmFormDO bean);

    List<BpmFormSimpleRespVO> convertList2(List<BpmFormDO> list);

    PageResult<BpmFormRespVO> convertPage(PageResult<BpmFormDO> page);

}
