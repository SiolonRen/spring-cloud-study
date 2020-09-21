package com.pcocoon.of.consumer.controller;

import com.pcocoon.entity.RestResult;
import com.pcocoon.entity.Sender;
import com.pcocoon.of.consumer.client.ProviderFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ProviderFeignClient providerFeignClient;


    @GetMapping("/hello")
    public RestResult hello() {
        return providerFeignClient.hello();
    }

    @GetMapping("/hello/{name}")
    public RestResult hello(@PathVariable("name") String name) {
        return providerFeignClient.hello(name);
    }

    @GetMapping("/bye")
    public RestResult bye(String name) {
        return providerFeignClient.bye(name);
    }

    @GetMapping("/addForParam")
    public RestResult addForParam() {
        return providerFeignClient.addForParam(2,"TEST");
    }

    @GetMapping("/addForJson")
    public RestResult addForJson() {
        return providerFeignClient.addForJson(Sender.builder().id(1).name("test").build());
    }

}


