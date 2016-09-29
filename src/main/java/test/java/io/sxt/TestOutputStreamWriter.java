package test.java.io.sxt;
import java.io.*;

public class TestOutputStreamWriter {
	public static void main(String[] args) {
		try {
			File file = new File("D:\\JavaSpace\\��ϰ\\src\\io/writer.java");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			out.write("aeorhapuehraiweuhraewr");
			System.out.println(out.getEncoding());
			out.close();
			out= new OutputStreamWriter(new FileOutputStream(file,true));
			out.write("aerearawerarawerejkahr");
			System.out.println(out.getEncoding());
			out.close();
		} catch (Exception e) {
			
		}
		System.out.println("�Ѿ�д��");
	}

}
