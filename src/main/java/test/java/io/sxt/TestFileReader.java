package test.java.io.sxt;
import java.io.*;
/**
 * ����һ��FileReader �ַ���,�����ļ���ȡ�����������
 */
public class TestFileReader {
	public static void main(String[] args) {
		long t1= System.currentTimeMillis();
		FileReader reader = null;
		// ����ת������ȡ���ַ�
		int c = 0;
		try {
			reader = new FileReader("D:\\JavaSpace\\��ϰ\\src\\io\\sxt/TestFileReader.java");
			int ln = 0;
			while((c=reader.read()) != -1) {
				System.out.print((char)c);
				ln++;
			}
			reader.close();
			System.out.printf("����ȡ��%d���ַ�",ln);
		}catch(FileNotFoundException e) {
			System.out.println("�Ҳ���ָ�����ļ�");
		} catch (IOException e1) {
			System.out.println("�ļ���ȡ����");
		}
		long t2= System.currentTimeMillis();
		System.out.println("�ܹ���ʱ:"+(t2-t1));

	}

}
