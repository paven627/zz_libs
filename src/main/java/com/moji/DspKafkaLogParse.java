package com.moji;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.log4j.Logger;
import test.kafka.KafkaProducerTest;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 解析dsp结算 kafka的发送 cost 和 citystat 日志
 */
public class DspKafkaLogParse {
    static String fileName = "824";
//    static String costFileName = "970cost";


    //    "2018-10-18 18:29:57"
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static String topic = "citystat";

    public static void main(String[] args) throws IOException, ParseException {
//        initKafka(topic);

//        parseCityStat("C:\\Users\\bin.deng\\Desktop\\dsp\\" + fileName, 19, 19, 9454, 32);

//        parseCost("C:\\Users\\bin.deng\\Desktop\\dsp\\" + costFileName, 20,21, 27600,1);
    }

    // 解析发送的统计日志
    private static void parseCityStat(String file, int startHour, int endHour, double totalconsum, int beishu) throws
            IOException {
//        2018-10-18 22:20:14,601 [INFO] cityStat:{"uuid":"976fbbb8-ff0c-4562-b608-b129637cd9f7",
// "adId":"400000000681","consumption":0.0,"impressions":1,"clicks":0,"cityId":5125,"positionId":306,"saleType":2,
// "statTime":"Oct 18, 2018 9:00:00 PM","date":"2018-10-18","hour":"21",
// "tableName":"dsp_ad_position_advertisement_city_stat_201810"} - ClearingMessageService.java(37) - [main]
//        String topic = "citystat";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\dsp\\" + fileName + "out.log")));
        int i = 0;
        String line = null;
        int count = 0;
        BigDecimal consum = new BigDecimal("0.0");
        while ((line = reader.readLine()) != null) {
            if (line.length() < 1) {
                continue;
            }
            i++;
            // json 开始位置
            int startPos = line.indexOf("{");
            int endPos = line.indexOf("}");
            String json = line.substring(startPos, endPos + 1);
            JSONObject jsonObject = JSONObject.parseObject(json);
            Date statTime = new Date((String) jsonObject.get("statTime"));
            if (jsonObject.get("materialId") == null) {
                jsonObject.put("materialId", "0");
            }
            if (statTime.getHours() >= startHour && statTime.getHours() <= endHour) {
                BigDecimal consumption = jsonObject.getBigDecimal("consumption");
                BigDecimal con = consumption.multiply(new BigDecimal(beishu));
                consum = consum.add(con);

                int impressions = jsonObject.getInteger("impressions") * beishu;
                int clicks = jsonObject.getInteger("clicks") * beishu;

                jsonObject.put("clicks", clicks);
                jsonObject.put("impressions", impressions);
                jsonObject.put("consumption", con);

                count++;
                System.out.println(jsonObject.toJSONString());
                writer.write(jsonObject.toJSONString());
                writer.newLine();
                if (consum.doubleValue() >= totalconsum) {
                    break;
                }
            }
        }
        System.out.println("文件:" + fileName + "out.log" + ",总消耗:" + consum + ",行数:" + i + ",发送数量:" + count);

        System.out.println("send message over.");
        reader.close();
        writer.close();
    }

    // 解析发送的消耗日志
//    private static void parseCost(String file, int startHour, int endHour, double totalconsum, int beishu) throws
//            IOException, ParseException {
////        2018-10-18 18:40:15,512 [INFO] cost:{"uuid":"33c45b80-af94-49f7-9f8a-b1e5c8f058bf",
// "advertId":"400000000970","price":120.0,"type":2,"clicks":1,"impressions":0,"time":"2018-10-18 18:28:55"} -
// ClearingMessageService.java(28) - [pool-1-thread-3]
//
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        BufferedWriter writer = new BufferedWriter(
//                new FileWriter(new File("C:\\Users\\bin.deng\\Desktop\\dsp\\" + costFileName + "out.log")));
//        int i = 0;
//        String line = null;
//        int count = 0;
//        BigDecimal consum = new BigDecimal("0.0");
//        while ((line = reader.readLine()) != null) {
//            if (line.length() < 1) {
//                continue;
//            }
//            i++;
//            // json 开始位置
//            int startPos = line.indexOf("{");
//            int endPos = line.indexOf("}");
//            String json = line.substring(startPos, endPos + 1);
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            Date statTime = sdf.parse((String) jsonObject.get("time"));
//            if (statTime.getHours() >= startHour && statTime.getHours() <= endHour) {
//                BigDecimal consumption = jsonObject.getBigDecimal("price");
//                BigDecimal con = consumption.multiply(new BigDecimal(beishu));
//                consum = consum.add(con);
//
//                count++;
//                System.out.println(jsonObject.toJSONString());
//                writer.write(jsonObject.toJSONString());
//                writer.newLine();
//                if (consum.doubleValue() >= totalconsum) {
//                    break;
//                }
//            }
//        }
//        System.out.println("总消耗:" + consum + ",行数:" + i + ",发送数量:" + count);
//
//        System.out.println("send message over.");
//        reader.close();
//        writer.close();
//    }


    static Producer<String, String> producer;

//    private static void sendKafka(String topic, String value) {
//        ProducerRecord<String, String> msg = new ProducerRecord<>(topic, value);
//        producer.send(msg);
//    }

    private static void initKafka(String topic) {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "KFK1:9090,KFK1:9091,KFK2:9092,KFK2:9093,KFK3:9094,KFK3:9095");
        props.put("bootstrap.servers", "KFK2:9092");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 10);
        props.put("linger.ms", 1000);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        producer = new KafkaProducer<>(props);

        //列出topic的相关信息
        List<PartitionInfo> partitions = producer.partitionsFor(topic);
        System.out.println(partitions);
//        for (PartitionInfo p : partitions) {
//            System.out.println(p);
//        }

    }

}
