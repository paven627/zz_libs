package test.java.io.javass;
import java.io.*;

public class TestOutput {
	public static void main(String[] args) {
		File file = new File("D:\\JavaSpace\\��ϰ\\src\\io\\Test.java");
		FileOutputStream out =null;
		String s = "ear\n\r aer\n aeraaeraeeraer������\n\rλ\n����";
		try {
			out = new FileOutputStream(file);
			byte[] b = s.getBytes();
			
			out.write(b);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("�ļ�û���ҵ�");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("�Ѿ�����ɹ�");
	}

}
