package com.pcocoon.of.consumer.client;

import com.pcocoon.entity.RestResult;
import com.pcocoon.entity.Sender;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description:[Provider服务客户端]</p>
 * Created on: 2020/9/18 11:32
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Component
@FeignClient(name = "cloud-study-provider")
public interface ProviderFeignClient {

    /**
     * 基本Get请求
     */
    @GetMapping("/hello")
    RestResult<String> hello();

    /**
     * Restful风格Get请求
     */
    @GetMapping("/hello/{name}")
    RestResult hello(@PathVariable("name") String name);

    /**
     * 拼接参数Get请求
     */
    @GetMapping("/bye")
    RestResult bye(@RequestParam("name") String name);


    /**
     * Post请求,form-data方式
     */
    @PostMapping("/addForParam")
    RestResult addForParam(@RequestParam("id") int id, @RequestParam("name") String name);


    /**
     * Post请求,application/json方式
     */
    @PostMapping("/addForJson")
    RestResult addForJson(@RequestBody Sender sender);

}
