/*
 * Copyright 2010-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.f.s.intercepter;

import cn.hutool.core.util.ReflectUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class MySqlSessionTemplate extends SqlSessionTemplate {


    public MySqlSessionTemplate(SqlSessionFactory _1stSessionFactory,
                                SqlSessionFactory _2ndSessionFactory,
                                List<String> methodNames) {
        super(_1stSessionFactory);

        Field sqlSessionProxy = ReflectUtil.getField(SqlSessionTemplate.class, "sqlSessionProxy");
        sqlSessionProxy.setAccessible(true);
        try {
            sqlSessionProxy.set(this, (SqlSession) Proxy.newProxyInstance(MySqlSessionTemplate.class.getClassLoader(),
                    new Class[]{SqlSession.class},
                    new MySqlSessionInterceptor(methodNames, _1stSessionFactory, _2ndSessionFactory)));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Proxy needed to route MyBatis method calls to the proper SqlSession got from Spring's Transaction Manager It also
     * unwraps exceptions thrown by {@code Method#invoke(Object, Object...)} to pass a {@code PersistenceException} to the
     * {@code PersistenceExceptionTranslator}.
     */
    private static class MySqlSessionInterceptor implements InvocationHandler {

        private final List<String> methodNames;

        private final SqlSessionFactory _1stSessionFactory;

        private final SqlSessionFactory _2stSessionFactory;

        private MySqlSessionInterceptor(List<String> methodNames,
                                        SqlSessionFactory _1stSessionFactory,
                                        SqlSessionFactory _2stSessionFactory) {
            this.methodNames = methodNames;
            this._1stSessionFactory = _1stSessionFactory;
            this._2stSessionFactory = _2stSessionFactory;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


            String statement = (String) args[0];

            if (methodNames.contains(statement)) {
                return method.invoke(new SqlSessionTemplate(_2stSessionFactory), args);
            } else {
                return method.invoke(new SqlSessionTemplate(_1stSessionFactory), args);
            }
        }
    }

}
