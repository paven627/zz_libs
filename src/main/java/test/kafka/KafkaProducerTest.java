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
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class KafkaProducerTest {

	static String s = "{\"@timestamp\":\"2020-05-11T16:28:29.560Z\",\"@metadata\":{\"beat\":\"socketbeat\"," +
            "\"type\":\"doc\",\"version\":\"6.0.0\",\"topic\":\"topic.cloud.service.bigdata.dsp.imp_clk\"}," +
            "\"message\":\"{\\\"params\\\":{\\\"campaign_id\\\":\\\"1109\\\",\\\"advertiser_id\\\":\\\"1211\\\"," +
            "\\\"ad_group_id\\\":\\\"1171\\\",\\\"creative_id\\\":\\\"1164\\\",\\\"agent_id\\\":\\\"1209\\\"," +
            "\\\"core_agent_id\\\":\\\"1\\\",\\\"requestId\\\":\\\"%s\\\"," +
            "\\\"stat_type\\\":\\\"1\\\",\\\"stat_value\\\":1," +
            "\\\"unqid\\\":\\\"af01c478-eaf0-48e0-bb6f-d22f4231b557\\\",\\\"ad_type\\\":\\\"1\\\"," +
            "\\\"advertising_media\\\":\\\"1001\\\",\\\"adx_id\\\":\\\"1000\\\",\\\"position_id\\\":\\\"9383938\\\"," +
            "\\\"price\\\":\\\"40000.0\\\",\\\"settlement_price\\\":\\\"\\\"},\\\"common\\\":{\\\"os\\\":\\\"1\\\"," +
            "\\\"imei\\\":\\\"8635590393099185\\\",\\\"app_version\\\":\\\"\\\",\\\"osv\\\":\\\"\\\"," +
            "\\\"device\\\":\\\"HUAWEI\\\",\\\"language\\\":\\\"\\\"," +
            "\\\"sid\\\":\\\"0f484f948e7e1d829602b29677a80f2d\\\",\\\"width\\\":\\\"320\\\"," +
            "\\\"height\\\":\\\"480\\\",\\\"package_name\\\":\\\"\\\",\\\"net\\\":\\\"0\\\"," +
            "\\\"request_time\\\":\\\"1589185175547\\\",\\\"ip\\\":\\\"101.29.75.209\\\"," +
            "\\\"oaid\\\":\\\"ffe7fba7-9bff-2862-ddfe-deffdbbd72f7\\\",\\\"idfa\\\":\\\"\\\"}}\"," +
            "\"type\":\"socketbeat\",\"beat\":{\"name\":\"socketbeat\",\"hostname\":\"vm-qa-dsp-datasync001.zz.apus" +
            ".com\",\"version\":\"6.0.0\"},\"target\":\"bigdata.dsp.imp_clk\"}";
	static HttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) throws ParseException {
        sendkafka();

//		randomDate();

	}

	private static void sendkafka() {
		Properties props = new Properties();

		props.put("bootstrap.servers", "test-kafka.apuscn.com:9092");
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
        String uuid = UUID.randomUUID().toString();

        for (int i = 0; i < 200000; i++) {
            String.format(s, uuid.toString());
            ProducerRecord<String, String> msg = new ProducerRecord<>(topic, s);
            producer.send(msg);
            count++;
        }

//		列出topic的相关信息
//        List<PartitionInfo> partitions = producer.partitionsFor(topic);
//        for (PartitionInfo p : partitions) {
//            System.out.println(p);
//        }

		System.out.println("send message over." + count);

		producer.flush();
		producer.close(5000, TimeUnit.MILLISECONDS);
	}


	private static void randomDate() throws ParseException {
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
		int count = 0;
		Producer<String, String> producer = new KafkaProducer<>(props);

		Random minuteRandom = new Random();
		Random dateRandom = new Random();

		for (int i = 0; i < 100; i++) {
			int minute = minuteRandom.nextInt(60);
			int date = dateRandom.nextInt(5) + 1;

			String dateStr ;
			if (date < 10) {
				dateStr = "0" + date;
			} else {
				dateStr = date +"";
			}

			int hour = 0;
			String hourStr;
			if (hour < 10) {
				hourStr = "0" + hour;
			} else {
				hourStr = String.valueOf(hour);
			}
			String value;

			if (minute < 10) {
				value = "2018-11-" + dateStr + " " + hourStr + ":0" + minute + ":12 " + String.format(s);
			} else {
				value = "2018-11-" + dateStr + " " + hourStr + ":" + minute + ":12 " + String.format(s);
			}
			System.out.println(value);
			ProducerRecord<String, String> msg = new ProducerRecord<>(topic, value);
			producer.send(msg);
			count++;
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
