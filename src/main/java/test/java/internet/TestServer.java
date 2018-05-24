package test.java.internet;

/* �򵥵�client/ server ����
 	1. java Socket ��̲���
 	2. Socket / ServerSocket ���÷�
 	3. ͨ��Socket ������Ի�ȡͨ�ŶԷ���Socket ��Ϣ
 	
 */
 import java.net.*;
 import java.io.*;
 public class TestServer {
 	public static void main(String[] args) {
 		try {
 			ServerSocket s = new ServerSocket(8888);
 			while(true) {
 				Socket s1 = s.accept();
 				OutputStream os = s1.getOutputStream();
 				DataOutputStream dos = new DataOutputStream(os);
 				dos.writeUTF("hello, " + s1.getInetAddress() +" port#" +s1.getPort() +
 				"  bye-bye!");
 				dos.close();
 				s1.close();
 			}
 		}catch(IOException e) {
 				e.printStackTrace();
 				System.out.println("�������г���");
 		}	
 	}		
}