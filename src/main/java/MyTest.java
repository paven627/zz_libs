import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class MyTest {
	static Map<String, String>  tempMap = new HashMap<>();
	
	public static void main(String[] args) throws UnknownHostException, IOException   {
		Socket socket = new Socket("192.168.1.182", 21567);
		OutputStream outputStream = socket.getOutputStream();
		
		System.out.println(outputStream);
		
	}
}