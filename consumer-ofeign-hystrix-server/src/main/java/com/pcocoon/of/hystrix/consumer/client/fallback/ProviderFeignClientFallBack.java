package com.pcocoon.of.hystrix.consumer.client.fallback;

import com.pcocoon.entity.RestResult;
import com.pcocoon.of.hystrix.consumer.client.ProviderHystrixClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>Description:[消费服务熔断实现]</p>
 * Created on: 2020/9/19 13:33
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Component
@Slf4j
public class ProviderFeignClientFallBack implements ProviderHystrixClient {
    @Override
    public RestResult<String> hello() {
        return RestResult.renderError("服务异常！请联系Provider");
    }

    @Override
    public RestResult<String> hi() {
        return null;
    }


}
