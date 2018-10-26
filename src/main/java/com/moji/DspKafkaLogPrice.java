package com.moji;


import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.PartitionInfo;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 解析dsp结算 kafka的发送 cost 和 citystat 日志, 统计各自的钱数
 */
public class DspKafkaLogPrice {
    static String fileName = "C:\\Users\\bin.deng\\Desktop\\400000000981citystat\\kafka_1025.log";
    //    static String fileName = "C:\\Users\\bin.deng\\Desktop\\400000000981citystat\\kafka_1026.log";
    static String adId = "400000000987";
    /**
     * 目标日期, 日解析该日期的数据
     */
    static String targetDate = "2018-10-25";
    static SimpleDateFormat costTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static Set<String> tableNames = new HashSet<>();

    public static void main(String[] args) throws IOException, ParseException {

        parseCityStat(fileName, 0, 24);
    }

    // 解析发送的统计日志
    private static void parseCityStat(String file, int startHour, int endHour) throws
            IOException, ParseException {
//        2018-10-18 22:20:14,601 [INFO] cityStat:{"uuid":"976fbbb8-ff0c-4562-b608-b129637cd9f7",
// "adId":"400000000681","consumption":0.0,"impressions":1,"clicks":0,"cityId":5125,"positionId":306,"saleType":2,
// "statTime":"Oct 18, 2018 9:00:00 PM","date":"2018-10-18","hour":"21",
// "tableName":"dsp_ad_position_advertisement_city_stat_201810"} - ClearingMessageService.java(37) - [main]
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int i = 0;
        String line = null;
        int statCount = 0;
        BigDecimal statConsum = new BigDecimal("0.0");
        int statImps = 0;
        int statClicks = 0;

        int costCount = 0;
        BigDecimal costConsum = new BigDecimal("0.0");
        int costImps = 0;
        int costClicks = 0;
        int logImps = 0;
        int logClicks = 0;
        while ((line = reader.readLine()) != null) {
            if (line.length() < 1) {
                continue;
            }
            i++;
            boolean isCost = false;
            boolean isStat = false;
            if (line.indexOf("cost") > 0) {
                isCost = true;
            } else if (line.indexOf("cityStat") > 0) {
                isStat = true;
            } else if (line.indexOf("messageType=1") > 0 && line.indexOf(adId) > 0) {
                logImps++;
            } else if (line.indexOf("messageType=2") > 0 && line.indexOf(adId) > 0) {
                logClicks++;
            }

            if (!isCost && !isStat) {
                continue;
            }
            // json 开始位置
            int startPos = line.indexOf("{");
            int endPos = line.indexOf("}");
            String json = line.substring(startPos, endPos + 1);
            JSONObject jsonObject = JSONObject.parseObject(json);

            // 报表
            if (isStat) {
                if (!adId.equals(jsonObject.get("adId"))) {
                    continue;
                }
                String date = jsonObject.getString("date");
                int hour = jsonObject.getInteger("hour");

                if (!date.equals(targetDate) || (hour < startHour || hour > endHour)) {
                    continue;
                }
                BigDecimal consumption = jsonObject.getBigDecimal("consumption");
                statConsum = statConsum.add(consumption);
                String tableName = jsonObject.getString("tableName");
                tableNames.add(tableName);
                int impressions = jsonObject.getInteger("impressions");
                int clicks = jsonObject.getInteger("clicks");

                statClicks += clicks;
                statImps += impressions;
                statCount++;
            }
            // 消耗
            if (isCost) {
                if (!jsonObject.get("advertId").equals(adId)) {
                    continue;
                }
                String date = jsonObject.getString("time");
                String timeStr = date.substring(0, 10);
                if (!timeStr.equals(targetDate)) {
                    continue;
                }
                Date costDate = costTimeFormat.parse(date);
                if (costDate.getHours() < startHour || costDate.getHours() > endHour) {
                    continue;
                }

                BigDecimal consumption = jsonObject.getBigDecimal("price");
                int impressions = jsonObject.getInteger("impressions");
                int clicks = jsonObject.getInteger("clicks");

                costConsum = costConsum.add(consumption);
                costClicks += clicks;
                costImps += impressions;
                costCount++;
            }
        }
        System.out.println("原始日志曝光数:" + logImps + ", 点击数:" + logClicks);
        System.out.println("消耗impressions:" + costImps + ",消耗clicks:" + costClicks + ",cost:" + costConsum + "\n" +
                "报表impressions:" + statImps + ",报表clicks:" + statClicks + ",报表:" + statConsum);
        System.out.println(tableNames);

        reader.close();
    }


}
