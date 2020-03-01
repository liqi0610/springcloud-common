package cn.v5cn.sofarpc.rpc;

import com.alipay.sofa.rpc.config.ConsumerConfig;

import java.util.concurrent.TimeUnit;

/**
 * RPC客户的
 * @author ZYW
 * @version 1.0
 * @date 2020-03-01 14:35
 */
public class RpcClient {
    public static void main(String[] args) {
        ConsumerConfig<HelloService> config = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())  //指定接口
                .setProtocol("bolt")                           //指定协议
                .setDirectUrl("bolt://127.0.0.1:12200")        //指定直连服务地址和端口
                .setConnectTimeout(10 * 1000);                 //指定连接超时时间，默认：1000

        //生成代理类
        HelloService service = config.refer();
        while (true) {
            System.out.println(service.sayHello("world"));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
