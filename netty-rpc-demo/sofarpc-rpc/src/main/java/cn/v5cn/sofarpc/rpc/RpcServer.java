package cn.v5cn.sofarpc.rpc;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

/**
 * 服务发布类
 * @author ZYW
 * @version 1.0
 * @date 2020-03-01 14:20
 */
public class RpcServer {
    public static void main(String[] args) {
        ServerConfig config = new ServerConfig()
                .setProtocol("bolt")  // 设置通讯协议，默认就是bolt
                .setPort(12200)       // 设置一个端口，默认12200
                .setDaemon(false);    // 非守护进程

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())  //指定接口
                .setRef(new HelloServiceImpl())                //指定实现
                .setServer(config);                            //指定服务端

        providerConfig.export();   //发布服务
    }
}
