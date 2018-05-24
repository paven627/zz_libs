package test.java.internet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Response {

	private static final int BUFFER_SIZE = 1024;

	private Request request;
	private OutputStream output;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		boolean errorFlag = true;
		if (request.getUri() != null) {
			File file = new File(HttpServerTest.WEB_ROOT, "order.txt");
			if (file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
				errorFlag = false;
			}
		}
		if (errorFlag) {
			// file not found
			String errorMessage = "HTTP/1.1 404 File NOT Fount\r\n" + "Content-Type: text/html\r\n"
					+ "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
			output.write(errorMessage.getBytes());
		}
		if (fis != null) {
			fis.close();
		}
	}

	public void sendJson() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.output));
		bw.write("HTTP/1.1 200 OK\n");
		bw.write("Content-Type: text/html; charset=UTF-8\n\n");
		bw.write(
				"{\"quality\": 0,\"data\": {\"clickurl\": \"http://www.baidu.com\",\"adstyle\": 1024,\"feed_type\": 6,\"isWifiDownload\": 0,"
				+ "\"adwidth\": 0,\"vedioHeight\": 0,\"appStar\": 0,\"clktrack\": \"http://192.168.1.181:9083/click_$msec\",\"imptrack\": \"http://192.168.1.181:9083/imp_$msec\", \"winnoticeurl\": \"http://192.168.1.181:9083/win\",\"imgurl\": \"http://img.sccnn.com/bimg/338/34264.jpg\",\"blockHeight\": 0,\"vedioWidth\": 0,\"adheight\": 0,\"isAutoPlay\": 0,\"adtype\": 2,\"playValidTime\": 0,\"sessionid\": \"mojisessionid\",\"adtext\": \"这是一个墨迹测试的广告\",\"pageType\": 0,\"adid\": \"mojiadid\",\"duration\": 0,\"price\": 100000000,\"chargingtype\": \"1\",\"adtitle\": \"墨迹测试\",\"addCoordinate\": 0},\"secondAdPrice\": 0,\"code\": 200,\"costTime\": 0}");
		bw.flush();
        bw.close();
        bw.close();
	}
}