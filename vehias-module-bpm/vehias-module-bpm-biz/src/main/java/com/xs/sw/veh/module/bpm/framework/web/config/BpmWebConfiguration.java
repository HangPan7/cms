package com.xs.sw.veh.module.bpm.framework.web.config;

import com.xs.sw.veh.framework.swagger.config.VehiasSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bpm 模块的 web 组件的 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class BpmWebConfiguration {

    /**
     * bpm 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi bpmGroupedOpenApi() {
        return VehiasSwaggerAutoConfiguration.buildGroupedOpenApi("bpm");
    }

}
