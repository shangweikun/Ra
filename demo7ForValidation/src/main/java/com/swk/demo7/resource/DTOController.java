package com.swk.demo7.resource;

import com.swk.demo7.entity.DTO;
import com.swk.demo7.entity.DTO1;
import com.swk.demo7.services.DTOService;
import com.swk.demo7.services.DTOValidateService;
import com.swk.demo7.services.DTOValidateService1;
import com.swk.demo7.services.DTOValidateService2;
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

    @Resource
    private DTOValidateService1 dtoValidateService1;

    @Resource
    private DTOValidateService2 dtoValidateService2;

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
        return ResponseEntity.ok("1 DTO is valid");
    }

    @PostMapping("/test2")
    ResponseEntity<String> test2(@RequestBody DTO1 dto) throws Exception {
        // persisting the user
        dtoValidateService1.check(dto);
        return ResponseEntity.ok("2 DTO is valid");
    }

    @PostMapping("/test3")
    ResponseEntity<String> test3(@RequestBody DTO1 dto) throws Exception {
        // persisting the user
        dtoValidateService2.check(dto);
        return ResponseEntity.ok("2 DTO is valid");
    }


}
