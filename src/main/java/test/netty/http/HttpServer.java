package test.netty.http;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.concurrent.EventExecutor;

/**
 * 处理HTTP请求
 * 
 * @author mz
 *
 */
public class HttpServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // 接收客户端连接用默认线程数为cpu的2倍,
															// 服务端才需要bossgroup,
															// 客户端只需要一个group
		EventLoopGroup workerGroup = new NioEventLoopGroup(); // 处理网络读写事件
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new HttpServerCodec());
							// 使用 serverCodec 和 自己添加 requestDecoder与
							// responseEncoder效果一样

							// ch.pipeline().addLast(new HttpResponseEncoder());
							// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
							// ch.pipeline().addLast(new HttpRequestDecoder());
							ch.pipeline().addLast(new HttpServerHandler());
						}
					});
			ChannelFuture f = b.bind(8080).sync(); // 绑定端口 ,等待绑定成功
			f.channel().closeFuture().sync(); // 等待服务器退出
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}

class HttpServerHandler extends ChannelInboundHandlerAdapter {

	private HttpRequest request;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
		System.out.println("收到：" + new Date());
//		ByteBuf bufs = (ByteBuf) msg;
//		System.out.println(bufs);
		if (msg instanceof HttpRequest) {

			// 请求，解码器将请求转换成HttpRequest对象
			HttpRequest request = (HttpRequest) msg;

			// 获取请求参数
			QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
			String name = queryStringDecoder.parameters().get("name").get(0);
			System.out.println("name=" + name);
		}

		if (msg instanceof HttpRequest) {
			request = (HttpRequest) msg;
//			System.out.println("Uri:" + uri);
		} else {
			System.out.println(msg);
		}
//		HttpMethod method = request.getMethod();
//		System.out.println(method);
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			System.out.println("buf:" + buf.toString(io.netty.util.CharsetUtil.UTF_8));
			buf.release();

			String res = "result some thing "; // 不复杂的可短时间处理的业务，如果为复杂的业务不可直接在handler中调用

			EventExecutor executor = ctx.executor();
			System.out.println("executor:" + executor);
			boolean inEventLoop = executor.inEventLoop();
			System.out.println(inEventLoop);
			FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
					Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
			response.headers().set(CONTENT_TYPE, "text/plain");
			response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
			if (HttpHeaders.isKeepAlive(request)) {
				response.headers().set(CONNECTION, Values.KEEP_ALIVE);
//				response.headers().set(CONNECTION, Values.CLOSE);
			}
			ctx.writeAndFlush(response);
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		ctx.close();
		
		System.out.println("完成" + new Date());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}