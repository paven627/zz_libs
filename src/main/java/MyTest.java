import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

class Ad implements Comparator<Ad> {
    private int price;
    private String name;

    public Ad(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    //    @Override
//    public int compareTo(Ad o) {
//        return this.price - o.getPrice();
//    }
//    @Override
//    public int compareTo(Ad o) {
//        return o.getPrice() - this.price;
//    }

    @Override
    public int compare(Ad o1, Ad o2) {
        return o1.price - o2.price;
    }
}

public class MyTest {
    static Logger logger = Logger.getLogger(MyTest.class);
    public static final BigDecimal MINUTEOFHOUR = new BigDecimal(60);

    private static KafkaConsumer<String, String> consumer;
    private static String topic = "apus_dsp";

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            int a = ThreadLocalRandom.current().nextInt(3);
            System.out.println(a);
        }


//        Properties props = new Properties();
//        props.put("bootstrap.servers", "dev-kafka.apuscn.com:9092");
//        props.put("group.id", "datasync1");
//        props.put("enable.auto.commit", "false");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
//        props.put("auto.offset.reset", "latest");
//        props.put("key.deserializer", StringDeserializer.class.getName());
//        props.put("value.deserializer", StringDeserializer.class.getName());
//
//        consumer = new KafkaConsumer<String, String>(props);
//        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
//        System.out.println(partitionInfos);
//        consumer.assign(Arrays.asList(new TopicPartition("kafka.wshare.match_meta_data.topic", 0)));
//
//
//        List<TopicPartition> partitions = partitionInfos.stream().map(new Function<PartitionInfo,
//                TopicPartition>() {
//            @Override
//            public TopicPartition apply(PartitionInfo partitionInfo) {
//                return new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
//            }
//        }).collect(Collectors.toList());
//        System.out.println(partitions);
    }


    //  通用API流量控制 , true 请求, false 不请求
    private static boolean commonApiFlowControl() {
        int dsp_request_percent = 60;
        if (dsp_request_percent >= 100) {
            return true;
        }
        if (dsp_request_percent == 0) {
            return false;
        }
        return ThreadLocalRandom.current().nextInt(100) < dsp_request_percent;
    }

    public static void gongzi(String[] args) throws Exception {
//		int month = 1;
        int gongzi = 30000;
        float rate = 0.03f;
        float lastmonth = 0;
        for (int month = 1; month < 13; month++) {
            if (month > 1) {
                rate = 0.1f;
            }
            float dangyue = ((gongzi * month) - (5000 * month) - (5641.90f * month) - (1000 * month)) * rate -
                    lastmonth;
            if (month > 1) {
                dangyue -= 2520;
            }
            lastmonth += dangyue;
//			System.out.println("上月:" + lastmonth);
            System.out.println("月:" + month + "," + dangyue);
        }
    }

}