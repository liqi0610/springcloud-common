package cn.v5cn.flink19;

import cn.v5cn.flink19.util.FlinkUtils;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-03-26 16:38
 */
public class KafkaToRedis {

    public static void main(String[] args) throws Exception {
        ParameterTool params = ParameterTool.fromPropertiesFile(args[0]);

        DataStreamSource<String> kafkaConsumer = FlinkUtils.createKafkaConsumer(params, new SimpleStringSchema());

        kafkaConsumer.print();

        FlinkUtils.getEnv().execute("KafkaToRedis");
    }
}
