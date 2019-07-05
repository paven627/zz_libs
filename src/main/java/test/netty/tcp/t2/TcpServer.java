package test.netty.tcp.t2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
import io.netty.util.CharsetUtil;

public class TcpServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							System.out.println("initChannel:" + this);
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new LineBasedFrameDecoder(80));
							pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
							TcpServerHandler tcpServerHandler = new TcpServerHandler();
							System.out.println(tcpServerHandler);
							pipeline.addLast(tcpServerHandler); // 针对每个TCP连接创建一个新的ChannelHandler实例
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
 * 每个连接使用不同的Handler，可以保存一个变量进行计数
 */
class TcpServerHandler extends ChannelInboundHandlerAdapter {

	// 连接相关的信息直接保存在TcpServerHandler的成员变量中
	private int counter = 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println(this);
		counter++;
		
		String line = (String) msg;
		System.out.println("第" + counter + "次请求:" + line);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}