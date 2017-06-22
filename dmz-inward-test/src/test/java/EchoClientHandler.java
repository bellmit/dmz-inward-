import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	int num =0;


	//客户端连接上服务器后调用
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端连接服务器，开始发送数据……");
		byte[] req = "QUERY TIME ORDER".getBytes();//消息
////		ByteBuf firstMessage = Unpooled.buffer(req.length);//发送类
////		firstMessage.writeBytes(req);//发送
////		ctx.writeAndFlush(firstMessage);//flush
//		
		byte[] req2 = "QUERY TIME ORDER2".getBytes();//消息
////		ByteBuf firstMessage2 = Unpooled.buffer(req2.length);//发送类
////		firstMessage2.writeBytes(req2);//发送
////		ctx.writeAndFlush(firstMessage2);//flush
//		ByteBuf data = ctx.alloc().buffer(20); //为ByteBuf分配四个字节
//		data.writeBytes(req);
//		ctx.write(data);
//		ctx.flush();
////        ctx.writeAndFlush(data);
//		
//		ByteBuf data2 = ctx.alloc().buffer(20); //为ByteBuf分配四个字节
//		data2.writeBytes(req2);
//		ctx.write(data2);
////        ctx.writeAndFlush(data2);
//        
//        
//        ctx.flush();
		
		while ( num <3) {
			ByteBuf time = ctx.alloc().buffer(20); // 为ByteBuf分配四个字节
			String st = "dddddd";
			time.writeBytes(st.getBytes());
			ctx.writeAndFlush(time); // (3)
			ByteBuf time2 = ctx.alloc().buffer(20); // 为ByteBuf分配四个字节
			String st2 = "222222222dddddd222222222";
			time2.writeBytes(st2.getBytes());
			ctx.writeAndFlush(time2); // (3)
			ctx.flush();
			num++;
		}
  
	}

	//发生异常时调用
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("client exceptionCaught..");
		// 释放资源
		ctx.close();
		cause.printStackTrace();
		super.exceptionCaught(ctx, cause);
	}

	//2
	@Override
	public boolean acceptInboundMessage(Object msg) throws Exception {
		System.out.println("acceptInboundMessage ....");
		//ByteBuf 和一般的流一样只能读取一次，应为acceptInboundMessage方法是在channelRead0方法之前调用
		//所以此处读取msg后在channelRead0方法中就读取不到了
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8");
//		System.out.println("accept inbound message:" + body);
		return super.acceptInboundMessage(msg);
	}

	//1
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("read start ....");
		
		System.out.println("client 读取server数据..");
		// 服务端返回消息后
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("服务端数据为 :" + body);
//		buf.release();
		super.channelRead(ctx, msg);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel 被注册到selector");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelUnregistered(ctx);
	}

	//失去连接时调用
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端与服务端失去连接");
		super.channelInactive(ctx);
	}

	//客户端读取数据完毕调用
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端读取数据完毕");
		ctx.flush();
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelWritabilityChanged(ctx);
	}
	
	

	
}
