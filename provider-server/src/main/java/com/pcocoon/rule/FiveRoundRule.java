package com.pcocoon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description:[5次轮询规则]</p>
 * Created on: 2020/9/17 21:47
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
@Slf4j
public class FiveRoundRule extends AbstractLoadBalancerRule {
    private AtomicInteger nextServerIndex;
    private AtomicInteger currentServerCount;

    public FiveRoundRule() {
        this.nextServerIndex = new AtomicInteger(0);
        this.currentServerCount = new AtomicInteger(0);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = this.getLoadBalancer();
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();
            if ((upCount == 0) || (serverCount == 0)) { return null; }
            int nextServerIndex = getNextServerIndex(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                currentServerCount.incrementAndGet();
                return (server);
            }
            // Next.
            server = null;
        }
        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: " + lb);
        }
        return server;
    }

    private int getNextServerIndex(int serverCount) {
        int next = 0;
        if (currentServerCount.get() > 5) {
            currentServerCount.compareAndSet(6, 0);
            next = (nextServerIndex.get() + 1) % serverCount;
        }
        nextServerIndex.compareAndSet(nextServerIndex.get(), next);
        return next;
    }
}


