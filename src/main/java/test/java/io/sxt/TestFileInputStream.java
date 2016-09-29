package test.java.io.sxt;
import java.io.*;
/**
 * ����һ�� FileInputStream �ֽ�������,�����ļ������ݶ�ȡ���������,����¼�ֽ���
 */
public class TestFileInputStream {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		// ����ת����ȡ���ֽ�
		int b = 0;
		FileInputStream in = null; 
		try {
			in = new FileInputStream("D:\\Soft\\oracle\\product\\10.2.0\\db_1\\database\\PWDoracle.ora");
		}catch(FileNotFoundException e) {
			System.out.println("�Ҳ���ָ�����ļ�");
			System.exit(-1);
		}	
		try {
			// ������¼��ȡ���ֽ���
			long num = 0;
			while((b = in.read()) != -1 ) {
				System.out.print((char)b);
				num++;
			}
			in.close();
			System.out.println();
			System.out.format("����ȡ��%d���ֽ�",num);
		}catch(IOException e1) {
			System.out.println("�ļ���ȡ����");
			System.exit(-1);
		}
		long t2= System.currentTimeMillis();
		System.out.println("�ܹ���ʱ:"+(t2-t1));
	}
}