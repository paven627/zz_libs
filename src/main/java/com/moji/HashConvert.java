package com.moji;

import test.java.io.callback.ReadlineCallback;

import java.io.*;

public class HashConvert {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\bin.deng\\Desktop\\launch\\ip");
		ReadlineCallback call = new ReadlineCallback() {
			@Override
			public String readline(int lineNum, String src) {
				String[] split = src.split(",");
				String source = split[0];
				String dest = split[1];


				String local = convert(source);
				String remote = convert(dest);
				System.out.println(local + " ----> " + remote);


				return null;
			}

		};
		fileEachLine(file, call);
	}

	private static String convert(String s) {
		String[] ip_port = s.split(":");
		String ip = convertToIp(ip_port[0]);
		int port = convertToInt(ip_port[1]);
		return ip + ":" + port;
	}

	private static String convertToIp(String hex) {
		String temp;
		StringBuilder sb = new StringBuilder();
		for (int i = 8; i > 0; i -= 2) {
			temp = hex.substring(i - 2, i);
			int i1 = convertToInt(temp);
			sb.append(i1);
			if (i > 3) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	private static int convertToInt(String hex) {
		return Integer.parseInt(hex, 16);
	}

	public static void fileEachLine(File src, ReadlineCallback callback) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(src));
		String line = null;

//		BufferedWriter writer = new BufferedWriter(
//				new FileWriter(dest));

		int i = 0;
		while ((line = reader.readLine()) != null) {
			i++;
			line = callback.readline(i, line);
			if (line == null) {
				continue;
			}
//			writer.write(line);
//			writer.newLine();
		}
//		writer.close();
		reader.close();
		System.out.println("行数:" + i);
	}
}
