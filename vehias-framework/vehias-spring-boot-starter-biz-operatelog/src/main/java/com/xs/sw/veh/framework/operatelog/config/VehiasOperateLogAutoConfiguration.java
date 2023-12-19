package com.xs.sw.veh.framework.operatelog.config;

import com.xs.sw.veh.framework.operatelog.core.aop.OperateLogAspect;
import com.xs.sw.veh.framework.operatelog.core.service.OperateLogFrameworkService;
import com.xs.sw.veh.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.xs.sw.veh.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class VehiasOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
