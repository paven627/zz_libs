import java.text.SimpleDateFormat;
import java.util.Random;

import com.alibaba.fastjson.util.Base64;

public class Test {
	private static SimpleDateFormat sdfSS = new SimpleDateFormat("hhmmss");

	static Random r = new Random();
	
	public static void main(String[] args) {
		String s= "YmI9MjU4JmdnPTAmYmU9MTE1MyZiZj0xMTEwOSZvcz1PdGhlcnMmbTI9MGQ1OTYyZGQ1NjcwYzNhYzBhZWEzMzgwZmZkMmQ5N2QmY2k9NDc3MSZiaD0lJVdJTl9QUklDRSUlJmZwPTAmbD0xMTMyJm09MjgzJmJwPTEmcmVxaWQ9bHA2d3dsY3hhd2NjZSZicj1hd21OemF1TzIwR3FZLXNXWXFfVm1IZVFvVDU3V2RVVXlSUTVBX0pVMV91Q1JNWnR5MWloWmY2RXJBcG5rcWdiZEYyNjhMRlBFMjBmdkdzWFBGWEtxVVhRM2p5UGxoVjN4aUtpQnJ1TDBNcy1zTTVRZkNsZmljWWlsV2VkOVpnUkt3LXJubEU3bDNSREQ2TkNQck9YZmcmZHQ9LTEmdXBQaWQ9ODc5MTMwMzU4OTAzNTEwMzQzOSZvZD0zMCZpY3A9MCZ6PTU4NzgmcG49Y29tLnRlbmNlbnQucXFtdXNpYw==";
		byte[] decodeFast = Base64.decodeFast(s);
		String x = new String (decodeFast);
		System.out.println(x);
		
	}

}