package test.java.io.sxt;
import java.io.*;
/**
 * ����һ�� FileOutputStream �ֽ������,������ļ������������
 * ��ͬĿ¼�µ�Test.java �ļ�,��� �ļ�������,���Զ�����
 * ��д��ʽ
 */
public class TestFileOutputStream {
	public static void main(String[] args) {
		int b = 0;
		FileOutputStream out = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream("D:\\JavaSpace\\��ϰ\\src\\io\\sxt\\TestFileInputStream.java");
			out = new FileOutputStream("D:\\JavaSpace\\��ϰ\\src\\io\\Test.java");
			while((b=in.read())!= -1){
				out.write(b);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ���ָ�����ļ�");
			System.exit(-1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("�ļ��Ը���");

	}

}
