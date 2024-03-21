package com.swk.demo7.services;

import com.swk.demo7.entity.DTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface DTOValidateService {

    void check(@Valid DTO dto) throws Exception;
}
