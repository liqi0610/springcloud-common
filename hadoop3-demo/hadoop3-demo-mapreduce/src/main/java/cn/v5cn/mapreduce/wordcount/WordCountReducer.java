package cn.v5cn.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 计算每个单词出现的次数相加
 * @author ZYW
 * @version 1.0
 * @date 2020-02-13 22:35
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text,LongWritable> {

    @Override
    protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable v2 : v2s) {
            sum += v2.get();
        }
        context.write(new Text(k2),new LongWritable(sum));
    }
}
