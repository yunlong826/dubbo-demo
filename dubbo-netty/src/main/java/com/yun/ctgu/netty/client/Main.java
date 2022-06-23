package com.yun.ctgu.netty.client;


import com.yun.ctgu.netty.entity.RpcRequest;
import io.netty.channel.Channel;
import java.util.UUID;

/**
 * <uv> [2018/10/13 19:51]
 */
public class Main {

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient("81.68.177.58", 8080);
        //启动client服务
        client.start();

        Channel channel = client.getChannel();
        //消息体
        RpcRequest request = new RpcRequest();
        request.setId(UUID.randomUUID().toString());
        request.setData("client.message");
        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }
}
