package com.xs.sw.veh.module.pay.dal.mysql.wallet;


import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.mybatis.core.mapper.BaseMapperX;
import com.xs.sw.veh.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xs.sw.veh.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import com.xs.sw.veh.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

@Mapper
public interface PayWalletTransactionMapper extends BaseMapperX<PayWalletTransactionDO> {

    default PageResult<PayWalletTransactionDO> selectPage(Long walletId, Integer type,
                                                          PageParam pageParam) {
        LambdaQueryWrapperX<PayWalletTransactionDO> query = new LambdaQueryWrapperX<PayWalletTransactionDO>()
                .eqIfPresent(PayWalletTransactionDO::getWalletId, walletId);
        if (Objects.equals(type, AppPayWalletTransactionPageReqVO.TYPE_INCOME)) {
            query.gt(PayWalletTransactionDO::getPrice, 0);
        } else if (Objects.equals(type, AppPayWalletTransactionPageReqVO.TYPE_EXPENSE)) {
            query.lt(PayWalletTransactionDO::getPrice, 0);
        }
        query.orderByDesc(PayWalletTransactionDO::getId);
        return selectPage(pageParam, query);
    }

    default PayWalletTransactionDO selectByNo(String no) {
        return selectOne(PayWalletTransactionDO::getNo, no);
    }

    default PayWalletTransactionDO selectByBiz(String bizId, Integer bizType) {
        return selectOne(PayWalletTransactionDO::getBizId, bizId,
                PayWalletTransactionDO::getBizType, bizType);
    }

}




