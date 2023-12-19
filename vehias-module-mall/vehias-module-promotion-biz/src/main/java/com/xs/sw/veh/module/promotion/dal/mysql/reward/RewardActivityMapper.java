package com.xs.sw.veh.module.promotion.dal.mysql.reward;

import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.promotion.controller.admin.reward.vo.RewardActivityPageReqVO;
import com.xs.sw.veh.module.promotion.dal.dataobject.reward.RewardActivityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 满减送活动 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RewardActivityMapper extends BaseMapperX<RewardActivityDO> {

    default PageResult<RewardActivityDO> selectPage(RewardActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RewardActivityDO>()
                .likeIfPresent(RewardActivityDO::getName, reqVO.getName())
                .eqIfPresent(RewardActivityDO::getStatus, reqVO.getStatus())
                .orderByDesc(RewardActivityDO::getId));
    }

    default List<RewardActivityDO> selectListByStatus(Collection<Integer> statuses) {
        return selectList(RewardActivityDO::getStatus, statuses);
    }

    default List<RewardActivityDO> selectListByProductScopeAndStatus(Integer productScope, Integer status) {
        return selectList(new LambdaQueryWrapperX<RewardActivityDO>()
                .eq(RewardActivityDO::getProductScope, productScope)
                .eq(RewardActivityDO::getStatus, status));
    }

}
