package cn.v5cn.flink19;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Flink Checkpointing和重启策略
 * @author ZYW
 * @version 1.0
 * @date 2020-03-12 17:00
 */
public class RestartStartDemo {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //开启Checkpointing，5秒发送一次保存State命令
        env.enableCheckpointing(5000);

        //更改重启策略，设置对多重启3次，每次延迟2秒。
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(3,2000));
    }
}
