package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class TestWget {
	static Logger logger = Logger.getLogger(TestWget.class);
	public static void main(String[] args) throws UnsupportedEncodingException {
		
//		String s = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0";
//		System.out.println(URLEncoder.encode(s,"utf-8"));
//		System.out.println(System.currentTimeMillis());
		try {
			int i = 2/ 0;
		} catch (Exception e) {
			e = new NullPointerException();
			logger.error("Unhandled exception", e);
		}
		
	}
}
