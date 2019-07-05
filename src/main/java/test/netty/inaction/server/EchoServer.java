package test.netty.inaction.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * netty in action server 示例
 * 
 * @author mz
 *
 */
public class EchoServer {

	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		new EchoServer(8080).start(); // 2
	}

	public void start() throws Exception {
		NioEventLoopGroup group = new NioEventLoopGroup(1); // 3

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group, group) // 4
					.channel(NioServerSocketChannel.class) // 5
					//					.localAddress(new InetSocketAddress(port)) // 6
					.childHandler(new ChannelInitializer<SocketChannel>() { // 7
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new Handler2());
							ch.pipeline().addLast(new EchoServerHandler());
						}
					});
			ChannelFuture f = b.bind(8080).sync(); // 8
			f.addListener(new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					System.out.println(future.isSuccess());
					System.out.println(future.channel());
				}
			});
			System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
			f.channel().closeFuture().sync(); // 9
			f.addListener(ChannelFutureListener.CLOSE);
		} finally {
			group.shutdownGracefully().sync(); // 10
			System.out.println("shutdown");
		}
	}

}

@Sharable
// 1
class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8)); // 2
		ctx.write(in); // 3
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("ReadComplete");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 4
				.addListener(ChannelFutureListener.CLOSE);
		ctx.close();

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace(); // 5
		ctx.close(); // 6
		ctx.channel().close();
		ctx.channel().disconnect();
	}
}

@Sharable
class Handler2 extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("日志方法 : " + in.toString(CharsetUtil.UTF_8)); // 2
		throw new Exception("aaaa");
//		ctx.fireChannelRead(msg);
	}
}