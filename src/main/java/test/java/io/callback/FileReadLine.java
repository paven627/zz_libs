package test.java.io.callback;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileReadLine {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws IOException {
		ReadlineCallback callback = new ReadlineCallback() {
			@Override
			public String readline(int lineNum, String src) {
				String[] split = src.split(",");
				String unix;
				try {
					unix = split[0].substring(0, 13);
					if (unix.length() < 13) {
						System.out.println(lineNum + ","+ src);
						return null;
					}
					Date d = new Date(Long.valueOf(unix));
					return sdf.format(d) + "," + split[1];
				} catch (Exception e) {
					System.out.println(lineNum + ","+ src);
					return null;
				}
			}
		};
//
		fileEachLine(new File("C:\\Users\\bin.deng\\Desktop/shunfei/mojiImeiTime"), new File("C:\\Users\\bin" +
				".deng\\Desktop\\shunfei\\mojiImeiTimeConvert"), callback);
	}


	public static void fileEachLine(File src, File dest, ReadlineCallback callback) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(src));

		String line = null;

		BufferedWriter writer = new BufferedWriter(
				new FileWriter(dest));

		int i = 0;
		while ((line = reader.readLine()) != null) {
			i++;
			line = callback.readline(i, line);
			if (line == null) {
				continue;
			}
			writer.write(line);
			writer.newLine();
		}
		writer.close();
		reader.close();
		System.out.println("行数:" + i);
	}

}
