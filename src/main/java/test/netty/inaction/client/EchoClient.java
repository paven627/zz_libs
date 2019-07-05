package test.netty.inaction.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClient {

	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // 1
			b.group(group) // 2
					.channel(NioSocketChannel.class) // 3
					.remoteAddress(new InetSocketAddress(host, port)) // 4
					.handler(new ChannelInitializer<SocketChannel>() { // 5
								@Override
								public void initChannel(SocketChannel ch)
										throws Exception {
									ch.pipeline().addLast(
											new EchoClientHandler());
								}
							});

			ChannelFuture f = b.connect().sync(); // 6

			f.channel().closeFuture().sync(); // 7
		} finally {
			group.shutdownGracefully().sync(); // 8
		}
	}

	public static void main(String[] args) throws Exception {
		new EchoClient("127.0.0.1", 8080).start();
	}
}

@Sharable
// 1
class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", // 2
				CharsetUtil.UTF_8));
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
		System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8)); // 3
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // 4
		cause.printStackTrace();
		ctx.close();
	}
}