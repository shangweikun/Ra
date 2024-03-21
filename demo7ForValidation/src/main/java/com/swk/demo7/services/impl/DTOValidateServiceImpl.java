package com.swk.demo7.services.impl;

import com.swk.demo7.entity.DTO;
import com.swk.demo7.services.DTOValidateService;
import org.springframework.stereotype.Service;

@Service
public class DTOValidateServiceImpl implements DTOValidateService {

    @Override
    public void check(DTO dto) throws Exception {
        System.out.println("dto is ok, this is DTOValidateServiceImpl");
    }
}
