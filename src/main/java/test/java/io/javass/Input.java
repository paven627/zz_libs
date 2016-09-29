package test.java.io.javass;
import java.io.*;

public class Input {
	public static void main(String[] args) {
		File file= new File("d:/javaSpace/��ϰ/src/io/javass/Input.java");
		FileInputStream in = null;
		try {
			 in = new FileInputStream(file);
			 //byte[] b=new byte[in.available()];
			 byte[] b = new byte[(int)file.length()];
			 in.read(b);
			 in.close();
			 System.out.println("�ļ�����:" +new String(b));			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ļ�û���ҵ�");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}

}
