package com.pcocoon.getway.config;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>Description:[自定义断言处理器]</p>
 * Created on: 2020/9/23 10:50
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Component
public class TokenRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public static final String TOKEN_KEY = "token";

    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(TOKEN_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String token = serverWebExchange.getRequest().getQueryParams().getFirst(TOKEN_KEY);
                return config.token.equals(token);
            }
        };
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }


    public static class Config {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}




