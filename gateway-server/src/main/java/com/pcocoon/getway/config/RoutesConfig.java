package com.pcocoon.getway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:[路由转发规则-Bean实现]</p>
 * Created on: 2020/9/22 18:08
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder router) {
        return router.routes()
                //转发百度
                .route("www.baidu.com",
                        r -> r.path("/s")
                                .uri("https://www.baidu.com"))
                //转发ofHystrixConsumer
                .route("cloud-study-of-hystrix-consumer",
                        r -> r.path("/ofHystrix/consumer/**")
                                .uri("lb://cloud-study-of-hystrix-consumer"))
                .build();
    }
}
