package cn.v5cn.simple.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-25 22:39
 */
public class RPCClient {
    private final static Logger LOG = LoggerFactory.getLogger(RPCClient.class);

    private String ip;
    private int port;
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private MessageCollector collector;
    private boolean started;
    private boolean stopped;
}
