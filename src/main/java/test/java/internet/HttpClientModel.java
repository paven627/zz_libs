package test.java.internet;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientModel {
	public static void main(String[] args) throws IllegalStateException,
			IOException {
//		 testGet();
		System.out.println(299 % 30 + 1);
		System.out.println(299 % 10 + 1);
		System.out.println(299 % 15 + 1); 
		
	}

	private static void testGet() throws IOException, ClientProtocolException {
		HttpClient httpClient = new DefaultHttpClient();

		for (int i = 1; i <= 300; i++) {
			int adId = i % 30 + 1; //30 
			int bid = i % 10 + 1; // 10
			int regionid = i % 15 + 1; // 15

			HttpPost httpGet = new HttpPost(
					"http://127.0.0.1:9090/i?app=1&p=4&did=p3b3ad1&bid=" + bid
							+ "&industry=350&adId=" + adId + "&regionId="
							+ regionid);

			httpGet.addHeader("Accept", "text/html, application/xhtml+xml, */*");

			httpGet.addHeader("Accept-Language", "zh-CN,en-US;q=0.5");

			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

			httpGet.addHeader("Accept-Encoding", "gzip, deflate");

			httpGet.addHeader("Host", "localhost:9999");

			httpGet.addHeader("Connection", "Keep-Alive");
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			System.err.println(EntityUtils.toString(httpResponse.getEntity()));
			inputStream.close();
		}
	}
}