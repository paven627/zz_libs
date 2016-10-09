package test.vertx.execblocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;

public class ExecBlockingExample extends AbstractVerticle {

	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		// vertx core-example 项目中的方法
		// Runner.runExample(ExecBlockingExample.class);

		VertxOptions options = new VertxOptions();
		options.setInternalBlockingPoolSize(10); // 阻塞任务线程池
		options.setWorkerPoolSize(10);
		options.setEventLoopPoolSize(10); //
		System.out.println(options.getEventLoopPoolSize());
		Vertx vert = Vertx.vertx(options);
		DeploymentOptions op = new DeploymentOptions();
		op.setInstances(3);
		vert.deployVerticle(ExecBlockingExample.class.getName(), op, handler -> {
			String result = handler.result();
			System.out.println(result + " 0");
		});

		vert.deployVerticle(ExecBlockingExample.class.getName(), op, handler -> {
			String result = handler.result();
			System.out.println(result + " 1");
		});
		vert.deployVerticle(ExecBlockingExample.class.getName(), op, handler -> {
			String result = handler.result();
			System.out.println(result + " 2");
		});
		vert.deployVerticle(ExecBlockingExample.class.getName(), op, handler -> {
			String result = handler.result();
			System.out.println(result + " 3");
		});
	}

	@Override
	public void start() throws Exception {

		vertx.createHttpServer().requestHandler(request -> {
			request.bodyHandler(new Handler<Buffer>() {

				@Override
				public void handle(Buffer event) {
					System.out.println("worker开始执行" + Thread.currentThread().getName() + ", " + event);
				}
			});
			// Let's say we have to call a blocking API (e.g. JDBC) to execute a
			// query for each
			// request. We can't do this directly or it will block the event
			// loop
			// But you can do this using executeBlocking:
			vertx.<String>executeBlocking(future -> {

				// Do the blocking operation in here

				// Imagine this was a call to a blocking API to get the result
				try {
					Thread.sleep(500);
				} catch (Exception ignore) {
				}
				String result = "armadillos!";
				future.complete(result);
			}, res -> {
				if (res.succeeded()) {
					System.out.println("work线程完成: " + Thread.currentThread().getName());
					request.response().putHeader("content-type", "text/plain")
							.end(Thread.currentThread().getName() + " , " + res.result());
				} else {
					res.cause().printStackTrace();
				}
			});

		}).listen(8080);

	}
}
