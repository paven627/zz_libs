package test.java.internet.httpclient;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class ThreadsafeClient {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		pool();

		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			test1();
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
	}

	private static void test1() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://localhost");
		HttpResponse response = client.execute(get);
		EntityUtils.toString(response.getEntity());
		response.getEntity().consumeContent();
	}

	private static void pool() throws IOException, ClientProtocolException {
		HttpParams params = new BasicHttpParams();
		// 增加最大连接到200
		ConnManagerParams.setMaxTotalConnections(params, 10);
		// 增加每个路由的默认最大连接到20
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(10);
		// 对localhost:80增加最大连接到50
		HttpHost localhost = new HttpHost("localhost", 10);
		connPerRoute.setMaxForRoute(new HttpRoute(localhost), 10);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			HttpClient httpClient = new DefaultHttpClient(cm, params);
			HttpGet get = new HttpGet("http://localhost");
			HttpResponse response = httpClient.execute(get);
			EntityUtils.toString(response.getEntity());
			response.getEntity().consumeContent();
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
	}
}
