package test.java.io.sxt;
import java.io.*;
/**
 * ��Ŀ¼�µ�����ļ���д��0 - 50000 ���ַ�,���˴���������,�ļ�������,���Զ�����
 * �����������Զ�����Ŀ¼,ֻ���Զ������ļ�
 */
public class TestFileWriter {
	public static void main(String[] args) {
		long t1=System.currentTimeMillis();
		FileWriter writer = null;
		try{
			writer = new FileWriter("D:\\JavaSpace\\��ϰ\\src\\io\\back.java");
			for(int c=0; c<=20000;c++){
				writer.write(c);
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		long t2 =System.currentTimeMillis();
		System.out.println("�����ʱ:"+(t2-t1));
		
	}

}
