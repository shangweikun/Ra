package com.swk.demo7.services.impl;


import cn.hutool.extra.spring.SpringUtil;
import com.swk.demo7.entity.DTO;
import com.swk.demo7.handlers.MyHandler;
import com.swk.demo7.services.DTOService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class DTOServiceImpl implements DTOService {


    @Override
    public void check(DTO dto) throws Exception {
        Object obj = SpringUtil.getBean(DTOService.class);
        String inParam = "com.swk.demo7.entity.DTO";
        Class<?> aClass = Class.forName(inParam);
        Method method = DTOService.class.getMethod("check", aClass);
//        MyHandler.assertCheckpoint(obj, method, new Object[]{dto})
        MyHandler.assertCheckpoint1(dto);
    }

}
