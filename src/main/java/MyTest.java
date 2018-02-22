
import org.apache.commons.httpclient.HeaderElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MyTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		HeaderElement[] parse = HeaderElement.parseElements("application/json; charset=utf-8");
		System.out.println(parse[1]);

		System.out.println(new String("\\u300a\\u6076\\u7075\\u9000\\u6563\\u300b\\u662f\\u4e00\\u6b3e\\u65e5\\u5f0f\\u548c\\u98ce\\u4f11\\u95f2\\u6302\\u673a\\u6e38\\u620f\\uff0c\\u4e0d\\u8981\\u6c42\\u73a9\\u5bb6\\u957f\\u671f\\u5728\\u7ebf\\u3002\\u4ece\\u4f60\\u8fdb\\u5165\\u6076\\u7075\\u4e16\\u754c\\u7684\\u7b2c"));
	}
	

}