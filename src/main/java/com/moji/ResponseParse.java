package com.moji;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.RequestUtil;

import com.alibaba.fastjson.JSONObject;

public class ResponseParse {
	public static void main(String[] args) throws IOException {
		File fileIn = new File("D:/gdt_1217.txt");
//		File fileIn = new File("D:/test.txt");
		File fileOut = new File("D:/gdt_1217_out.txt");

		BufferedReader reader = new BufferedReader(new FileReader(fileIn));

		String line = null;

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));

		int i = 0;
		String url ;
		while ((line = reader.readLine()) != null) {
			line = findJson(line);
			i++;
			if(i % 100 == 0) {
				System.out.println(i);
			}
//			sb = new StringBuilder();
			JSONObject json = JSONObject.parseObject(line);
			url = json.getString("url");
			
			
			String viewid = getParamValue(url, "http://c.gdt.qq.com/gdt_mclick.fcg?viewid");
			writer.write(viewid);
			writer.newLine();
		}
		writer.close();
		reader.close();
	}

	private static String getParamValue (String url , String key) {
		Map<String, String[]> values = new HashMap<String, String[]>();
		RequestUtil.parseParameters(values, url, "UTF-8");
		return values.get(key)[0];
	}
	
	private static String findJson(String readLine) {
		int firstIndex = readLine.indexOf("{");
		int lastIndex = readLine.indexOf("}");
		String substring = readLine.substring(firstIndex, lastIndex + 1);
		return substring;
	}

}
