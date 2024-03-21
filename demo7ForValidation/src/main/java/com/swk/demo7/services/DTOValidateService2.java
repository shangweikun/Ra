package com.swk.demo7.services;

import com.swk.demo7.entity.DTO1;

import javax.validation.Valid;

public interface DTOValidateService2 {

    void check(@Valid DTO1 dto) throws Exception;
}
