//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioSocketChannel;
//
//import java.net.InetSocketAddress;
//
//public class EchoClient {
//
//	private String host;
//	private int port;
//
//	public EchoClient(String host, int port) {
//		this.host = host;
//		this.port = port;
//	}
//
//	public static void main(String[] args) throws Exception {
//		EchoClient client = new EchoClient("localhost", 3899);
//		client.start();
//
//	}
//
//	private void start() throws Exception {
//		EventLoopGroup nioEventLoopGroup = null;
//		try {
//			// NIO
//			nioEventLoopGroup = new NioEventLoopGroup();
//			// 客户端引导启动类
//			Bootstrap bootstrap = new Bootstrap();
//			bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class)
//					.remoteAddress(new InetSocketAddress(this.host, this.port))
//					.handler(new ChannelInitializer<Channel>() {
//
//						@Override
//						protected void initChannel(Channel ch) throws Exception {
//							ch.pipeline().addLast(new EchoClientHandler());
//						}
//					}).option(ChannelOption.SO_KEEPALIVE, true);
//			// 链接服务器
//			ChannelFuture channelFutire = bootstrap.connect().sync();
//			channelFutire.channel().closeFuture().sync();
//		} finally {
//			nioEventLoopGroup.shutdownGracefully().sync();
//		}
//	}
//}
