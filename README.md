# netty_web_framework
基于netty做为web容器的web框架，适用于c/s结构的服务端开发框架。
框架背景：

前期为公司项目做全链路压测，发现公司跑到tomcat上的服务，即使是最简单的方法QPS也就到3000左右，后期查询发现可能和tomcat的业务逻辑有关。

因为以前在项目开发中用netty做过即时聊天的项目，对netty也比较熟，就有了想用netty做一个web框架的想法。

框架应用：

       本框架没有页面渲染功能，适用于c/s结构的服务端开发。比较适用于当前比较流行的APP服务端开发。
性能：

       同样的功能用springboot和netty相比，QPS大概是前者3.5倍左右。

Springboot压测结果截图（QPS平均在1万左右）：
 ![springboot性能压测报告](https://github.com/Jim201314/netty_web_framework/tree/master/readme/spring.png)
 

本框架压测结果截图：（QPS平均在3.8万）
 ![image](https://github.com/Jim201314/netty_web_framework/tree/master/readme/netty.png)

主要技术：
 
Netty 4.1.6, 
Spring 4.3.2.RELEASE
mybatis
Mysql

 

项目结构：
 ![image](https://github.com/Jim201314/netty_web_framework/master/readme/idea.png)

入口函数：

复制代码
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
复制代码
 

 

访问截图(这是一个静态方法)：

 

 

另外还有一个关于数据库的访问demo, 需要配置好数据库，并导入表结构和数据.

访问URL:

http://localhost:8080/api?service=city&action=list

 SQL如下：

复制代码
DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,

  `cityname` varchar(20) CHARACTER SET utf8 DEFAULT NULL,

  `pinying` varchar(20) DEFAULT NULL,

  `status` tinyint(1) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `city` VALUES ('1', '杭州', 'hangzhou', '1');

INSERT INTO `city` VALUES ('2', '上海', 'shanghai', '1');

INSERT INTO `city` VALUES ('3', '北京', 'beijing', '1');
复制代码
 

源码已上传到githup, 有兴趣的同学可以弄下来看看，但不保证在生产环境运行稳定。

https://github.com/Jim201314/netty_web_framework
