package test.java.io.sxt;
import java.io.*;
public class TestBuffered {
	public static void main(String[] args) {
		long t1=System.currentTimeMillis();
		try {
			File file = new File("D:\\JavaSpace\\��ϰ\\src\\io\\back.java");
			FileInputStream in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			int c = 0 ;
			System.out.println(bis.read());
			bis.mark(100);
			for (int i = 0; i < file.length() &&(c=bis.read())!=-1; i++) {
				System.out.print((char)c+" ");
			}
			System.out.println();
			bis.reset();
			for (int i = 0; i <=file.length()&& (c=bis.read())!=-1; i++) {
				System.out.print((char)c+" ");
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2= System.currentTimeMillis();
		System.out.println("�ܹ���ʱ"+(t2-t1));
	}
}
