package com.swk.demo7.services.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.swk.demo7.classloaders.MyClassLoader;
import com.swk.demo7.entity.DTO1;
import com.swk.demo7.handlers.MyHandler;
import com.swk.demo7.services.DTOValidateService2;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class DTOValidateServiceImpl2 implements DTOValidateService2 {

    @Override
    public void check(DTO1 dto) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        String classStr = "com.swk.demo7.services.DTOValidateService2";
        Class<?> clazz = myClassLoader.loadClass(classStr);
        Object obj = SpringUtil.getBean(clazz);
        String inParam = "com.swk.demo7.entity.DTO1";
        Class<?> aClass = myClassLoader.loadClass(inParam);
        Method method = clazz.getMethod("check", aClass);
        MyHandler.assertCheckpoint(obj, method, new Object[]{dto});

        System.out.println("dto is ok, this is DTOValidateServiceImpl");
    }
}
