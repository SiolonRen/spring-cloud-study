package com.pcocoon.of.consumer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Arrays;

/**
 * <p>Description:[Feign自定义拦截器]</p>
 * Created on: 2020/9/18 17:04
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
public class FeignTokenInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        template.headers().put("token", Arrays.asList("莫得感情的TOKEN"));
    }
}



