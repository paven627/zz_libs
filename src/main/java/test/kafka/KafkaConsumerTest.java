package test.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KafkaConsumerTest {

    private static KafkaConsumer<String, String> consumer;

    private static Properties props = new Properties();

    /**
     * @param args
     */
    public static void main(String[] args) {
//        KafkaConsumerTest simpleConsumer = new KafkaConsumerTest();
//        simpleConsumer.execMsgConsume();

        kafkaConsume();
    }


    public static void kafkaConsume() {
        String topic = "topic.cloud.service.bigdata.dsp.imp_clk";

        props.put("bootstrap.servers", "dev-kafka.apuscn.com:9092");
        props.put("group.id", "datasync2");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());


        consumer = new KafkaConsumer<String, String>(props);
//        consumer.subscribe(Arrays.asList(topic));

//        列出topic的相关信息
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);

        List<TopicPartition> topicPartions = partitionInfos.stream().map(new Function<PartitionInfo,
                        TopicPartition>() {
            @Override
            public TopicPartition apply(PartitionInfo partitionInfo) {
                return new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
            }
        }).collect(Collectors.toList());
        System.out.println(partitionInfos);

        consumer.assign(topicPartions);

        for (TopicPartition p : topicPartions) {
            System.out.println(p);
            long position = consumer.position(p);
            System.out.println(position);
        }



    }
	private void execMsgConsume() {
        Properties props = new Properties();
            props.put("zookeeper.connect", "dev-kafka.apuscn.com:9092");
        props.put("group.id", "group-1");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
         
        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConnector consumer =  Consumer.createJavaConsumerConnector(config);


        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("test_dsp_rquest_log_topic", 1);
        topicCountMap.put("test", 1);
        Decoder<String> keyDecoder = new StringDecoder(new VerifiableProperties());
        Decoder<String> valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> createMessageStreams = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        for (Iterator<String> it = createMessageStreams.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            System.out.println("The key of the createMessageStreams is " + key);
            List<KafkaStream<String, String>> values = createMessageStreams.get(key);
            for (KafkaStream<String, String> value : values) {
                 ConsumerIterator<String, String> consumerIt = value.iterator();
                 while (consumerIt.hasNext()) {
                     MessageAndMetadata<String, String> data = consumerIt.next();
                     System.out.println("The message got by consuer is " + data.message());
                 }
            }
        }
         
    }
     

}
