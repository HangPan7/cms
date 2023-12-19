package com.xs.sw.veh.module.pay.job.order;

import cn.hutool.core.util.StrUtil;
import com.xs.sw.veh.framework.quartz.core.handler.JobHandler;
import com.xs.sw.veh.framework.tenant.core.job.TenantJob;
import com.xs.sw.veh.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 支付订单的过期 Job
 *
 * 支付超过过期时间时，支付渠道是不会通知进行过期，所以需要定时进行过期关闭。
 *
 * @author 芋道源码
 */
@Component
public class PayOrderExpireJob implements JobHandler {

    @Resource
    private PayOrderService orderService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = orderService.expireOrder();
        return StrUtil.format("支付过期 {} 个", count);
    }

}
