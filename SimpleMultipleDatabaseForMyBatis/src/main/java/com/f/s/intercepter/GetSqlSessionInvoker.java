package com.f.s.intercepter;

import cn.hutool.core.collection.ListUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class GetSqlSessionInvoker implements MethodInterceptor {

    private final Object target;

    private final SqlSessionFactory sqlSessionFactory;

    private final Class<?> interfaceClass;

    private final String interceptorType;

    private final List<String> methodFullNames;

    public GetSqlSessionInvoker(Object target,
                                SqlSessionFactory sqlSessionFactory,
                                Class<?> interfaceClass, String interceptorType, List<String> methodFullNames) {
        this.target = target;
        this.sqlSessionFactory = sqlSessionFactory;
        this.interfaceClass = interfaceClass;
        this.interceptorType = interceptorType;
        this.methodFullNames = methodFullNames;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("getObject")) {

            if ("2".equals(this.interceptorType)) {
                return new MySqlSessionTemplate(
                        ((MapperFactoryBean<?>) target).getSqlSessionFactory(),
                        sqlSessionFactory,
                        interfaceClass.getSimpleName().equals("Demo1Mapper") ?
                                ListUtil.list(false, "org.example.tmp0.mapper.Demo1Mapper.getCount") :
                                methodFullNames
                ).getMapper(interfaceClass);
            } else {

                return new SqlSessionTemplate(sqlSessionFactory).getMapper(interfaceClass);
            }

        }
        return method.invoke(target, objects);
    }
}
