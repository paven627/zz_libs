package com.moji;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解析dsp结算 kafka的发送 cost 和 citystat 日志, 统计各自的钱数
 */
public class DspKafkaLogPrice {
    static Logger logger = Logger.getLogger(DspKafkaLogPrice.class);

    static String fileName = "C:\\Users\\bin.deng\\Desktop\\400000002269\\400000002269";
    //    static String fileName = "C:\\Users\\bin.deng\\Desktop\\400000000981citystat\\kafka_1026.log";

//    static String fileName = "C:\\Users\\bin.deng\\Desktop\\dsp_stat_gap\\kafka.log";
    static String adId = "400000002269";
    /**
     * 目标日期, 日解析该日期的数据
     */
    static String targetDate = "2019-04-11";
    static SimpleDateFormat costTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static Set<String> tableNames = new HashSet<>();


    public static void main(String[] args) {

		try {
			parseCityStat(fileName, 0, 23);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	//日志数据
	static Map<Integer, Integer> logHourImps = new HashMap<>();
	static Map<Integer, Integer> logHourClicks = new HashMap<>();
	static BigDecimal logCost = new BigDecimal("0.0");

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

		Map<Integer, Integer> costHourImps = new HashMap<>();
		Map<Integer, Integer> costHourClicks = new HashMap<>();
		Map<Integer, Integer> statHourImps = new HashMap<>();
		Map<Integer, Integer> statHourClicks = new HashMap<>();

        Map<Integer, Integer> statClicksMinute = new HashMap<>();
        Map<Integer, Integer> costClicksMinute = new HashMap<>();




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
            } else if (line.indexOf("time='"+targetDate) > 0 && line.indexOf("statType=1") > 0 && line.indexOf
                    (adId) > 0) {
                logImps++;
            } else if (line.indexOf("time='"+targetDate) > 0 && line.indexOf("statType=2") > 0 && line.indexOf
                    (adId) > 0) {
                logClicks++;
            }
			// json 开始位置
			int startPos = line.indexOf("{");
			int endPos = line.indexOf("}");
			line = line.replace("=", ":");
			String json = line.substring(startPos, endPos + 1);

			if (!isCost && !isStat) {
				messageLog(json);

				continue;
			}
			JSONObject jsonObject = null;
			if (isCost) {
				jsonObject = convertCost(json);
			}
			if (isStat) {
				jsonObject = convertCityStat(json);
			}


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
                if (clicks > 0) {
//                    logger.info("stat:" + json);
                    parsetTime(line);
                }

                // 分小时:
                Integer hourImps = statHourImps.get(hour) == null ? 0 : statHourImps.get(hour);
                Integer hourClicks = statHourClicks.get(hour) == null ? 0 : statHourClicks.get(hour);
                statHourImps.put(hour, hourImps += impressions);
                statHourClicks.put(hour, hourClicks += clicks);

                // 总数
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
                int hour = costDate.getHours();
                if (hour < startHour || hour > endHour) {
                    continue;
                }

                BigDecimal consumption = jsonObject.getBigDecimal("price");
                int impressions = jsonObject.getInteger("impressions");
                int clicks = jsonObject.getInteger("clicks");
                if (clicks > 0) {
//                    logger.info("cost:" + json);
                }

                //分小时数
                Integer imp = costHourImps.get(hour) == null ? 0 : costHourImps.get(hour);
                costHourImps.put(hour, imp += impressions);
                int hourClick = costHourClicks.get(hour) == null ? 0 : costHourClicks.get(hour);
                costHourClicks.put(hour, hourClick += clicks);


                // 总数
                costConsum = costConsum.add(consumption);
                costClicks += clicks;
                costImps += impressions;
                costCount++;
            }
        }
		System.out.println("原始日志总数,曝光数:" + logImps + ", 点击数:" + logClicks+",钱数:"+ logCost);

		System.out.println("kafka发送出的数据数量:");
		System.out.println("costCount:" + costCount + ",statCount:" + statCount);

		System.out.println("Cost");
		System.out.println("曝光:" + costImps + ",点击:" + costClicks + ",钱数:" + costConsum);
		System.out.println("dspstat:");
		System.out.println("曝光:" + statImps + ",点击:" + statClicks + ",钱数:" + statConsum);
		System.out.println("\n分小时数:");
		System.out.println("原始日志:曝光" + logHourImps +"\n点击"+ logHourClicks);
		System.out.println("\n消耗曝光:" + costHourImps + "\n" +
                "消耗点击:" + costHourClicks + "\n报表曝光:" + statHourImps +
                "\n报表点击:" + statHourClicks);
		System.out.println(tableNames);
        AtomicInteger dump = new AtomicInteger();
        timeMap.forEach((k, v) -> {
            if (v > 0) {
                dump.addAndGet(v);
//                System.out.println(k + ",:" + v);
            }

        });
        System.out.println(dump);
        reader.close();
    }

	private static void messageLog(String json) throws ParseException {
		JSONObject log = convertCost(json);
		if(!log.getString("advertId").equals(adId)  ){

		}
		String time = log.getString("time");
		Date logDate = costTimeFormat.parse(time);
		int hour = logDate.getHours();
		Integer count = log.getInteger("count");
		if(log.getInteger("statType") == 1){
			Integer hourImps = logHourImps.get(hour) == null ? 0 : logHourImps.get(hour);
			logHourImps.put(hour, hourImps += count);
		} else if (log.getInteger("statType") == 2) {
			Integer hourClicks = logHourClicks.get(hour) == null ? 0 : logHourClicks.get(hour);
			logHourClicks.put(hour, hourClicks += count);
		}
		logCost= logCost.add(log.getBigDecimal("price"));
	}

	private static JSONObject convertCost(String json) {
    	return  JSONObject.parseObject(json);
	}

	private static JSONObject convertCityStat(String json) {
		String[] split = json.split(",");
		StringBuilder sb = new StringBuilder(50);
		for (int j = 0; j < split.length; j++) {
			if (j == 8) {
				continue;
			}
			if (j > 0) {
				sb.append(",");
			}
			sb.append(split[j]);
		}
		json = sb.toString();

		try {
			return JSONObject.parseObject(json);
		} catch (Exception e) {
			System.out.println("error:" + json);
			throw e;
		}
	}


	static Map<String, Integer> timeMap = new HashMap<>();

    private static void parsetTime(String line) {
        int timeIndex = line.indexOf("time") + 6;
        String time = line.substring(timeIndex, timeIndex + 19);
        int count = timeMap.get(time) == null ? 0 : timeMap.get(time);
        count++;
        timeMap.put(time, count);
    }


}
