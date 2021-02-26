package test.java.internet.httpclient;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class JDKHttpServer {

    public static void main(String[] args) throws IOException {
        HttpServer hs = HttpServer.create(new InetSocketAddress(80), 0);
        hs.createContext("/rta", new JsonHandler());
        hs.setExecutor(null);
        hs.start();
    }

}


class JsonHandler implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = t.getRequestBody();
                try {
                    byte[] request = new byte[is.available()];
                    is.read(request);
                    String x = new String(request);
                    System.out.println(x);

                    String response = "{\"usergrowth_dhh_delivery_ask_response\":{\"result\":true,\"task_id\":\"1\"," +
                            "\"errcode\":0,\"task_id_list\":{\"string\":[\"2\",\"1\",\"3\"]}}}";


//                    if(x.indexOf("advertising_space_id=1012") > 1) {
                        Thread.sleep(500);
//                    }


                    t.sendResponseHeaders(200, response.length());

                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
