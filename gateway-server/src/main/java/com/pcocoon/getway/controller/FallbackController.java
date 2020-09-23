package com.pcocoon.getway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:[熔断方法]</p>
 * Created on: 2020/9/23 15:41
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@RestController
public class FallbackController {

    @RequestMapping(value = "/fallback")
    public String fallback(){
        return "fallback nothing";
    }

}
