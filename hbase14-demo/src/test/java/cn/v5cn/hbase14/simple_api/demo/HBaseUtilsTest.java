package cn.v5cn.hbase14.simple_api.demo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-05 20:46
 */
public class HBaseUtilsTest {

    /**
     * 创建表，本机需要配置hbase的hastname到ip地址
     */
    @Test
    public void createTable() {
        Assert.assertTrue(HBaseUtils.createTable("FileTable1", "fileInfo", "saveInfo"));
    }

    @Test
    public void addFileDetails() {
        HBaseUtils.putRow("FileTable1", "rowkey1", "fileInfo", "name", "file1.txt");
        HBaseUtils.putRow("FileTable1", "rowkey1", "fileInfo", "type", "txt");
        HBaseUtils.putRow("FileTable1", "rowkey1", "fileInfo", "size", "1024");
        HBaseUtils.putRow("FileTable1", "rowkey1", "saveInfo", "creator", "jixin");
        HBaseUtils.putRow("FileTable1", "rowkey2", "fileInfo", "name", "file2.jpg");
        HBaseUtils.putRow("FileTable1", "rowkey2", "fileInfo", "type", "jpg");
        HBaseUtils.putRow("FileTable1", "rowkey2", "fileInfo", "size", "1024");
        HBaseUtils.putRow("FileTable1", "rowkey2", "saveInfo", "creator", "jixin");
    }

}
