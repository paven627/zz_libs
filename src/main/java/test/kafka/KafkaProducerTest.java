package test.kafka;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class KafkaProducerTest {

    static String s = "{\"@timestamp\":\"2020-08-04T14:17:48.902Z\",\"@metadata\":{\"beat\":\"socketbeat\"," +
            "\"type\":\"doc\",\"version\":\"6.0.0\",\"topic\":\"topic.cloud.service.bigdata.dsp.imp_clk\"}," +
            "\"target\":\"bigdata.dsp.imp_clk\",\"message\":\"{\\\"params\\\":{\\\"campaign_id\\\":\\\"1006\\\"," +
            "\\\"test\\\":\\\"true\\\",\\\"ext\\\":\\\"redis_blacklist\\\"," +
            "\\\"advertiser_id\\\":\\\"1002\\\",\\\"ad_group_id\\\":\\\"1012\\\",\\\"creative_id\\\":\\\"10121\\\"," +
            "\\\"agent_id\\\":\\\"1000\\\",\\\"core_agent_id\\\":\\\"1142\\\",\\\"requestId\\\":\\\"ccc1\\\"," +
            "\\\"stat_type\\\":\\\"1\\\",\\\"stat_value\\\":1,\\\"unqid\\\":\\\"%s\\\"," +
            "\\\"ad_type\\\":\\\"2\\\",\\\"advertising_media\\\":\\\"1001\\\",\\\"adx_id\\\":\\\"1000\\\"," +
            "\\\"position_id\\\":\\\"9383938\\\",\\\"price\\\":\\\"102.0\\\",\\\"settlement_price\\\":\\\"\\\"}," +
            "\\\"common\\\":{\\\"os\\\":\\\"1\\\",\\\"imei\\\":\\\"aaa\\\",\\\"app_version\\\":\\\"\\\"," +
            "\\\"osv\\\":\\\"\\\",\\\"device\\\":\\\"HUAWEI\\\",\\\"language\\\":\\\"chinese\\\"," +
            "\\\"sid\\\":\\\"abc\\\",\\\"width\\\":\\\"320\\\",\\\"height\\\":\\\"480\\\"," +
            "\\\"package_name\\\":\\\"a\\\",\\\"net\\\":\\\"0\\\",\\\"request_time\\\":\\\"1596535200000\\\"," +
            "\\\"ip\\\":\\\"49.117.193.248\\\",\\\"oaid\\\":\\\"aaa\\\",\\\"idfa\\\":\\\"\\\"}}\"," +
            "\"type\":\"socketbeat\",\"beat\":{\"name\":\"socketbeat\",\"hostname\":\"vm-qa-dsp-datasync001.zz.apus" +
            ".com\",\"version\":\"6.0.0\"}}";
    static HttpClient httpClient = new DefaultHttpClient();

    public static void main(String[] args) throws ParseException {
        sendkafka();

//		randomDate();

    }

    private static void sendkafka() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "dev-kafka.apuscn.com:9092");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 100);
        props.put("linger.ms", 1000);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        String topic = "topic.cloud.service.bigdata.dsp.imp_clk";
        int count = 0;
        Producer<String, String> producer = new KafkaProducer<>(props);
//
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            String value = String.format(KafkaProducerTest.s, uuid);
            System.out.println(value);
            ProducerRecord<String, String> msg = new ProducerRecord<>(topic, 1,"0", value);
            producer.send(msg);
            count++;
        }

//		列出topic的相关信息
        List<PartitionInfo> partitions = producer.partitionsFor(topic);
        for (PartitionInfo p : partitions) {
            System.out.println(p);
        }

        System.out.println("send message over." + count);

        producer.flush();
        producer.close(5000, TimeUnit.MILLISECONDS);
    }


}
