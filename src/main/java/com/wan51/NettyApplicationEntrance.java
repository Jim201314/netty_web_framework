package com.wan51;

import com.wan51.netty.handler.WebSocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 老包子 on 2017/3/22.
 * netty web 项目启动入口
 */
public class NettyApplicationEntrance {

    private static final Logger logger = Logger.getLogger(NettyApplicationEntrance.class);

    public static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "8080"));

    @Autowired
    WebSocketServerInitializer webSocketServerInitializer;

    public void init() throws InterruptedException {
        // Configure SSL.
        final SslContext sslCtx =  null;
        /** 注释SSL
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        **/
        EventLoopGroup bossGroup = new NioEventLoopGroup(4);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(webSocketServerInitializer);

            Channel ch = b.bind(PORT).sync().channel();

            System.out.println("Open your web browser and navigate to " +
                    (SSL? "https" : "http") + "://127.0.0.1:" + PORT + '/');

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String[] configurations = { "config/applicationContext.xml","config/applicationContext-netty.xml"};

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(configurations);

        logger.info("-------------------------------------------");

        logger.info("start nettyContainer....");

        logger.info("--------------------------------------------");
    }


}
