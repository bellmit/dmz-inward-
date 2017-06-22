import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * • 配置服务器功能，如线程、端口 • 实现服务器处理程序, 它包含业务逻辑，决定当有一个请求连接或接收数据时该做什么
 * 
 * @author Elwin
 *
 */
public class EchoServer {

	public static void main(String[] args) throws Exception {
		// 连接池处理数据
		EventLoopGroup group = null;
		try {
			group = new NioEventLoopGroup();
			// Server端创建引导启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// 装配bootstrap
			// 指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
			// 设置InetSocketAddress让服务器监听某个端口已等待客户端连接。
			serverBootstrap.group(group).channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress("localhost", 3899))
					.childHandler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(new EchoServerHandler());
//							ch.pipeline().addLast(new WriteTimeoutHandler(10));
                            //控制写入超时10秒构造参数10表示如果持续10秒钟都没有数据写了，那么就超时。
//							ch.pipeline().addLast(new ReadTimeoutHandler(10));
							

						}
					}).option(ChannelOption.SO_KEEPALIVE, true);

			// 最后绑定服务器等待直到绑定完成，调用sync()方法会阻塞直到服务器完成绑定,然后服务器等待通道关闭，因为使用sync()，所以关闭操作也会被阻塞。
			ChannelFuture channelFuture = serverBootstrap.bind().sync();
			System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
			channelFuture.channel().closeFuture().sync();
		} finally {

			group.shutdownGracefully().sync();
		}
	}
}
