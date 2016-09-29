package test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;

public class EventbusTest extends AbstractVerticle {
	static Vertx vertx;
	static EventBus eventBus;

	public static void main(String[] args) {
		createServer();
		eventBus = vertx.eventBus();
		eventBus.registerCodec(new MyMessageCodec());
		consumer();
	}

	// 注册一个消费者到指定的频道
	private static void consumer() {
		MessageConsumer<MyMessage> consumer = eventBus.consumer("channel1", message -> {
			MyMessage body = message.body();
			System.out.println("I have received a message:" + body);
			message.reply(555);

		});
		// 注册成功
		consumer.completionHandler(res -> {
			if (res.succeeded()) {
				System.out.println("The handler registration has reached all nodes");
			} else {
				System.out.println("Registration failed!");
			}
		});

		// 可以注册多个consumer 
		
		// MessageConsumer<String> consumer2 = eventBus.consumer("channel1", new
		// Handler<Message<String>>() {
		//
		// @Override
		// public void handle(Message<String> event) {
		// System.out.println(event.body());
		// }
		// });
	}

	private static void createServer() {
		VertxOptions op = new VertxOptions();
		vertx = Vertx.vertx(op);
		DeploymentOptions options = new DeploymentOptions();
		options.setInstances(1);
		vertx.deployVerticle(EventbusTest.class.getName(), options);
		HttpServerOptions hso = new HttpServerOptions();
		hso.setPort(8080);
		vertx.createHttpServer(hso).requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest event) {
				event.bodyHandler(new Handler<Buffer>() {

					@Override
					public void handle(Buffer event) {
						// 有多个频道订阅者时, 把接收到的数据发送的指定的频道中, 推送到eventbus中
						// eventBus.publish("channel1", event.toString());

						DeliveryOptions options = new DeliveryOptions();
						
						options.setSendTimeout(100);
						options.setCodecName("myMessage");
						
						// 只有一个接收者,send方法可以获取回复
						MyMessage m = new MyMessage("head1", event.toString());
						
						eventBus.send("channel1", m, options, new Handler<AsyncResult<Message<MyMessage>>>() {

							@Override
							public void handle(AsyncResult<Message<MyMessage>> event) {
								System.out.println("回复:" + event.succeeded() + "," + event.result().address() + ":"
										+ event.result().body());
								event.result().reply(24234);
							}

						});
					}
				});
				event.response().putHeader("Content-Type", "html/text").end("ok");
			}
		}).listen(e -> {
			System.out.println(e.succeeded());
		});
	}

}

// 自定义消息需要注册解码类
class MyMessageCodec implements io.vertx.core.eventbus.MessageCodec<MyMessage, MyMessage>{

	@Override
	public void encodeToWire(Buffer buffer, MyMessage s) {
		
	}

	@Override
	public MyMessage decodeFromWire(int pos, Buffer buffer) {
		// TODO Auto-generated method stub
		return null;
	}

	// 本地消息,直接返回
	@Override
	public MyMessage transform(MyMessage s) {
		return s;
	}
	
	 /** 
     * 编解码器的名称： 
     *              必须唯一，用于发送消息时识别编解码器，以及取消编解码器 
     */  
	@Override
	public String name() {
		return "myMessage";
	}

	// 用户自定义的使用 -1, 其他是系统用
	@Override
	public byte systemCodecID() {
		return -1;
	}
	
}

// 自定义消息类型
class MyMessage {
	String head;
	String content;

	public MyMessage(String head, String content) {
		super();
		this.head = head;
		this.content = content;
	}

	@Override
	public String toString() {
		return "MyMessage [head=" + head + ", content=" + content + "]";
	}

}
