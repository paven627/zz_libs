import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class MyTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		String s = "一二三sr";
		if (s.length() > 4) {
			s = s.substring(0, 4);
		}
		System.out.println(s);
		
		
		System.out.println(new String("\u9996\u9875feed\u89c6\u9891\u6d4b\u8bd5"));
	}
}