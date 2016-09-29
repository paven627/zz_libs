package test.java.internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��ѧ����������
 */
public class TCPServer {
	public static void main (String[] args)throws Exception{
		ServerSocket ss = new ServerSocket(6666);
		//accept() ������ȴ�ͻ�������,�Ż����ִ��
		while(true) {
			Socket s = ss.accept();
			DataInputStream dis= new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		}
	}

}
