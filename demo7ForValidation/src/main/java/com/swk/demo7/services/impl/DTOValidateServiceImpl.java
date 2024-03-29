package com.swk.demo7.services.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.swk.demo7.entity.DTO;
import com.swk.demo7.handlers.MyHandler;
import com.swk.demo7.services.DTOService;
import com.swk.demo7.services.DTOValidateService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class DTOValidateServiceImpl implements DTOValidateService {

    @Override
    public void check(DTO dto) throws Exception {
        String classStr = "com.swk.demo7.services.DTOValidateService";
        Class<?> clazz = Class.forName(classStr);
        Object obj = SpringUtil.getBean(clazz);
        String inParam = "com.swk.demo7.entity.DTO";
        Class<?> aClass = Class.forName(inParam);
        Method method = clazz.getMethod("check", aClass);
        MyHandler.assertCheckpoint(obj, method, new Object[]{dto});

        System.out.println("dto is ok, this is DTOValidateServiceImpl");
    }
}
