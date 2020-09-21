package com.pcocoon.hystrix.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.pcocoon.entity.RestResult;
import com.pcocoon.hystrix.provider.tools.ErrorTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:[熔断controller]</p>
 * Created on: 2020/9/19 15:14
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */

@RestController
@RequestMapping("/hystrix")
@DefaultProperties(defaultFallback = "defaultFail")
@Slf4j
public class HystrixController {

    @HystrixCommand(fallbackMethod = "helloFail")
    @GetMapping("/hello")
    public RestResult hello() {
        ErrorTools.generateError();
        return RestResult.renderSuccess("hello");
    }

    private RestResult helloFail(Throwable throwable) {
        log.info("==============" + throwable.getMessage());
        return RestResult.renderError("helloFail,cause by:{}", throwable.getMessage());
    }

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiLinkFail")
    public RestResult hi() {
        ErrorTools.generateError();
        return RestResult.renderSuccess("hi");
    }

    @HystrixCommand(fallbackMethod = "hiEndFail")
    private RestResult hiLinkFail(Throwable throwable) {
        log.info("==============" + throwable.getMessage());
        ErrorTools.generateError();
        return RestResult.renderError("hi.link");
    }

    @GetMapping("/bye")
    public RestResult bye() {
        return RestResult.renderSuccess("bye");
    }


    private RestResult hiEndFail(Throwable throwable) {
        log.info("==============" + throwable.getMessage());
        return RestResult.renderError("hiEndFail");
    }


    private RestResult defaultFail(Throwable throwable) {
        return RestResult.renderError(throwable);
    }


    @GetMapping("/config-all")
    @HystrixCommand(
            /*
             * 配置全局唯一标识服务的名称,默认会取被注解的方法名.
             * 需要注意:Hystrix里同一个键的唯一标识并不包括groupKey,建议取一个独一二无的名字,防止多个方法之间因为键重复而互相影响.
             */
            commandKey = "commandConfig",
            /*
             * 配置全局唯一标识服务分组的名称
             * 通过设置分组，Hystrix会根据组来组织和统计命令的告、仪表盘等信息。
             * Hystrix命令默认的线程划分也是根据命令组来实现。默认情况下，Hystrix会让相同组名的命令使用同一个线程池，所以我们需要在创建Hystrix命令时为其指定命令组来实现默认的线程池划分。
             * 此外，Hystrix还提供了通过设置threadPoolKey来对线程池进行设置。建议最好设置该参数，使用threadPoolKey来控制线程池组。
             */
            groupKey = "commandConfig-group",
            /*
             * 用来标识一个线程池,如果没设置的话会取groupKey,很多情况下都是同一个类内的方法在共用同一个线程池.
             * 如果两个共用同一线程池的方法上配置了同样的属性,在第一个方法被执行后线程池的属性就固定了,所以属性会以第一个被执行的方法上的配置为准.
             */
            threadPoolKey = "commandConfig-group",
            /*
             * 异常回调方法
             * 当前方法必须和回调方法定义在同一个类中，因为定义在了同一个类中，所以fackback method可以是public/private均可。
             * 在回调方法的入参中添加Throwable参数可以获取到当前方法的异常栈
             */
            fallbackMethod = "configCallback",
            /*
             * 默认的回调函数，该函数的函数体不能有入参，返回值类型与@HystrixCommand修饰的函数体的返回值一致。
             * 如果指定了fallbackMethod，则fallbackMethod优先级更高。
             */
            defaultFallback = "defaultFail",
            /*
             * 当Hystrix命令被包装成RxJava的Observer异步执行时,此配置指定了Observable被执行的模式.
             * 默认是Observable会在被创建后立刻执行,而ObservableExecutionMode.EAGER模式下,则会产生一个Observable被subscribe后执行.我们常见的命令都是同步执行的,此配置项可以不配置.
             */
            observableExecutionMode = ObservableExecutionMode.EAGER,
            /*
             * 默认Hystrix在执行方法时捕获到异常时执行回退,并统计失败率以修改熔断器的状态,而被忽略的异常则会直接抛到外层,不会执行回退方法,也不会影响熔断器的状态.
             */
            ignoreExceptions = {
                    NullPointerException.class,
                    OutOfMemoryError.class
            },
            /*
             * 将未被忽略的异常包装成另一种异常
             */
            raiseHystrixExceptions = {},
            /*
             * 与此熔断器相关的配置项
             */
            commandProperties = {
                    //===============================线程隔离相关配置项(Isolation)===============================
                    /*
                     * 配置请求隔离的方式,有THREAD(线程池)和SEMAPHORE(信号量)两种,信号量方式高效但配置不灵活,我们一般采用Java里常用的线程池方式.
                     */
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    /*
                     * 是否给方法执行设置超时.
                     */
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    /*
                     * 方法执行超时时间,单位ms;
                     */
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    /*
                     * 是否在方法执行超时时中断方法.
                     * 需要注意在JVM中我们无法强制中断一个线程,如果Hystrix方法里没有处理中断信号的逻辑,那么中断会被忽略
                     */
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    /*
                     * 是否在方法被取消时中断方法.
                     * 需要注意在JVM中我们无法强制中断一个线程,如果Hystrix方法里没有处理中断信号的逻辑,那么中断会被忽略
                     */
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnFutureCancel", value = "false"),
                    /*
                     * 此配置项要在execution.isolation.strategy配置为SEMAPHORE时才会生效.
                     * 它指定了一个Hystrix方法使用信号量隔离时的最大并发数,超过此并发数的请求会被拒绝.信号量隔离的配置就这么一个,也是前文说信号量隔离配置不灵活的原因.
                     */
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    //===============================统计器相关配置项(Metrics)===============================
                    /*
                     * 此配置项指定了窗口的大小,单位是ms
                     */
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000"),
                    /*
                     * 指定健康数据统计器(影响Hystrix熔断)中每个桶的大小
                     * 统计时,Hystrix通过metrics.rollingStats.timeInMilliseconds/metrics.healthSnapshot.intervalInMilliseconds计算出桶数,
                     * 在窗口滑动时,每滑过一个桶的时间间隔时就统计一次当前窗口内请求的失败率.
                     */
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
                    /*
                     * Hystrix会将命令执行的结果类型都统计汇总到一块,给上层应用使用或生成统计图表,此配置项即指定了,生成统计数据流时滑动窗口应该拆分的桶数.
                     * 此配置项最易跟上面的metrics.healthSnapshot.intervalInMilliseconds搞混,认为此项影响健康数据流的桶数.需要保持此值能被metrics.rollingStats.timeInMilliseconds整除.
                     */
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    /*
                     * 是否统计方法响应时间百分比
                     * 值为true时Hystrix会统计方法执行的1%,10%,50%,90%,99%等比例请求的平均耗时用以生成统计图表.
                     */
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "true"),
                    /*
                     * 统计响应时间百分比时的窗口大小,单位ms
                     */
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
                    /*
                     * 统计响应时间百分比时滑动窗口要划分的桶数
                     * 需要保持能被metrics.rollingPercentile.timeInMilliseconds整除.
                     */
                    @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "6"),
                    /*
                     * 统计响应时间百分比时,每个滑动窗口的桶内要保留的请求数,桶内的请求超出这个值后,会覆盖最前面保存的数据.
                     * 在统计响应百分比配置全为默认的情况下,每个桶的时间长度为10s=60000ms/6,但这10s内只保留最近的100条请求的数据.
                     */
                    @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
                    //===============================熔断器相关配置项(CircuitBreaker)===============================
                    /*
                     * 是否启用熔断器
                     */
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    /*
                     * 是否强制启用/关闭熔断器,强制启用/关闭都想不到什么应用的场景,保持默认值,不配置即可.
                     */
                    @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
                    /*
                     * 启用熔断器功能窗口时间内的最小请求数.
                     * 试想如果没有这么一个限制,我们配置了50%的请求失败会打开熔断器,窗口时间内只有3条请求,恰巧两条都失败了,那么熔断器就被打开了,5s内的请求都被快速失败.
                     * 此配置项的值需要根据接口的QPS进行计算,值太小会有误打开熔断器的可能,值太大超出了时间窗口内的总请求数,则熔断永远也不会被触发.建议设置为QPS*窗口秒数*60%.
                     */
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    /*
                     * 在通过滑动窗口获取到当前时间段内Hystrix方法执行的失败率后,就需要根据此配置来判断是否要将熔断器打开了.
                     * 即窗口时间内超过50%的请求失败后会打开熔断器将后续请求快速失败.
                     */
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    /*
                     * 指定熔断器打开后经过多长时间允许一次请求尝试执行
                     * Hystrix会在经过一段时间后就放行一条请求,如果这条请求执行成功了,说明此时服务很可能已经恢复了正常,那么会将熔断器关闭,如果此请求执行失败,则认为服务依然不可用,熔断器继续保持打开状态.
                     */
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    //===============================其他相关配置项(Context/Fallback)===============================
                    /*
                     * 是否启用请求结果缓存,不意味着我们的每个请求都会被缓存.
                     * 缓存请求结果和从缓存中获取结果都需要我们配置cacheKey,并且在方法上使用@CacheResult注解声明一个缓存上下文.
                     */
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),
                    /*
                     * 是否启用请求日志
                     */
                    @HystrixProperty(name = "requestLog.enabled", value = "true"),
                    /*
                     * 是否启用方法回退
                     */
                    @HystrixProperty(name = "fallback.enabled", value = "true"),
                    /*
                     * 回退方法执行时的最大并发数,如果大量请求的回退方法被执行时,超出此并发数的请求会抛出REJECTED_SEMAPHORE_FALLBACK异常.
                     */
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10")
            },
            //与此熔断器线程相关的配置项
            threadPoolProperties = {
                    /*
                     * 核心线程池的大小,一般根据QPS * 99% cost + redundancy count计算得出.
                     */
                    @HystrixProperty(name = "coreSize", value = "10"),
                    /*
                     * 是否允许线程池扩展到最大线程池数量
                     */
                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "false"),
                    /*
                     * 线程池中线程的最大数量
                     * 此配置项单独配置时并不会生效,需要启用allowMaximumSizeToDivergeFromCoreSize项.
                     */
                    @HystrixProperty(name = "maximumSize", value = "10"),
                    /*
                     * 作业队列的最大值
                     * 设置为此值时,队列会使用SynchronousQueue,此时其size为0,Hystrix不会向队列内存放作业.
                     * 如果此值设置为一个正的int型,队列会使用一个固定size的LinkedBlockingQueue,此时在核心线程池内的线程都在忙碌时,会将作业暂时存放在此队列内,但超出此队列的请求依然会被拒绝.
                     */
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    /*
                     * 动态修改队列长度
                     * 即使队列未满,队列内作业达到此值时同样会拒绝请求.所以有时候只设置了maxQueueSize也不会起作用.
                     */
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5"),
                    /*
                     * 线程被回收前的存活时间,单位:M
                     * 由上面的maximumSize,我们知道,线程池内核心线程数目都在忙碌,再有新的请求到达时,线程池容量可以被扩充为到最大数量,等到线程池空闲后,多于核心数量的线程还会被回收
                     */
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "5")
            }
    )
    public RestResult commandConfig() {
        return RestResult.renderSuccess();
    }

}
