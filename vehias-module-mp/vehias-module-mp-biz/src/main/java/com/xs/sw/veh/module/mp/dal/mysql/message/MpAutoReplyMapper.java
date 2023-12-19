package com.xs.sw.veh.module.mp.dal.mysql.message;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.mp.controller.admin.message.vo.message.MpMessagePageReqVO;
import com.xs.sw.veh.module.mp.dal.dataobject.message.MpAutoReplyDO;
import com.xs.sw.veh.module.mp.enums.message.MpAutoReplyMatchEnum;
import com.xs.sw.veh.module.mp.enums.message.MpAutoReplyTypeEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MpAutoReplyMapper extends BaseMapperX<MpAutoReplyDO> {

    default PageResult<MpAutoReplyDO> selectPage(MpMessagePageReqVO pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAccountId, pageVO.getAccountId())
                .eqIfPresent(MpAutoReplyDO::getType, pageVO.getType()));
    }

    default List<MpAutoReplyDO> selectListByAppIdAndKeywordAll(String appId, String requestKeyword) {
        return selectList(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAppId, appId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.KEYWORD.getType())
                .eq(MpAutoReplyDO::getRequestMatch, MpAutoReplyMatchEnum.ALL.getMatch())
                .eq(MpAutoReplyDO::getRequestKeyword, requestKeyword));
    }

    default List<MpAutoReplyDO> selectListByAppIdAndKeywordLike(String appId, String requestKeyword) {
        return selectList(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAppId, appId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.KEYWORD.getType())
                .eq(MpAutoReplyDO::getRequestMatch, MpAutoReplyMatchEnum.LIKE.getMatch())
                .like(MpAutoReplyDO::getRequestKeyword, requestKeyword));
    }

    default List<MpAutoReplyDO> selectListByAppIdAndMessage(String appId, String requestMessageType) {
        return selectList(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAppId, appId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.MESSAGE.getType())
                .eq(MpAutoReplyDO::getRequestMessageType, requestMessageType));
    }

    default List<MpAutoReplyDO> selectListByAppIdAndSubscribe(String appId) {
        return selectList(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAppId, appId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.SUBSCRIBE.getType()));
    }

    default MpAutoReplyDO selectByAccountIdAndSubscribe(Long accountId) {
        return selectOne(MpAutoReplyDO::getAccountId, accountId,
                MpAutoReplyDO::getType, MpAutoReplyTypeEnum.SUBSCRIBE.getType());
    }

    default MpAutoReplyDO selectByAccountIdAndMessage(Long accountId, String requestMessageType) {
        return selectOne(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAccountId, accountId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.MESSAGE.getType())
                .eq(MpAutoReplyDO::getRequestMessageType, requestMessageType));
    }

    default MpAutoReplyDO selectByAccountIdAndKeyword(Long accountId, String requestKeyword) {
        return selectOne(new LambdaQueryWrapperX<MpAutoReplyDO>()
                .eq(MpAutoReplyDO::getAccountId, accountId)
                .eq(MpAutoReplyDO::getType, MpAutoReplyTypeEnum.KEYWORD.getType())
                .eq(MpAutoReplyDO::getRequestKeyword, requestKeyword));
    }
}