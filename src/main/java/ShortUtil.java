import java.security.MessageDigest;
import java.util.Base64;

import sun.misc.BASE64Encoder;

public class ShortUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {

			resultString = new String(origin.trim());
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString.trim();

			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static String[] ShortText(String string) {
		String key = ""; // 自定义生成MD5加密字符串前的混合KEY
		String[] chars = new String[] { // 要使用生成URL的字符
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		String hex = MD5Encode(key + string);
		
		
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;
		String[] ShortStr = new String[4];

		for (int i = 0; i < subHexLen; i++) {
			String outChars = "";
			int j = i + 1;
			String subHex = hex.substring(i * 8, j * 8);
			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			for (int k = 0; k < 6; k++) {
				int index = (int) (Long.valueOf("0000003D", 16) & idx);
				outChars += chars[index];
				idx = idx >> 5;
			}
			ShortStr[i] = outChars;
		}

		return ShortStr;
	}

	public static void main(String[] args) {
		// String data =
		// "http://pgdt.gtimg.cn/gdt/0/DAAOcTXAKAAPAAA9BZJVpPAuY0Uci1.jpg/0?ck=a114ce1cd0fc09bb4d3b4a78ee1775ee";
		String data = "https://v.pic.fastapi.net/magic/4b/4b7dbff03b8c1d3b3e368ef067bf7377.jpg?p=aHR0cDovL3QxMi5iYWlkdS5jb20vaXQvdT0xNDU4NjUwODI4LDU3MTU3ODkyNSZmbT03Ng.VFRDUDpjcm9wX3Bvcz1jZW50ZXIBc2l6ZT03KjMBc2l6ZV90eXBlPXNjYWxl";
		
		System.out.println(MD5Encode(data));
		// String data = "http://finance.sina.com.cn/0?aa.jpg";
		print(data);
		// data =
		// "http://pgdt.gtimg.cn/gdt/0/DAAExvuAUAALQABHBYprVIComBZBTO.jpg/0?ck=dd2ff30240ebe863d3175fb61ed4c583";
		// print(data);
		// print("http://pgdt.gtimg.cn/gdt/0/DAACvqcAUAALQABiBYqk5cA40E69ki.jpg/0?ck=6dcae12d554f8e0ce2010676eed267d6");
		// print("http://finance.sina.com.cn");
		// print("http://finance.sina.com.cn");

		// String encodeToString =
		// Base64.getUrlEncoder().encodeToString(data.getBytes());
		// System.out.println(encodeToString);

		// String encode = new BASE64Encoder().encode(data.getBytes());
		// System.out.println(encode);

		String[] shortUrl = shortUrl(data);
		for (String string : shortUrl) {
			System.out.println(string);
		}
		// System.out.println(MD5Encode(data));

		// long t1 = System.currentTimeMillis();
		//
		// for (int i = 0; i < 10000; i++) {
		// String s = shortUrl(data)[0];
		// }
		// long t2 = System.currentTimeMillis();
		// System.out.println(t2 - t1);
	}

	private static void print(String data) {
		System.out.println(MD5Encode(data));
		String[] shortText = ShortText(data);
		for (String string : shortText) {
			System.out.println(string);
		}
	}

	public static String[] shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		// String key = "" ;
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = MD5Encode(url);
		String hex = sMD5EncryptResult;
		String[] resUrl = new String[4];
		// 得到 4组短链接字符串
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			// 循环获得每组6位的字符串
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				// (具体需要看chars数组的长度 以防下标溢出，注意起点为0)
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}
}
