package com.pcocoon.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Description:[服务启动类]</p>
 * Created on: 2020/9/15 21:48
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
