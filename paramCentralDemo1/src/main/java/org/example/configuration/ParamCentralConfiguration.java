package org.example.configuration;

import org.example.aop.ParamTableDaoAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParamCentralConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ParamTableDaoAspect paramTableDaoAspect(){
        return new ParamTableDaoAspect();
    }
}
