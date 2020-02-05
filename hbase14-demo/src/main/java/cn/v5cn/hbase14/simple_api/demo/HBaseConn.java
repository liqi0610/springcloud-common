package cn.v5cn.hbase14.simple_api.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-05 14:04
 */
public class HBaseConn {
    private static final HBaseConn HBASE_CONN = new HBaseConn();

    private static Configuration configuration;
    private static Connection connection;

    /**
     * 远程连接hbase的坑
     * https://blog.csdn.net/yz930618/article/details/74173332
     * 需要在本机配置hosts,hbase所在机器的hostname和hbase所在机器的IP
     */
    private HBaseConn() {
        if(configuration == null) {
            configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", "192.168.33.12"); //服务器
            configuration.set("hbase.zookeeper.property.clientPort","2181"); //端口号
        }
    }

    private Connection getConnection() {
        if(connection == null || connection.isClosed()) {
            try {
                connection = ConnectionFactory.createConnection(configuration);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 获得Connecton对象
     * @return 返回对象
     */
    public static Connection instanceConn() {
        return HBASE_CONN.getConnection();
    }

    /**
     * 获得HBase表
     * @param tableName 表名称
     * @return 返回Table对象
     * @throws IOException
     */
    public static Table getTable(String tableName) throws IOException {
        return HBASE_CONN.getConnection().getTable(TableName.valueOf(tableName));
    }

    public static void closeConn() {
        if(connection != null && !connection.isClosed()) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
