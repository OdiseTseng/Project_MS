package com.ncsist.sstp.server;

import com.ncsist.sstp.server.handler.ClientHandler;
import com.ncsist.sstp.vo.Properties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
//import org.simulation.service.session.SessionUser;
//import org.simulation.util.func.CommonFunction;
//import org.simulation.util.log.LogDataHelper;
//import org.simulation.service.handler.NettyClientHandler;
//import org.simulation.vo.properties.PropertiesVO;


public class ClientSocket {
    private String apiUrl = "http://";
//	private String apiUrl = "http://localhost:8080/";
//	String apiUrl = "http://192.168.50.219:8080/";
//	String apiUrl = "http://192.168.50.89:8080/";

    public ClientSocket() {
        apiUrl += Properties.getServerIp() + ":" + Properties.getServerPort() + "/";

    }

    public static void startNettyClient() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //Create bootstrap target，Setting parameters
            Bootstrap bootstrap = new Bootstrap();
            //設定thread
            bootstrap.group(eventExecutors)
                    //設定client的channel type
                    .channel(NioSocketChannel.class)
                    //使用匿名内部类初始化通道 使用匿名類別initialize channel
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //add client channel processor
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
//            LogDataHelper.writeLog(CommonFunction.getCurrentFunctionName(), "Client ready");
            System.out.println("Client ready");
            //Connect server
            ChannelFuture channelFuture = bootstrap.connect(Properties.getNettyIp(), Properties.getNettyPort()).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
//            LogDataHelper.writeLog(ClientSocket.class.getPackageName(), CommonFunction.getCurrentFunctionName(), e.getClass().getName(), e.getMessage());
        } finally {
            //close thread group
            eventExecutors.shutdownGracefully();
        }
    }
}
