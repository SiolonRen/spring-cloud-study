package com.pcocoon.provider.controller;

import com.pcocoon.entity.RestResult;
import com.pcocoon.entity.Sender;
import com.pcocoon.provider.tools.ErrorTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

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


    @GetMapping("/hello")
    public RestResult hello() {
        ErrorTools.generateError();
        return RestResult.renderSuccess("hello");
    }

    @GetMapping("/hi")
    public RestResult hi() {
        ErrorTools.generateError();
        return RestResult.renderSuccess("hi");
    }

    @GetMapping("/hello/{name}")
    public RestResult hello(@PathVariable("name") String name) {
        return RestResult.renderSuccess(name);
    }

    @GetMapping("/bye")
    public RestResult bye(String name) {
        return RestResult.renderSuccess(name);
    }

    @PostMapping("/addForEntity")
    public RestResult addForEntity(Sender sender) {
        return RestResult.renderSuccess(sender);
    }

    @PostMapping("/addForParam")
    public RestResult addForParam(Sender sender, HttpServletRequest request) {
        return RestResult.renderSuccess(sender);
    }

    @PostMapping("/addForJson")
    public RestResult addForJson(@RequestBody Sender sender) {
        return RestResult.renderSuccess(sender);
    }




}
