package test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class VertxServer extends AbstractVerticle {

	public static void main(String[] args) {
		VertxOptions vo = new VertxOptions();
		vo.setEventLoopPoolSize(1);
		Vertx vertx = Vertx.vertx(vo);

		DeploymentOptions options = new DeploymentOptions();
		options.setInstances(1);

		vertx.deployVerticle(VertxServer.class.getName(), options, e -> {
			System.out.println(e.succeeded());
			System.out.println(e.failed());
			System.out.println(e.cause());
			System.out.println(e.result());
		});
	}

	@Override
	public void start() {
		
		Handler<HttpServerRequest>  h = new Handler<HttpServerRequest>() {
			
			@Override
			public void handle(HttpServerRequest event) {
				HttpServerResponse response = event.response();
				response.putHeader("aa", "bb").setStatusCode(200).end("Hello world");
				
			}
		}; 
				
//		Handler<HttpServerRequest> handler = e -> {
//			HttpServerResponse response = e.response();
//			response.putHeader("content-type", "application/json").end("Hello world");
//		};
		
		vertx.createHttpServer().requestHandler(h).listen(8080, resultHandler -> {
//			resultHandler.succeeded() 
		});

	}
}
