package com.xs.sw.veh.module.crm.dal.mysql.contactbusinesslink;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.crm.controller.admin.contactbusinesslink.vo.CrmContactBusinessLinkPageReqVO;
import com.xs.sw.veh.module.crm.dal.dataobject.contactbusinesslink.CrmContactBusinessLinkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * CRM 联系人商机关联 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CrmContactBusinessLinkMapper extends BaseMapperX<CrmContactBusinessLinkDO> {

    default PageResult<CrmContactBusinessLinkDO> selectPage(CrmContactBusinessLinkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmContactBusinessLinkDO>()
                .eqIfPresent(CrmContactBusinessLinkDO::getContactId, reqVO.getContactId())
                .eqIfPresent(CrmContactBusinessLinkDO::getBusinessId, reqVO.getBusinessId())
                .betweenIfPresent(CrmContactBusinessLinkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CrmContactBusinessLinkDO::getId));
    } // TODO @zyna：方法和方法之间要有空行
    default PageResult<CrmContactBusinessLinkDO> selectPageByContact(CrmContactBusinessLinkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CrmContactBusinessLinkDO>()
                .eqIfPresent(CrmContactBusinessLinkDO::getContactId, reqVO.getContactId())
                .orderByDesc(CrmContactBusinessLinkDO::getId));
    }
}