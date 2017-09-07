import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moji.launchserver.thirdapi.util.ThirdAPIUtil;

public class MyTest {
	static DecimalFormat df = new DecimalFormat("#.0000");

	static int pv = 3921;
	static int clicks = 136;

	private static final String EMPTY = "";
//	private static final Log logger = LogFactory.getLog(MyTest.class);
	
	private static final Logger logger = LoggerFactory.getLogger(MyTest.class);
	
	
	private static final String URL_PARAM_CONNECT_FLAG = "&";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		logger.info("a{}",123);
	}

	private static String getUrl2(Map<String, String> map, String valueEnc) {

		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		String strURL;
		if (map.remove("no_encode") != null) {
			strURL = joinUrlNoEncode(map, valueEnc, url);
		} else {
			strURL = joinUrlEncode(map, valueEnc, url);
		}
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}

	private static String joinUrlNoEncode(Map<String, String> map, String valueEnc, StringBuffer url) {
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		return url.toString();
	}

	private static String joinUrlEncode(Map<String, String> map, String valueEnc, StringBuffer url) {
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		return url.toString();
	}

	private static String getUrl(Map<String, String> map, String valueEnc) {

		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : "";
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}
}