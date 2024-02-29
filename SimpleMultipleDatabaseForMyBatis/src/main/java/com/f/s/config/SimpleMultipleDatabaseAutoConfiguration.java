package com.f.s.config;

import com.f.s.enhance.MapperFactoryBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMultipleDatabaseAutoConfiguration {

    @Bean
    public MapperFactoryBeanPostProcessor mapperFactoryBeanPostProcessor(){
        return new MapperFactoryBeanPostProcessor();
    }
}
