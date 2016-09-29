package test.java.internet;
import java.net.*;
import java.io.*;

/** 
	TestServer �� TestClient �ͻ��˶�ȡ�ͻ��˵� IP�Ͷ˿ں�
	���ҷ��ͻؿͻ���,��ʾ��TestClient ����
*/
public class TestClient {
	public static void main(String[] args) {
		try {
			Socket s1 = new Socket("127.0.0.1",8888);	
			InputStream is = s1.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF());
			dis.close();
			s1.close();
		}catch (ConnectException connExc) {
			connExc.printStackTrace();
			System.out.println("����������ʧ��");
		}catch(IOException ioExc) {
			ioExc.printStackTrace();
		}
	}
}