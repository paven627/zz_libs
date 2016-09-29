package test.java.internet.asyncClient;

import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncClientHttpExchange {
	public static void main(final String[] args) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		// 默认的配置
		try {
			httpclient.start();
			HttpGet request = new HttpGet("http://www.apache.org/");
			Future<HttpResponse> future = httpclient.execute(request, null);
			HttpResponse response = future.get();
			// 获取结果
			System.out.println("Response: " + response.getStatusLine());
			System.out.println("Shutting down");
		} finally {
			httpclient.close();
		}
		System.out.println("Done");
	}
}