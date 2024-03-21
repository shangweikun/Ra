package com.swk.demo7.services;

import com.swk.demo7.entity.DTO1;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface DTOValidateService1 {

    void check(@Valid DTO1 dto) throws Exception;
}
