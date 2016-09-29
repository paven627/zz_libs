package test.java.io.sxt;
import java.io.*;
/**
 * ����һ�� FileInputStream �ֽ�������,�����ļ������ݶ�ȡ���������,����¼�ֽ���
 */
public class TestFileInputStream2 {
	public static void main(String[] args) {
		long t1 =System.currentTimeMillis();
		File file = new File("D:\\JavaSpace\\��ϰ\\src\\io\\TestFileInputStream.java");
		InputStream in = null; 
		try {
			in = new FileInputStream(file);
		}catch(FileNotFoundException e) {
			System.out.println("�Ҳ���ָ�����ļ�");
			System.exit(-1);
		}	
		try {
			byte[] b = new byte[(int)file.length()];
			for(int i = 0;i<b.length;i++) {
				b[i]=(byte)in.read();
			}
			String s =new String(b);
			System.out.println(s);
			in.close();
		}catch(IOException e1) {
			System.out.println("�ļ���ȡ����");
			System.exit(-1);
		}
		long t2=System.currentTimeMillis();
		System.out.println("�ܹ���ʱ:"+(t2-t1));
	}
}