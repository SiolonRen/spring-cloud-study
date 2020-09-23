package com.pcocoon.consumer.controller;

import com.pcocoon.entity.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Description:[测试访问控制器]</p>
 * Created on: 2020/9/15 22:10
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@RestController
public class StudyController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/hello")
    public RestResult hello() {
        ResponseEntity<RestResult> entity = restTemplate.getForEntity("http://CLOUD-STUDY-PROVIDER/hello", RestResult.class);
        return entity.getBody();
    }
}
