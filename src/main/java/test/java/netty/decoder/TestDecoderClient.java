package test.java.netty.decoder;

import java.io.IOException;
import java.net.UnknownHostException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import test.java.netty.encoder.IntegerToByteEncoder;

public class TestDecoderClient {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.AUTO_READ, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new IntegerToByteEncoder());
					ch.pipeline().addLast(new ToIntDecoder());
					ch.pipeline().addLast(new HelloClientIntHandler());
				}
			});
			ChannelFuture f = b.connect("localhost", 8080).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}

	}
}

class HelloClientIntHandler extends ChannelInboundHandlerAdapter {

//	// 连接成功后，向server发送消息
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("==============channel--active==============");
		System.out.println("向服务器端写入1991数字");
		ctx.write(1991);
		ctx.flush();
	}

	// 接收server端的消息，并打印出来
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("==============channel--read==============");
		System.out.println("the msg type is " + msg.getClass().getName());

		Integer result = (Integer) msg;
		System.out.println("接收到服务器数据整形是" + result);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}