package cn.v5cn.springcloud.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  使用Fallback() 提供降级策略
 * </p>
 *
 * @author ZYW
 * @version v1.0.0
 * @date 2019-08-22 12:48
 */
public class HelloWorldCommand2 extends HystrixCommand<String> {

    private String name;

    protected HelloWorldCommand2(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时  17        
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }

    public static void main(String[] args) {
        HelloWorldCommand2 command2 = new HelloWorldCommand2("test-Fallback");
        String result = command2.execute();
        System.out.println("result=" + result);
    }
    /*
     * 运行结果：
     * result=exeucute Falled
     */
}
