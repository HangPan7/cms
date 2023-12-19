package com.xs.sw.veh.module.pay.framework.web.config;

import com.xs.sw.veh.framework.swagger.config.VehiasSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pay 模块的 web 组件的 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class PayWebConfiguration {

    /**
     * pay 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi payGroupedOpenApi() {
        return VehiasSwaggerAutoConfiguration.buildGroupedOpenApi("pay");
    }

}
