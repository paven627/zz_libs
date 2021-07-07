package test.java.internet.completefuture;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author dengbin
 */
public class AsyncCallback {
    public static CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault();


    private static void streamCallback() throws InterruptedException {
        //流方式调用
        final CountDownLatch latch2 = new CountDownLatch(1);
        final HttpGet get3 = new HttpGet("http://10.11.8.40/td");
        HttpAsyncRequestProducer producer3 = HttpAsyncMethods.create(get3);
        AsyncCharConsumer<HttpResponse> consumer3 = new AsyncCharConsumer<HttpResponse>() {
            HttpResponse response;

            @Override
            protected void onResponseReceived(final HttpResponse response) {
                this.response = response;
            }

            @Override
            protected void releaseResources() {
            }

            @Override
            protected HttpResponse buildResult(final HttpContext context) {
                return this.response;
            }

            @Override
            protected void onCharReceived(CharBuffer buf, IOControl arg1) throws IOException {
                System.out.println("GET(流方式)返回结果:" + buf.toString());
            }
        };
        httpAsyncClient.execute(producer3, consumer3, new FutureCallback<HttpResponse>() {
            public void completed(final HttpResponse response) {
                latch2.countDown();
                System.out.println("GET(流方式)返回状态：" + response.getStatusLine());
            }

            public void failed(final Exception e) {
                latch2.countDown();
                e.printStackTrace();
            }

            public void cancelled() {
                latch2.countDown();
                System.out.println("cancelled");
            }
        });
        latch2.await();
    }
    static String json = null;
    public static CompletableFuture<String> getHttpData(String url) {

        CompletableFuture asyncFuture = new CompletableFuture();

        HttpGet get = new HttpGet(url);

        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(get);


        AsyncCharConsumer<HttpResponse> consumer = new AsyncCharConsumer<HttpResponse>() {
            HttpResponse response;

            @Override
            protected void onCharReceived(CharBuffer buf, IOControl ioctrl) throws IOException {
                json = buf.toString();
                System.out.println("onCharReceived: " + json );

            }

            @Override
            protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                response = httpResponse;
                System.out.println(statusLine);
                System.out.println("onResponseReceived");
            }

            protected HttpResponse buildResult(final HttpContext context) {
                return response;
            }
        };

        FutureCallback callback = new FutureCallback<HttpResponse>() {

            public void completed(HttpResponse response) {
//                try {
                    System.out.println("complete");
//                    String value = EntityUtils.toString(response.getEntity());
//                    System.out.println(value);
//                    asyncFuture.complete(EntityUtils.toString(response.getEntity()));
                    asyncFuture.complete(json);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }

            @Override
            public void failed(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("cancel");
            }

        };

        httpAsyncClient.execute(producer, consumer, callback);
        return asyncFuture;
    }


    public static void main(String[] args) throws Exception {
        httpAsyncClient.start();

//        streamCallback();

        CompletableFuture<String> future1 = AsyncCallback.getHttpData("http://10.11.8.40/td");
        future1.whenComplete((s, throwable) -> System.out.println("TAG 计算结果" + s));

//        String s = future1.get();
//        System.out.println(s);


//        CompletableFuture future2 = AsyncCallback.getHttpData("http://10.11.8.40/td");
//        CompletableFuture future3 = AsyncCallback.getHttpData("http://10.11.8.40/td");
//
//
//
//        List<CompletableFuture> futureList = Lists.newArrayList(future1, future2, future3);
//
//        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
//
//        CompletableFuture<String> future4 = allDoneFuture.thenApply(v -> {
//
//            List<Object> result = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
//
//            //注意顺序
//
//            String result1 = (String) result.get(0);
//
//            String result2 = (String) result.get(1);
//
//            String result3 = (String) result.get(2);
//
//            //处理业务....
//
//            return result1 + result2 + result3;
//
//        }).exceptionally(e -> {
//            e.printStackTrace();
//            return "";
//
//        });
//        System.out.println(future4.get());
        Thread.sleep(1000000);



    }


}
