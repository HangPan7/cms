package com.xs.sw.veh.module.product.convert.property;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.module.product.controller.admin.property.vo.value.ProductPropertyValueCreateReqVO;
import com.xs.sw.veh.module.product.controller.admin.property.vo.value.ProductPropertyValueRespVO;
import com.xs.sw.veh.module.product.controller.admin.property.vo.value.ProductPropertyValueUpdateReqVO;
import com.xs.sw.veh.module.product.dal.dataobject.property.ProductPropertyValueDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 属性值 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductPropertyValueConvert {

    ProductPropertyValueConvert INSTANCE = Mappers.getMapper(ProductPropertyValueConvert.class);

    ProductPropertyValueDO convert(ProductPropertyValueCreateReqVO bean);

    ProductPropertyValueDO convert(ProductPropertyValueUpdateReqVO bean);

    ProductPropertyValueRespVO convert(ProductPropertyValueDO bean);

    List<ProductPropertyValueRespVO> convertList(List<ProductPropertyValueDO> list);

    PageResult<ProductPropertyValueRespVO> convertPage(PageResult<ProductPropertyValueDO> page);

}
