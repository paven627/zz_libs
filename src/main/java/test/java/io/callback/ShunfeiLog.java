package test.java.io.callback;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShunfeiLog {
	public static void main(String[] args) throws IOException {
		fileEachLine();
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Map<String, List<String>> shunfeiImeis = new HashMap<>(200000);

	//1找出相同的IMEI号
	public static void fileEachLine() throws IOException {
		File shunfei = new File("C:\\Users\\bin.deng\\Desktop\\shunfei\\shunfeiimei");
		File mojiImei = new File("C:\\Users\\bin.deng\\Desktop\\shunfei\\mojiImeiTime");
		// 共同的imei号出现在墨迹日志中的时间
		BufferedWriter mojiImeiWriter = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\shunfei\\mojitime")));
		BufferedWriter writer2 = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\shunfei\\墨迹没有的imei.log")));
		BufferedWriter SameImei = new BufferedWriter(
				new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\shunfei\\sameimei.log")));


		// 读取舜飞的日志文件放进map
		BufferedReader shunfeiReader = new BufferedReader(new FileReader(shunfei));
		String line = null;
		int i = 0;
		String imei;
		while ((line = shunfeiReader.readLine()) != null) {
			i++;
			imei = line.split(",")[1];
			List<String> list = shunfeiImeis.get(imei);
			if (list == null) {
				list = new ArrayList<>();
			}
			list.add(line);
			shunfeiImeis.put(imei, list);
		}

		//读墨迹日志
		BufferedReader mojiIMEIReader = new BufferedReader(new FileReader(mojiImei));
		i = 0;
		while ((line = mojiIMEIReader.readLine()) != null) {
			i++;
			imei = line.split(",")[1];
			List<String> imeis = shunfeiImeis.remove(imei);

			// 双方相同的imei 在舜飞中出现的
			if (imeis != null) {
				if (imeis.size() == 1) {
					SameImei.write(imeis.get(0));
					SameImei.newLine();
				} else {
					for (String l : imeis) {
						SameImei.write(l);
						SameImei.newLine();
					}
				}
				String[] split = line.split(",");
				String unix = null;
				try {
					unix = split[0].substring(0, 13);
				} catch (Exception e) {
					System.out.println(i + "," + line);
					continue;
				}
				Date d = new Date(Long.valueOf(unix));

				//  双方相同imei 在墨迹出现的
				mojiImeiWriter.write(sdf.format(d) + "," + split[1]);
				mojiImeiWriter.newLine();
			}
		}
		//  舜飞日志中不存在与墨迹日志中的
		for (Map.Entry<String, List<String>> stringListEntry : shunfeiImeis.entrySet()) {
			String key = stringListEntry.getKey();
			writer2.write(key);
			writer2.newLine();
			writer2.flush();
		}

		mojiImeiWriter.close();
		SameImei.close();
		writer2.close();
		shunfeiReader.close();
		mojiIMEIReader.close();
		System.out.println("行数:" + i);
	}


	interface writeResult {
		void write();
	}
}
