package com.f.s.enhance;


import com.f.s.directory.MapperDirectory;
import com.f.s.directory.MapperMultipleDatabaseProperty;
import com.f.s.intercepter.GetSqlSessionInvoker;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.Optional;

public class MapperFactoryBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 在FactoryBean初始化之前执行逻辑
        if (bean instanceof MapperFactoryBean) {

            Optional<MapperMultipleDatabaseProperty> byBeanName = MapperDirectory.getByBeanName(beanName);

            if (byBeanName.isPresent() ||
                    ((MapperFactoryBean<?>) bean).getMapperInterface().getSimpleName().equals("Demo1Mapper")) {
                // 对FactoryBean进行增强的逻辑
                System.out.println("Before Initialization: " + beanName);

                MapperMultipleDatabaseProperty property =
                        byBeanName.orElseGet(MapperMultipleDatabaseProperty::new);
                String type = "demo1Mapper".equals(beanName) ? "1" : property.getType();

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(MapperFactoryBean.class);
                enhancer.setCallback(new GetSqlSessionInvoker(
                        bean,
                        getSqlSessionFactory(
                                ((MapperFactoryBean<?>) bean).getMapperInterface(),
                                ((MapperFactoryBean<?>) bean).getSqlSession().getConfiguration()),
                        ((MapperFactoryBean<?>) bean).getMapperInterface(),
                        type,
                        "1".equals(type) ? new ArrayList<>() : property.getMethodFullNames()
                ));
                return enhancer.create();
            }
        }
        return bean;
    }

    public SqlSessionFactory getSqlSessionFactory(Class<?> clazz, Configuration configuration) {


        Environment environment = new Environment("1",
                new SpringManagedTransactionFactory(),
                new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://43.137.37.109:3306/confluence?useSSL=false",
                        "confluence", "159951Sql"));

        Configuration newConfiguration = new Configuration(environment);
        newConfiguration.addMapper(clazz);

        return new DefaultSqlSessionFactory(newConfiguration);
    }
}

