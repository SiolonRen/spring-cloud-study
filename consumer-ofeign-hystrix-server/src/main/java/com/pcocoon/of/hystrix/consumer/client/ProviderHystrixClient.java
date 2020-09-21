package com.pcocoon.of.hystrix.consumer.client;

import com.pcocoon.entity.RestResult;
import com.pcocoon.of.hystrix.consumer.client.fallback.ProviderFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Description:[Provider服务客户端]</p>
 * Created on: 2020/9/18 11:32
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Component
@FeignClient(name = "cloud-study-hystrix-provider", fallback = ProviderFeignClientFallBack.class)
public interface ProviderHystrixClient {

    /**
     * 基本Get请求
     */
    @GetMapping("/hystrix/hello")
    RestResult<String> hello();


    @GetMapping("/hystrix/hi")
    RestResult<String> hi();
}
