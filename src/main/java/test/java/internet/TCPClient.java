package test.java.internet;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1",6666);
		OutputStream os =s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF("Hello Server");
		dos.flush();
		dos.close();
		s.close();
	}
	
}
