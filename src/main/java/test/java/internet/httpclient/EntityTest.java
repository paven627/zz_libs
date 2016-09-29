package test.java.internet.httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class EntityTest {
	public static void main(String[] args) throws Exception {
		formEntityTest();
	}

	public static void formEntityTest() throws Exception {
		URI uri = new URI("http", null, "www.baidu.com", 80, "/test",
				"abc=123", null);
		HttpPost post = new HttpPost(uri);
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 1
		params.add(new BasicNameValuePair("a", "1"));
		// 2
		params.add(new BasicNameValuePair("b", "2"));
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
				params, HTTP.UTF_8);
		urlEncodedFormEntity.setContentEncoding("utf-8");
		StringEntity str = new StringEntity("a=1&b=2", "UTF-8");
		System.out.println(str.toString());

		URI relativize = uri.relativize(uri);
		System.out.println(relativize);
	}
}
