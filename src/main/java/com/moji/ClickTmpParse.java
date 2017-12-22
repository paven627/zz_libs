package com.moji;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;


/**
 * 
 * 解析点击日志,取个别字段到新文件
 * 
 * @author bin.deng
 *
 */
public class ClickTmpParse {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\bin.deng\\Desktop\\gdt_1209click");

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;

		BufferedWriter writer = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\gdt_1209click.txt")));
		
		StringBuilder sb = new StringBuilder("");
		int i  = 0;
		while ((line = reader.readLine()) != null) {
			sb = new StringBuilder();
			System.out.println(i);
			i ++;
			line = findJson(line);
			
			JSONObject json = JSONObject.parseObject(line);
			// IMEI，IP地址，手机品牌，型号，广告位，点击坐标
			JSONObject common = json.getJSONObject("common");
			String imei = common.getString("identifier");
			String version = common.getString("app_version");
			String device = common.getString("device");
			long unix = common.getLong("unix");
			JSONObject params = json.getJSONObject("params");
			String index = params.getString("ad_index");
			String down_x = params.getString("down_x");
			String down_y = params.getString("down_y");
			String up_x = params.getString("up_x");
			String up_y = params.getString("up_y");

			writer.write(sb.append(imei).append(",").append(device).append(",").append(version).append(",").append(index).append(",").append(down_x).append(",").append(down_y)
			.append(",").append(up_x).append(",").append(up_y).append(",").append(new Date(unix).toLocaleString()).toString());
			
			
//			writer.write(imei + "," + device + ","  + index + "," + down_x + "," + down_y + "," + up_x + "," + up_y+","+  new Date(unix).toLocaleString());
			writer.newLine();
		}
		writer.close();
		reader.close();
	}

	private static String findJson(String readLine) {
		int firstIndex = readLine.indexOf("{");
		int lastIndex = readLine.lastIndexOf("}");
		String substring = readLine.substring(firstIndex, lastIndex + 1);
		return substring;
	}

}
