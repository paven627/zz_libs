package test.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

public class HttpClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(clientGroup).channel(NioServerSocketChannel.class)
					// 设置封包 使用一次大数据的写操作，而不是多次小数据的写操作
					.option(ChannelOption.TCP_NODELAY, true).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new HttpClientCodec());
							pipeline.addLast(new HttpClientHandler());
						}
					});
			ChannelFuture f = b.bind(8080).sync(); // 绑定端口
			f.channel().closeFuture().sync();
		} finally {
			clientGroup.shutdownGracefully();
		}
	}
}

class HttpClientHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		// 请求，解码器将请求转换成HttpRequest对象
		HttpRequest request = (HttpRequest) msg;

		// 获取请求参数
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
		String name = queryStringDecoder.parameters().get("name").get(0);
		System.out.println("name=" + name);
		ctx.writeAndFlush("ok");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}