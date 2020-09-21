package com.pcocoon.of.hystrix.consumer.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description:[服务调用配置]</p>
 * Created on: 2020/9/18 16:24
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Configuration
public class FeignConfiguration {

    /**
     * 超时策略
     */
    @Bean
    Request.Options requestOptions() {
        //connectTimeout:       建立连接后从服务区读取到可用资源所用的时间
        //connectTimeoutUnit:   时间单位
        //readTimeout:          建立连接所用的时间适用于网络正常情况下两端连接所用的时间
        //readTimeoutUnit:      时间单位
        //followRedirects:      是否响应3xx重定向
        return new Request.Options(3000, TimeUnit.MILLISECONDS, 3000, TimeUnit.MILLISECONDS, false);
    }

    /**
     * 服务调用日志
     *
     * @return
     */
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

}

