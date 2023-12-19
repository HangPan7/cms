package com.xs.sw.veh.framework.idempotent.config;

import com.xs.sw.veh.framework.idempotent.core.aop.IdempotentAspect;
import com.xs.sw.veh.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.xs.sw.veh.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.xs.sw.veh.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.xs.sw.veh.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.xs.sw.veh.framework.redis.config.VehiasRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = VehiasRedisAutoConfiguration.class)
public class VehiasIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
