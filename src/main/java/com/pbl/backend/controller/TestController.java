package com.pbl.backend.controller;

import com.pbl.backend.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test")
    public Result get(){
        String psw = "123456";
        return Result.SUCCESS(bCryptPasswordEncoder.encode(psw));
    }
}