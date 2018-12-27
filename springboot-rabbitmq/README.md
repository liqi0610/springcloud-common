## RabbitMQ 例子

### 1、交换机（Exchange）

交换机为`FanoutExchange`模式

### 2、延时队列
延时队列使用`rabbitmq-delayed-message-exchange`插件来完成延时队列的创建。
插件[Github](https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/)

### 参考文章
1. [www.pandan.xyz/categories/rabbitmq/](www.pandan.xyz/categories/rabbitmq/)
2. [https://segmentfault.com/a/1190000016072908](https://segmentfault.com/a/1190000016072908)

### Docker安装RabbitMQ
[https://hub.docker.com/_/rabbitmq/](https://hub.docker.com/_/rabbitmq/)
```
docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=zhangsan -e RABBITMQ_DEFAULT_PASS=000000 rabbitmq:3.7.8-management
```