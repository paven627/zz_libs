package test.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import test.grpc.pb.Adsort;
import test.grpc.pb.PredictionServiceGrpc;

import java.io.IOException;

/**
 * @author dengbin
 */
public class GrpcServerTest {

    public static void main(String[] args) throws Exception {
        serverStart();
    }


    public static void serverStart() throws IOException, InterruptedException {
        int port = 50051;
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        Server server = serverBuilder
                .addService(new PredictionServiceGrpc.PredictionServiceImplBase() {
                    @Override
                    public void predict(Adsort.Request request, StreamObserver<Adsort.Response> responseObserver) {
                        System.out.println("request:" + request);
                        Adsort.Response.Builder r = Adsort.Response.newBuilder();
                        Adsort.Result.Builder b = Adsort.Result.newBuilder();


                        b.setAdCreativeId("1014");
                        r.addResults(b);

//                        b=Adsort.Result.newBuilder();
//                        b.setAdCreativeId("1012");
//                        r.addResults(b);
//
//                        b=Adsort.Result.newBuilder();
//                        b.setAdCreativeId("1013");
//                        r.addResults(b);
//
//                        b=Adsort.Result.newBuilder();
//                        b.setAdCreativeId("1067");
//                        r.addResults(b);

//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        responseObserver.onNext(r.buildPartial());
                        responseObserver.onCompleted();
                    }
                }).build()
                .start();

        System.out.println("started");
        synchronized (GrpcServerTest.class) {
            GrpcServerTest.class.wait();
        }
//        Thread.sleep(1000);
//        server.shutdown();
//        System.out.println("shutdown");
    }

}
