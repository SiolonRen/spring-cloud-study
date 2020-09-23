package com.pcocoon.of.hystrix.consumer.controller;

import com.pcocoon.entity.RestResult;
import com.pcocoon.of.hystrix.consumer.client.ProviderClient;
import com.pcocoon.of.hystrix.consumer.client.ProviderHystrixClient;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:[测试访问控制器]</p>
 * Created on: 2020/9/15 22:10
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@RestController
@Slf4j
public class StudyController {

    @Autowired
    private ProviderHystrixClient providerHystrixClient;

    @Autowired
    private ProviderClient providerClient;


    @GetMapping("/hystrix/hello")
    public RestResult hystrixHello() {
        log.info("啊啊啊啊啊，我被请求了！");
        return providerHystrixClient.hello();
    }

    @GetMapping("/hystrix/hi")
    public RestResult hystrixHi() {
        return providerHystrixClient.hi();
    }

    @GetMapping("/hello")
    public RestResult hello() {
        log.info("啊啊啊啊啊，我被请求了！");
        return providerClient.hello();
    }

    @GetMapping("/hi")
    public RestResult hi() {
        return providerClient.hi();
    }



}


