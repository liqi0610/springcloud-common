package cn.v5cn.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * 每一行根据空格截取单词并返回。
 * map阶段输入的k1,v1 分别是k1是行号，v1是整行文本
 * map阶段返回的k2,v2 分别是k2是单词，v2是单词的数量
 * @author ZYW
 * @version 1.0
 * @date 2020-02-13 22:30
 */
public class WordCountMap extends Mapper<LongWritable,Text,Text,LongWritable> {

    @Override
    protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
        String lineText = v1.toString();
        //按照空格分隔行
        String[] lineArray = lineText.split(" ");
        for(String value : lineArray) {
            //写数据到reduce阶段
            context.write(new Text(value.trim()),new LongWritable(1));
        }
    }
}
