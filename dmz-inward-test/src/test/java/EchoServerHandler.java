//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//
//public class EchoServerHandler extends ChannelInboundHandlerAdapter {
//
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("server 读取数据。。。。");
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8");
//		System.out.println("接收客户端数据:" + body);
//		// 向客户端写数据
////
////		while(true){
////		String currentTime = new Date(System.currentTimeMillis()).toString();
////		System.out.println("server 向客户端发送数据。。。。是： " +System.currentTimeMillis());
////		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
////		ctx.write(resp);
////		ctx.flush();
////		}
////		while (true) {
////            ByteBuf time = ctx.alloc().buffer(20); //为ByteBuf分配四个字节
////            String st = "dddddd";
////            time.writeBytes(st.getBytes());
////            ctx.writeAndFlush(time); // (3)
////        }
//	}
//
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("server 读取数据完毕..");
//        ctx.flush();//刷新后才将数据发出到SocketChannel
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
//	}
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//	}
//
//
//}
