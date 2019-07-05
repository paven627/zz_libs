package test.netty.decoder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 消息转数字解码器
 * 
 * 
 *
 * 
 * 1 实现继承了 ByteToMessageDecode 用于将字节解码为消息
 * 
 * 2 检查可读的字节是否至少有4个 ( int 是4个字节长度)
 * 
 * 3 从入站 ByteBuf 读取 int ， 添加到解码消息的 List 中
 * 
 */
public class ToIntDecoder extends ByteToMessageDecoder { // 1

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//		System.out.println("ctx:" + ctx);
//		System.out.println("channel:" + ctx.channel());
//		System.out.println("接到数据 : " + in.toString(CharsetUtil.UTF_8)); // 2

//		System.out.println(in.readableBytes());

//		out.add("bbb");
		if (in.readableBytes() >= 4) { // 2
			int n = in.readInt();
			System.out.println("read :" + n);
			out.add(n); // 3
		}
	}

}
