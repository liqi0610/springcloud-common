# 包含Netty和RPC的练习例子
## 动态代理
### dynamic-proxy-jdk
JDK自带的动态代理
### dynamic-proxy-javassist
javassist动态代理

## 序列化
### serialize-hessian
hessian序列化
### serialize-protobuf
protobuf序列化需要编写proto文件，使用protobuf带的编译工具生成不同语言的代码。

## RPC
### laoqian-rpc老钱博客RPC

博客地址：![老钱博客](https://juejin.im/post/5ad2a99ff265da238d51264d)

这个RPC是使用Netty和fastjson实现，这个例子是计算斐波那契数和指数。斐波那契数输入输出比较简单，一个Integer，一个Long。 
指数输入有两个值，输出除了计算结果外还包含计算耗时，以纳秒计算。之所以包含耗时，只是为了呈现一个完整的自定义的输入和输出类。
* 自定义协议类型
* 没有使用代理
* 通过type来确定使用那个处理器处理请求
* 包含消息唯一ID
* 使用JSON序列化

