package cn.v5cn.sofarpc.rpc;

/**
 * RPC服务接口实现类
 * @author ZYW
 * @version 1.0
 * @date 2020-03-01 14:19
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}
