

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by billxiong on 7/21/16. !! JDK 1.7 调试通过
 */
public class Main {

	public static void main(String[] args) throws Exception {
		String app_key = "2fb4e1d";
		String app_secret = "fe0690eaf0a7b3f6ffd604f0f71c4a91";

		// 拼接需要签名字符串
		// 查询参数需要严格按照字典顺序排序之后再连接成字符串
		// String string_to_sign = "GET\n" +
		// "/api_v2/medias/1982/reports/ies\n" +
		// "app_key=2fb4e1d&attrs=id%2Cname%2Ccampaign%2Cmaterial&includes=campaign%2Cmaterial&maxResults=1&signature_method=HmacSHA256&signature_version=1&sort=-id&startIndex=0&timestamp=2017-11-21T09%3A53%3A25Z";

//		String stamp = "";
		String string_to_sign = "GET\n" + 
				"/api_v2/medias/1982/reports/ies\n" + 
				"app_key=2fb4e1d&date=2017-11-23&maxResults=1&pubId=PUB_10010093&signature_method=HmacSHA256&signature_version=1&startIndex=0&timestamp="
				+ "2017-11-28T04%3A25%3A04Z";

		byte[] signatureBytes = Codec.encryptHMAC(string_to_sign.getBytes(), // 待签名字符串
				Codec.encryptBASE64(app_secret.getBytes())   // HmacSHA256 加密所需密钥
		);

		// 此处注意：JDK 自带 Base64 类，编码后会多出来一个换行符，需要剔除。
		String encryptBASE64 = Codec.encryptBASE64(signatureBytes);
		System.out.println(1);
		System.out.println(encryptBASE64);
		System.out.println(1);
//		System.out.println(new sun.misc.BASE64Encoder().encode(signatureBytes));
		
		System.out.println(1);
		String signatureBase64 = encryptBASE64.replaceAll("\\n", "").replaceAll("\r", "");
		String signatureBase64AndEncoded = quote_plus(signatureBase64);

	
		System.out.println("Base64: " + signatureBase64);
		System.out.println("最终签名: " + signatureBase64AndEncoded);
	}

	// 空格会被编码为 "+"
	public static String quote_plus(String raw) throws UnsupportedEncodingException {
		return URLEncoder.encode(raw, "UTF-8");
	}

	/**
	 * Created by billxiong on 7/21/16.
	 */
	public static class Codec {

		public static final String ALGO_HMAC = "HmacSHA256";

		/**
		 * BASE64解密
		 */
		public static byte[] decryptBASE64(String key) throws Exception {
			return (new BASE64Decoder()).decodeBuffer(key);
		}

		/**
		 * BASE64加密
		 */
		public static String encryptBASE64(byte[] key) throws Exception {
			return (new BASE64Encoder()).encodeBuffer(key);
		}

		/**
		 * HMAC加密
		 */
		public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

			SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), ALGO_HMAC);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			return mac.doFinal(data);
		}
	}
}
