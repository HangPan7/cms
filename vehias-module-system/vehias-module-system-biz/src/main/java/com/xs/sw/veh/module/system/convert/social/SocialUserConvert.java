package com.xs.sw.veh.module.system.convert.social;

import com.xs.sw.veh.module.system.api.social.dto.SocialUserBindReqDTO;
import com.xs.sw.veh.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    @Mapping(source = "reqVO.type", target = "socialType")
    SocialUserBindReqDTO convert(Long userId, Integer userType, SocialUserBindReqVO reqVO);

}
