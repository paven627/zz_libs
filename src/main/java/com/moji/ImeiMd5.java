package com.moji;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.security.MessageDigest;
import java.util.Date;


/**
 * 
 * 解析点击日志,取个别字段到新文件
 * 
 * @author bin.deng
 *
 */
public class ImeiMd5 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\bin.deng\\Desktop\\jingchun\\imei.txt");

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;

		BufferedWriter iosWriter = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\jingchun\\ios")));
		BufferedWriter androidWriter = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\jingchun\\andro")));

		StringBuilder sb;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			i++;
			sb = new StringBuilder();
//			System.out.println(i);
			BufferedWriter writer;
			if (line.indexOf("-") > 1) {
				// ios
				writer = iosWriter;
			} else {
				writer = androidWriter;
			}
			writer.write(md5Uppercase(line));
			writer.newLine();
		}
		iosWriter.close();
		androidWriter.close();
		reader.close();
	}

	public static String md5Uppercase(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}
}
