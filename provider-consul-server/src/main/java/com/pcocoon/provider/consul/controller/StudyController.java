package com.pcocoon.provider.consul.controller;

import com.pcocoon.entity.RestResult;
import org.springframework.beans.factory.annotation.Value;
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
public class StudyController {

    @Value("${server.port}")
    private String port;


    @GetMapping("/hello")
    public RestResult hello() {
        return RestResult.renderSuccess("hello" + port);
    }

}
