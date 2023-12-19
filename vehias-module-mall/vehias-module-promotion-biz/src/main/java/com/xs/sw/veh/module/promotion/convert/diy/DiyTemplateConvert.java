package com.xs.sw.veh.module.promotion.convert.diy;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.promotion.controller.admin.diy.vo.template.*;
import com.xs.sw.veh.module.promotion.controller.app.diy.vo.AppDiyTemplatePropertyRespVO;
import com.xs.sw.veh.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.xs.sw.veh.module.promotion.dal.dataobject.diy.DiyTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 装修模板 Convert
 *
 * @author owen
 */
@Mapper
public interface DiyTemplateConvert {

    DiyTemplateConvert INSTANCE = Mappers.getMapper(DiyTemplateConvert.class);

    DiyTemplateDO convert(DiyTemplateCreateReqVO bean);

    DiyTemplateDO convert(DiyTemplateUpdateReqVO bean);

    DiyTemplateRespVO convert(DiyTemplateDO bean);

    List<DiyTemplateRespVO> convertList(List<DiyTemplateDO> list);

    PageResult<DiyTemplateRespVO> convertPage(PageResult<DiyTemplateDO> page);

    DiyTemplatePropertyRespVO convertPropertyVo(DiyTemplateDO diyTemplate, List<DiyPageDO> pages);

    AppDiyTemplatePropertyRespVO convertPropertyVo2(DiyTemplateDO diyTemplate, String home, String user);

    DiyTemplateDO convert(DiyTemplatePropertyUpdateRequestVO updateReqVO);

}
