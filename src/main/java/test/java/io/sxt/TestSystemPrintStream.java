package test.java.io.sxt;
import java.io.*;
public class TestSystemPrintStream {
	public static void main(String[] args) {
		File file = new File("d:/log.txt");  //����һ��Ŀ�ĵ��ļ�
		PrintStream ps = null;
		try {
			FileOutputStream stream = new FileOutputStream(file);
			 ps = new PrintStream(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ps != null) {
			System.setOut(ps);
		}
		int ln = 0;
		for (int i = 0; i <=10000; i++) {
			System.out.println(i+" ");
		}
	}

}
