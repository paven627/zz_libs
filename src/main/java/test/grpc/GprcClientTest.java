package test.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import test.grpc.pb.Adsort;
import test.grpc.pb.PredictionServiceGrpc;

import java.util.List;
import java.util.UUID;

/**
 * @author dengbin
 */
public class GprcClientTest {
    public static void main(String[] args) {
        clientTest();
    }

    private static void clientTest() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext().build();
        PredictionServiceGrpc.PredictionServiceBlockingStub bstub =
                PredictionServiceGrpc.newBlockingStub(channel);
        long l1 = System.currentTimeMillis();
        Adsort.Response response = bstub.predict(makeRequest());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        List<Adsort.Result> resultsList = response.getResultsList();
        System.out.println(resultsList);

    }

    private static Adsort.Request makeRequest() {
        Adsort.Request.Builder builder = Adsort.Request.newBuilder();
        builder.setRequestId(UUID.randomUUID().toString());

        Adsort.Client.Builder client = Adsort.Client.newBuilder();
        client.setImei("imei");

        client.setNetType("1");

        Adsort.DspAd.Builder dspAd = Adsort.DspAd.newBuilder();
        dspAd.setAdGroupId("1");
        builder.addAds(dspAd);
        builder.setAdPositionId("pid");
        builder.setSize(1);
        builder.setClient(client);

        return builder.build();
    }
}
