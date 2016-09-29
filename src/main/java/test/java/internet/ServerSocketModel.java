package test.java.internet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketModel {

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("服务器已启动...............");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("客户端已连接IP地址为：" + socket.getInetAddress());

			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String readContent;
			while ((readContent = bufferedReader.readLine()) != null) {
				System.out.println("ReadFromClient:" + readContent);
				if (readContent.length() == 0)
					break;
			}

			// 封装输出流
			OutputStream outputStream = socket.getOutputStream();

			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(outputStream));

			// 封装输入流
			System.out.println("读取完毕，准备输出返回信息.");
			bufferedWriter.write("HTTP/1.1 200 OKn");
			bufferedWriter.write("Content-Length: 10n");
			bufferedWriter.write("Content-Type: text/html;charset=utf8n");
			bufferedWriter.write("n"); // 区分HEAD区和正文区
			bufferedWriter.write("1234567890");
			System.out.println("返回信息输出完毕.");
			bufferedWriter.flush();
			socket.close();
			System.out.println("客户端连接已释放.");

		}

	}
}