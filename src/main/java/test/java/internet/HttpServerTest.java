package test.java.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpServerTest {
	public static final String WEB_ROOT = "d:/logs";

	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServerTest server = new HttpServerTest();
		server.await();
	}

	public void await() {

		ServerSocket serverSocket = null;

		int port = 5888;

		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Loop waiting for a request
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				// create Request object and parse
				Request request = new Request(input);
				request.parse();

				// create Response object
				Response response = new Response(output);
				response.setRequest(request);

				try {
					Thread.sleep(195);

				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendJson(); // 返回json

				// response.sendStaticResource(); // 发送文件内容

				// Close the socket;
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

		}

	}
}