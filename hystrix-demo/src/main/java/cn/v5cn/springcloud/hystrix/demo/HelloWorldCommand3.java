package cn.v5cn.springcloud.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * <p>
 *  信号量隔离:SEMAPHORE
 *  隔离本地代码或可快速返回远程调用(如memcached,redis)可以直接使用信号量隔离,降低线程隔离开销.
 * </p>
 *
 * @author ZYW
 * @version v1.0.0
 * @date 2019-08-22 14:06
 */
public class HelloWorldCommand3 extends HystrixCommand<String> {

    private String name;

    protected HelloWorldCommand3(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置信号量隔离方式,默认采用线程池隔离 */
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "HystrixThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        HelloWorldCommand3 command3 = new HelloWorldCommand3("semaphore");
        String result = command3.execute();
        System.out.println("result=" + result);
        System.out.println("MainThread=" + Thread.currentThread().getName());
    }
    /* --------------HystrixCommandProperties统计相关------------------*/
    // 统计滚动的时间窗口,默认:5000毫秒（取自circuitBreakerSleepWindowInMilliseconds）
    /*private final HystrixProperty metricsRollingStatisticalWindowInMilliseconds;
    // 统计窗口的Buckets的数量,默认:10个,每秒一个Buckets统计
    private final HystrixProperty metricsRollingStatisticalWindowBuckets; // number of buckets in the statisticalWindow
    // 是否开启监控统计功能,默认:true
    private final HystrixProperty metricsRollingPercentileEnabled;
    *//* --------------熔断器相关------------------*//*
    // 熔断器在整个统计时间内是否开启的阀值，默认20。也就是在metricsRollingStatisticalWindowInMilliseconds（默认10s）内至少请求20次，熔断器才发挥起作用
    private final HystrixProperty circuitBreakerRequestVolumeThreshold;
    // 熔断时间窗口，默认:5秒.熔断器中断请求5秒后会进入半打开状态,放下一个请求进来重试，如果该请求成功就关闭熔断器，否则继续等待一个熔断时间窗口
    private final HystrixProperty circuitBreakerSleepWindowInMilliseconds;
    //是否启用熔断器,默认true. 启动
    private final HystrixProperty circuitBreakerEnabled;
    //默认:50%。当出错率超过50%后熔断器启动
    private final HystrixProperty circuitBreakerErrorThresholdPercentage;
    //是否强制开启熔断器阻断所有请求,默认:false,不开启。置为true时，所有请求都将被拒绝，直接到fallback
    private final HystrixProperty circuitBreakerForceOpen;
    //是否允许熔断器忽略错误,默认false, 不开启
    private final HystrixProperty circuitBreakerForceClosed;
    *//* --------------信号量相关------------------*//*
    //使用信号量隔离时，命令调用最大的并发数,默认:10
    private final HystrixProperty executionIsolationSemaphoreMaxConcurrentRequests;
    //使用信号量隔离时，命令fallback(降级)调用最大的并发数,默认:10
    private final HystrixProperty fallbackIsolationSemaphoreMaxConcurrentRequests;
    *//* --------------其他------------------*//*
    //使用命令调用隔离方式,默认:采用线程隔离,ExecutionIsolationStrategy.THREAD
    private final HystrixProperty executionIsolationStrategy;
    //使用线程隔离时，调用超时时间，默认:1秒
    private final HystrixProperty executionIsolationThreadTimeoutInMilliseconds;
    //线程池的key,用于决定命令在哪个线程池执行
    private final HystrixProperty executionIsolationThreadPoolKeyOverride;
    //是否开启fallback降级策略 默认:true
    private final HystrixProperty fallbackEnabled;
    // 使用线程隔离时，是否对命令执行超时的线程调用中断（Thread.interrupt()）操作.默认:true
    private final HystrixProperty executionIsolationThreadInterruptOnTimeout;
    // 是否开启请求日志,默认:true
    private final HystrixProperty requestLogEnabled;
    //是否开启请求缓存,默认:true
    private final HystrixProperty requestCacheEnabled; // Whether request caching is enabled. */

    /****************HystrixCollapserProperties*******************/
    //请求合并是允许的最大请求数,默认: Integer.MAX_VALUE
    /*private final HystrixProperty maxRequestsInBatch;
    //批处理过程中每个命令延迟的时间,默认:10毫秒
    private final HystrixProperty timerDelayInMilliseconds;
    //批处理过程中是否开启请求缓存,默认:开启
    private final HystrixProperty requestCacheEnabled;*/

    /*-----------------------------HystrixThreadPoolProperties----------------------------------*/
    /* 配置线程池大小,默认值10个 */
    /*private final HystrixProperty corePoolSize;
    //配置线程值等待队列长度,默认值:-1 建议值:-1表示不等待直接拒绝,测试表明线程池使用直接决绝策略+ 合适大小的非回缩线程池效率最高.所以不建议修改此值。 当使用非回缩线程池时，queueSizeRejectionThreshold,keepAliveTimeMinutes 参数无效
    private final HystrixProperty maxQueueSize;*/
}
