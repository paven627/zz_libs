package test.java.internet;
import java.net.*;
 import java.io.*;
 
 public class TestSocketClient {
 	public static void main(String[] args) {
 		InputStream inputStream = null;
 		OutputStream outputStream = null;
 		try {
 			Socket socket = new Socket("localhost",5888);
 			inputStream = socket.getInputStream();
 			outputStream = socket.getOutputStream();
 			DataInputStream dis = new DataInputStream(inputStream);
 			DataOutputStream dos = new DataOutputStream(outputStream);
 			dos.writeUTF("this is client");
 			String s = dis.readUTF();
 			if(s != null) {
 				System.out.println(s);
 				System.out.println("server's IP is "+socket.getInetAddress());
 				dos.close();
 				dis.close();
 				socket.close();
 			}
 		}catch(UnknownHostException e) {
 			e.printStackTrace();
 		}catch(IOException e) {
 			e.printStackTrace();	
 		}
 	}	
}