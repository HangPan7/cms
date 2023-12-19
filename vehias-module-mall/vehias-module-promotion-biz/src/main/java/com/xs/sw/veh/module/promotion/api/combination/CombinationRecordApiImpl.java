package com.xs.sw.veh.module.promotion.api.combination;

import com.xs.sw.veh.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import com.xs.sw.veh.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import com.xs.sw.veh.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import com.xs.sw.veh.module.promotion.convert.combination.CombinationActivityConvert;
import com.xs.sw.veh.module.promotion.dal.dataobject.combination.CombinationRecordDO;
import com.xs.sw.veh.module.promotion.enums.combination.CombinationRecordStatusEnum;
import com.xs.sw.veh.module.promotion.service.combination.CombinationRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static com.xs.sw.veh.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.xs.sw.veh.module.promotion.enums.ErrorCodeConstants.COMBINATION_RECORD_NOT_EXISTS;

/**
 * 拼团活动 API 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class CombinationRecordApiImpl implements CombinationRecordApi {

    @Resource
    private CombinationRecordService recordService;

    @Override
    public void validateCombinationRecord(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        recordService.validateCombinationRecord(userId, activityId, headId, skuId, count);
    }

    @Override
    public CombinationRecordCreateRespDTO createCombinationRecord(CombinationRecordCreateReqDTO reqDTO) {
        return CombinationActivityConvert.INSTANCE.convert4(recordService.createCombinationRecord(reqDTO));
    }

    @Override
    public boolean isCombinationRecordSuccess(Long userId, Long orderId) {
        CombinationRecordDO record = recordService.getCombinationRecord(userId, orderId);
        if (record == null) {
            throw exception(COMBINATION_RECORD_NOT_EXISTS);
        }
        return CombinationRecordStatusEnum.isSuccess(record.getStatus());
    }

    @Override
    public CombinationValidateJoinRespDTO validateJoinCombination(Long userId, Long activityId, Long headId, Long skuId, Integer count) {
        return recordService.validateJoinCombination(userId, activityId, headId, skuId, count);
    }

}
