package com.xs.sw.veh.module.report.framework.security.config;

import com.xs.sw.veh.framework.common.enums.WebFilterOrderEnum;
import com.xs.sw.veh.framework.security.config.AuthorizeRequestsCustomizer;
import com.xs.sw.veh.framework.security.config.SecurityProperties;
import com.xs.sw.veh.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Report 模块的 Security 配置
 */
@Configuration("reportSecurityConfiguration")
public class SecurityConfiguration {

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Bean("reportAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                registry.antMatchers("/jmreport/**").permitAll(); // 积木报表
                registry.antMatchers("/ureport/**").permitAll(); // UReport 报表
            }

        };
    }


    /**
     * 创建 UReportFilter 过滤器，响应 header 设置 token
     */
    /*@Bean
    public FilterRegistrationBean<UReportFilter> uReportFilterFilterRegistrationBean() {
        FilterRegistrationBean<UReportFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UReportFilter(oauth2TokenApi));
        registrationBean.setOrder(WebFilterOrderEnum.TRACE_FILTER);
        return registrationBean;
    }*/

}
