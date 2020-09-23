package com.pcocoon.getway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:[限流配置]</p>
 * Created on: 2020/9/23 17:40
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Configuration
public class CurrentLimitConfig {

    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }
}
