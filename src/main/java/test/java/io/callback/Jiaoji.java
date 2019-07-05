package test.java.io.callback;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jiaoji {

	public static void main(String[] args) throws IOException {

		BufferedReader jiaoji = new BufferedReader(new FileReader("C:\\Users\\bin.deng\\Desktop\\shunfei\\jiaoji"));
		BufferedReader moji = new BufferedReader(new FileReader("C:\\Users\\bin" +
				".deng\\Desktop\\shunfei\\mojiImeiTimeConvert"));
		BufferedReader shunfei = new BufferedReader(new FileReader("C:\\Users\\bin" +
				".deng\\Desktop\\shunfei\\shunfeiimeitime"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("C:\\Users\\bin.deng\\Desktop\\shunfei\\hebing"));

		String line = null;

		Map<String, List<String>> mojiLogs = new HashMap<>(20000);
		Map<String, List<String>> shunfeiLogs = new HashMap<>(20000);

		while ((line = moji.readLine()) != null) {
			String imei = line.split(",")[1];
			List<String> list = mojiLogs.get(imei);
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(line);
			mojiLogs.put(imei, list);
		}

		while ((line = shunfei.readLine()) != null) {
			String imei = line.split(",")[1];
			List<String> list = shunfeiLogs.get(imei);
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(line);
			shunfeiLogs.put(imei, list);
		}

		int i = 0;
		while ((line = jiaoji.readLine()) != null) {
			i++;
			List<String> immoji = mojiLogs.get(line);
			List<String> imsf = shunfeiLogs.get(line);
			if ((immoji == null && imsf != null) || (immoji != null && imsf == null)) {
				System.out.println("其中一方没有:" + line);
			}
			for (String s : imsf) {
				writer.write("sf:" + s);
				writer.newLine();
			}
			for (String s : immoji) {
				writer.write("moji:"+ s);
				writer.newLine();
			}
		}
		jiaoji.close();
		moji.close();
		shunfei.close();
		writer.close();

		System.out.println("行数:" + i);
	}

}
