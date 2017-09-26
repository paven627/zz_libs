

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5Util {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String encryptMojiPsw(String info) {
		return encryptToMD5(info + "_moji");
	}

	/**
	 * 进行MD5加密
	 * 
	 * @param info
	 *            要加密的信息
	 * @return String 加密后的字符串
	 */
	public static String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}

	public static String[] encryptToMD5SplitByLength(String info, int length) {
		String md5 = encryptToMD5(info);

		int size = md5.length() / length;
		if (md5.length() % length != 0) {
			size++;
		}

		String[] result = new String[size];
		int index = 0;
		int startIndex = index * length;
		int endIndex = index * length + length;
		while (md5.length() > endIndex) {
			result[index] = md5.substring(startIndex, endIndex);
			index++;
			startIndex = index * length;
			endIndex = index * length + length;
		}

		result[index] = md5.substring(startIndex);
		return result;
	}

	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b
	 *            二进制字节数组
	 * @return String
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static String encryptToMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				alga.update(buffer, 0, length);
			}
			return new String(bufferToHex(alga.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	// 解密
	public static String decode(String str) {
		byte[] bts = new byte[str.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2),
					16);
		}
		return new String(bts);
	}

	// 加密
	public static String encode(String str) {
		byte[] bytes = str.getBytes();
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
					.substring(1).toUpperCase());
		}
		return retString.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

}