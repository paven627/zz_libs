package test.java.internet;
import java.io.*;
import java.net.*;

public class TestSocketServer {
	public static void main(String[] args ) {
		InputStream in = null;
		OutputStream out = null;
		try {
			ServerSocket ss = new ServerSocket(5888);
			Socket socket =ss.accept();
			in = socket.getInputStream();
			out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			DataInputStream dis = new DataInputStream(in);
			String s = null;
			// �ӿͻ��˲�ͣ�Ķ�,ֻҪ���벻Ϊ��,�ͰѶ˿ں�ip д����
			if ((s = dis.readUTF()) != null) {
				System.out.println(s);
				System.out.println("from : "+ socket.getInetAddress());
				System.out.println("port :  "+ socket.getPort());
			}
			dos.writeUTF("Hello ! This is Server ");
			dis.close();
			dos.close();
			socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}	
}