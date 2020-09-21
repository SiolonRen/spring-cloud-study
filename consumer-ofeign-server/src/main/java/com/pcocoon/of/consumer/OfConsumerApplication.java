package com.pcocoon.of.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
@EnableFeignClients
public class OfConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfConsumerApplication.class, args);
    }

}
