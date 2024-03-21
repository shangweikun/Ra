package com.swk.demo7.resource;

import com.swk.demo7.entity.DTO;
import com.swk.demo7.services.DTOService;
import com.swk.demo7.services.DTOValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DTOController {

    @Resource
    private DTOService dtoService;

    @Resource
    private DTOValidateService dtoValidateService;

    @PostMapping("/test")
    ResponseEntity<String> test(@RequestBody DTO dto) throws Exception {
        // persisting the user
        dtoService.check(dto);
        return ResponseEntity.ok("DTO is valid");
    }

    @PostMapping("/test1")
    ResponseEntity<String> test1(@RequestBody DTO dto) throws Exception {
        // persisting the user
        dtoValidateService.check(dto);
        return ResponseEntity.ok("2 DTO is valid");
    }


}
