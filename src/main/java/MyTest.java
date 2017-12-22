import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.RequestUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

class Person {
	String name;
	int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
//	@Override
//	public int hashCode() {
//	    return age;
//	}
}
public class MyTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		String url = "http://c.gdt.qq.com/gdt_mclick.fcg?viewid=rIyVohkcaH8tKvB9qYwb3T1LLA5bLvwFGIEbijnwS2eG0HFnXK5HlD1fkw!AOsd!Uo9grvgrau1lJnH6SI4PbNsuJxx3VXI_QwPhwcHhVhkai0!mVD3FLmQOjJ5oH_JBjSr341Kvjbm4O6NZqm1fAty2siaAPNpnLfuzGyGQAwH8ku1E_K1Xk9FG0lh5F7bFFY_FOIy!NlPBD74!YPAAtw&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22OPPO%20R9s%20Plus%22%7D&acttype=0&s=%7B%22req_width%22%3A%22640%22%2C%22req_height%22%3A%22960%22%2C%22width%22%3A%22__WIDTH__%22%2C%22height%22%3A%22__HEIGHT__%22%2C%22down_x%22%3A%22__DOWN_X__%22%2C%22down_y%22%3A%22__DOWN_Y__%22%2C%22up_x%22%3A%22__UP_X__%22%2C%22up_y%22%3A%22__UP_Y__%22%7D\n";
		
		Map<String, String[]> values = new HashMap<String, String[]>();
		RequestUtil.parseParameters(values, url, "UTF-8");
		System.out.println(values.keySet());
		
//		System.out.println(Arrays.toString(values.get("http://c.gdt.qq.com/gdt_mclick.fcg?viewid")));
		System.out.println(values.get("http://c.gdt.qq.com/gdt_mclick.fcg?viewid")[0]);
		
	}
}