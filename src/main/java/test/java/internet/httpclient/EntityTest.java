package test.java.internet.httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class EntityTest {
	public static void main(String[] args) throws Exception {
		formEntityTest();
	}

	public static void formEntityTest() throws Exception {
		URI uri = new URI("http", null, "localhost", 8080, "/mzdsp/bid/11108",
				"{\"id\":\"dev-t19-1477648424-0-642\",\"device\":{\"ip\":\"0:0:0:0:0:0:0:1\",\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D257\",\"dpidmd5\":\"1abe5e1a8a08f577bda8209a69cf8265\",\"make\":\"Apple\",\"model\":\"iPhone6,2\",\"os\":\"iOS\",\"osv\":\"7.1.2\",\"language\":\"zh_CN\",\"connectiontype\":2,\"devicetype\":0,\"ext\":{\"idfa\":\"E9B295F1-9CEB-49D1-96E1-A84E6972AA2D\",\"ssid\":\"xtrader\",\"w\":320,\"h\":568,\"ts\":1463995184,\"realip\":\"0:0:0:0:0:0:0:1\",\"isipdx\":false}},\"user\":{\"id\":\"\"},\"app\":{\"name\":\"%E7%81%B5%E9%9B%86%E5%B9%BF%E5%91%8A\",\"ext\":{\"sdk\":\"1.1.0\",\"deeplink\":1},\"content\":{\"ext\":{\"copyright\":0,\"quality\":2}},\"cat\":[],\"bundle\":\"com.xtrader.XTraderMobile\"},\"imp\":[{\"id\":\"b5002176fbbc4569b64ee28b427e2456\",\"tagid\":\"714\",\"bidfloor\":40,\"banner\":{\"w\":640,\"h\":960,\"mimes\":[\"image/jpeg\",\"image/png\",\"application/x-shockwave-flash\",\"video/x-flv\",\"application/x-shockwave-flash\",\"text/html\",\"image/gif\"],\"pos\":0},\"ext\":{\"showtype\":1,\"has_winnotice\":1,\"has_clickthrough\":0,\"action_type\":2}}],\"ext\":{\"media_source\":2}}", null);
		HttpPost post = new HttpPost(uri);
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 1
//		params.add(new BasicNameValuePair("a", "1"));
//		// 2
//		params.add(new BasicNameValuePair("b", "2"));
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
				params, HTTP.UTF_8);
		urlEncodedFormEntity.setContentEncoding("utf-8");
		StringEntity str = new StringEntity("a=1&b=2", "UTF-8");
		System.out.println(str.toString());
		
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse execute = client.execute(post);
		System.out.println(execute);
//		URI relativize = uri.relativize(uri);
//		System.out.println(relativize);
	}
}
