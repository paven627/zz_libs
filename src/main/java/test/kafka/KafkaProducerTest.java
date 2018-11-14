package test.kafka;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaProducerTest {
    static String s = " - {'common': {},'params': {'city_id': %s,'materiel_id':'0',"
            + "'ad_index': 306, 'adverting_type':2,'stat_value': 1," +
            "'ad_id': 400000000363,'price': '1.0','stat_type': 2}}";
    static HttpClient httpClient = new DefaultHttpClient();

    public static void main(String[] args) {
        sendkafka();
//        sendNginx();
    }


    private static void sendkafka() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "192.168.1.17:9092,192.168.1.17:9093,192.168.1.17:9094");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 10);
        props.put("linger.ms", 1000);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        String topic = "moji_ad_stream26";
        int  count = 0;
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int hour = 0; hour <23; hour++) {
            String hourStr;
            if (hour < 10) {
                hourStr = "0" + hour;
            } else {
                hourStr = String.valueOf(hour);
            }
//            2018-06-06 10:19:12
            for (int minute = 0; minute <60; minute++) {
                String value;
                if (minute < 10) {
                    value = "2018-11-07 " + hourStr + ":0" + minute + ":12 " + String.format(s, hour);
                } else {
                    value = "2018-11-07 " + hourStr + ":" + minute + ":12 " + String.format(s, hour+1);
                }
//                Logger.getLogger(KafkaProducerTest.class).info(value);
                ProducerRecord<String, String> msg = new ProducerRecord<>(topic, value);
                producer.send(msg);
                count ++;
            }
        }
        //列出topic的相关信息
//        List<PartitionInfo> partitions = producer.partitionsFor(topic);
//        for (PartitionInfo p : partitions) {
//            System.out.println(p);
//        }

        System.out.println("send message over." + count);
        producer.flush();
        producer.close(100, TimeUnit.MILLISECONDS);
    }

}
