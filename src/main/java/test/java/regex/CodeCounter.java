package test.java.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodeCounter {
	static int normal = 0; // ����
	static int comment = 0; // ע��
	static int whiteLine = 0; // ����

	public static void main(String[] args) {
		File file = new File(
				"D:\\WorkSpace\\Hawkeye\\Hawkeye3.2.1\\Hawkeye\\src\\main\\java");

		getFile(file);
		System.out.println("代码: " + normal);
		System.out.println("注释:" + comment);
		System.out.println("空行 :" + whiteLine);
	}

	private static void getFile(File file) {
		if (file.isFile()) {
			parse(file);
		} else {
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isDirectory()) {
					getFile(f);
				} else {
					System.out.println(f);
					parse(f);
				}
			}
		}
	}

	private static void parse(File file) {
		int filenormal = 0;
		int filecomment = 0;
		int filewhiteLine = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			boolean isCommentLine = false;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.matches("^[\\s&&[^\\n]]*$")) {
					filewhiteLine++;
					whiteLine++;
				} else if (line.startsWith("/*") && !line.endsWith("*/")) {
					isCommentLine = true;
					filecomment++;
					comment++;
				} else if (line.startsWith("//")) {
					filecomment++;
					comment++;
				} else if (isCommentLine) {
					filecomment++;
					comment++;
					if (line.endsWith("*/")) {
						isCommentLine = false;
					}
				} else {
					filenormal++;
					normal++;
				}
			}
			System.out.println("����" + filewhiteLine);
			System.out.println("ע��" + filecomment);
			System.out.println("����" + filenormal);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
