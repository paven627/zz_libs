import java.io.*;

public class MyTest {
	
	public MyTest() {
		super();
		System.out.println("����");
	}

	public static void main(String[] args) {
		System.out.println("�����ӽ���");
		while (true) {
			try {
				String strLine = new BufferedReader(new InputStreamReader(
						System.in)).readLine();
				if (strLine != null) {
					System.out.println("hi:" + strLine); // ��ȡ�����̵�����
				} else {
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}