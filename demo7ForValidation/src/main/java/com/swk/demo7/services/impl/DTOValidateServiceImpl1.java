package com.swk.demo7.services.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.swk.demo7.entity.DTO1;
import com.swk.demo7.handlers.MyHandler;
import com.swk.demo7.services.DTOValidateService1;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class DTOValidateServiceImpl1 implements DTOValidateService1 {

    @Override
    public void check(DTO1 dto) throws Exception {
        String classStr = "com.swk.demo7.services.DTOValidateService1";
        Class<?> clazz = Class.forName(classStr);
        Object obj = SpringUtil.getBean(clazz);
        String inParam = "com.swk.demo7.entity.DTO1";
        Class<?> aClass = Class.forName(inParam);
        Method method = clazz.getMethod("check", aClass);
        MyHandler.assertCheckpoint(obj, method, new Object[]{dto});

        System.out.println("dto is ok, this is DTOValidateServiceImpl");
    }
}
