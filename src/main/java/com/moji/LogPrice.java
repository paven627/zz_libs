package com.moji;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class LogPrice {

    static Map<String, Integer> adImp = new HashMap<>(50);
    static Map<String, Integer> adClick = new HashMap<>(50);


    public static void main(String[] args) throws IOException {
        generateInsertSql();        //后台数据
//        System.out.println(adImp);
//        System.out.println(adClick);
        generateStatsSql();
    }

    static DecimalFormat df = new DecimalFormat("0.0000");

    // 生成插入统计数据sql
    private static void generateStatsSql() throws IOException {

        File file = new File("C:\\Users\\bin.deng\\Desktop\\4.2完整数据");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int i = 0;
        String line = null;
        String sql;
        while ((line = reader.readLine()) != null) {
            String[] split = line.replaceAll("\\s", "").split("\\|");
            if (split.length != 14 || !split[2].equals("2018-04-02")) {
//                System.out.println(i);
                continue;
            }
            String adid = split[9];
            Integer click = adClick.get(adid);
            Integer imp = adImp.get(adid);
            if (imp == null) {
                System.out.println(adid);
                break;
            }
            if (click == null) {
                click = 0;
            }
            double r = click / (double) imp;
            String format = df.format(r);
//            System.out.println("adid:" + adid + ", " + click + "," + imp + "," + format);
            sql = String.format("insert into ad_adverting_data (datestr, platform, position_id, ad_type , ad_property_type ,ad_purpose , " +
                            "advertiser_id, ad_id ,  ad_show ,ad_click ,ad_click_rate , create_time) " +
                            "values ('2018-04-01' ,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s, '2018-04-02 03:00:07')",
                    split[3], split[4], split[5], split[6], split[7], split[8], split[9], split[10], split[11], format);
            System.out.println(sql);
        }
        reader.close();
    }

    // 生成插入后台数据的sql
    private static void generateInsertSql() throws IOException {
        File file = new File("C:\\Users\\bin.deng\\Desktop\\datasort1.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        Set<String> idSet = new HashSet<>();


        Map<String, String> platformMap = new HashMap<>(50);
        Map<String, Integer> indexMap = new HashMap<>(50);
        Map<String, String> typeMap = new HashMap<>(50);


        int i = 0;

        while ((line = reader.readLine()) != null) {
            i++;
            String[] split = line.split("\\|");
            if (split.length != 7) {
                System.out.println(i);
//                    continue;
                return;
            }
            String date = split[1];
            if (!date.equals("20180401")) {
                continue;
            }
            String adid = split[2];
            String platform = split[3];
            String type = split[4];
            Integer adIndex = Integer.parseInt(split[5]);
            Integer statValue = Integer.parseInt(split[6]);

            idSet.add(adid);
            platformMap.put(adid, platform);
            indexMap.put(adid, adIndex);
            typeMap.put(adid, type);
            impAndClick(adid, type, statValue);
        }
        reader.close();

        for (String adid : idSet) {
//                next.getKey() + "-" + next.getValue() + "-" + adImp.get(next.getKey()) + "-" + adClick.get(next.getKey())


            String imp = String.format("insert into ad_v_real_time_stat_201804  " +
                            "(`timeSign`,`datestr`,`hourstr`,`minutestr`,`platform`,`ad_index`,`stat_type`,`stat_value`,`ad_id`, `ad_index_part`, `price`)" +
                            " values ('2018-04-01 23:59:00', '2018-04-01','23', '0','%s','%s', '1', %s, '%s', -1,0 );",
                    platformMap.get(adid), indexMap.get(adid), adImp.get(adid) == null ? 0 : adImp.get(adid), adid);

            String click = String.format("insert into ad_v_real_time_stat_201804  " +
                            "(`timeSign`,`datestr`,`hourstr`,`minutestr`,`platform`,`ad_index`,`stat_type`,`stat_value`,`ad_id`, `ad_index_part`, `price`) " +
                            " values ('2018-04-01 23:59:00', '2018-04-01','23', '0','%s','%s', '2' , %s, '%s', -1, 0 );",
                    platformMap.get(adid), indexMap.get(adid), adClick.get(adid) == null ? 0 : adClick.get(adid), adid);


//            System.out.println(imp);
//            System.out.println(click);
        }
    }

    private static void impAndClick(String adid, String type, Integer statValue) {
        if (type.equals("2")) {
            int clickCount = adClick.get(adid) == null ? 0 : adClick.get(adid);
            adClick.put(adid, clickCount += statValue);
            return;
        }
        if (type.equals("1")) {
            int count = adImp.get(adid) == null ? 0 : adImp.get(adid);
            adImp.put(adid, count += statValue);
            return;
        }
    }

}
