package test.java.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

/**
 *
 */
public class TcpServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						private TcpServerHandler tcpServerHandler = new TcpServerHandler();

						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new LineBasedFrameDecoder(80));
							pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
							pipeline.addLast(tcpServerHandler); // 多个连接使用同一个ChannelHandler实例
						}

					});
			ChannelFuture f = b.bind(8080).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}

/**
 * 对于用一个连接， 使用的是同一个 handler， 使用 attribute保存变量区分不同的线程
 *
 */
@Sharable // 多个连接使用同一个ChannelHandler，要加上@Sharable注解
class TcpServerHandler extends ChannelInboundHandlerAdapter {

	private AttributeKey<Integer> attributeKey = AttributeKey.valueOf("counter");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println(ctx);
		System.out.println(ctx.channel());
		Attribute<Integer> attribute = ctx.attr(attributeKey);

		int counter = 1;

		if (attribute.get() == null) {
			attribute.set(1);
		} else {
			counter = attribute.get();
			counter++;
			attribute.set(counter);
		}

		String line = (String) msg;
		System.out.println("第" + counter + "次请求:" + line);
		ctx.writeAndFlush(line);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}