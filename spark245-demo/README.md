# Spark2.4.5版本

## 1. Spark安装
![Spark安装](./img/WX20200420-222719.png)

## 2. 提交任务

```shell script
./spark-submit 
# 指定master节点
--master spark://127.0.0.1:7077 \
# 指定计算内存
--executor-memory 512m \
# 指定计算核数
--total-executor-cores 4 \
# 指定程序的Main方法类和需要提供的参数
--class cn.v5cn.spark.demo.Main /root/WordCount.jar hdfs://127.0.0.1:9000/wc hdfs://127.0.0.1:9000/out2
```
## 3. Spark架构体系
![park架构体系](./img/WX20200420-221817.png)

## 4. Spark和Yarn中角色对比
![Spark和Yarm中角色对比](./img/WX20200420-222407.png)