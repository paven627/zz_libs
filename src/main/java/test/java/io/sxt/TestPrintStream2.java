package test.java.io.sxt;
import java.io.*;
public class TestPrintStream2 {
	public static void main(String[] args) {
		String fileName ="d:/log.txt";
		if (fileName!= null) {
			list(fileName,System.out);
		}
	}

	private static void list(String fileName, PrintStream out) {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(fileName)
					);
			String s = null;
			while((s=br.readLine())!= null){
				out.println(s);
			}
			br.close();
		} catch (Exception e) {
			out.print("�޷���ȡ�ļ�");
		}
	}

}
