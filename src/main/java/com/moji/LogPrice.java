package com.moji;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LogPrice {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\bin.deng\\Desktop\\tmp20180401.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;

        Map<String, Double> adPrice = new HashMap<>(50);
        Map<String, Integer> adImp = new HashMap<>(50);
        Map<String, Integer> adClick = new HashMap<>(50);


        int i = 0;

        try {
            while ((line = reader.readLine()) != null) {
                i++;
                String[] split = line.split("###");
                String adid = split[1];
                String type = split[5];
                Integer price = Integer.parseInt(split[7]);
                if (type.equals("2")) {
                    int clickCount = adClick.get(adid) == null ? 0 : adClick.get(adid);
                    adClick.put(adid, ++clickCount);
                    continue;
                }

                int impCount = adImp.get(adid) == null ? 0 : adImp.get(adid);
                adImp.put(adid, ++impCount);

                BigDecimal onePrice = new BigDecimal(price).divide(new BigDecimal(1000));
                Double totlePrice = adPrice.get(adid);
                if (totlePrice == null) {
                    adPrice.put(adid, onePrice.doubleValue());

                } else {
                    adPrice.put(adid, totlePrice += onePrice.doubleValue());
                }
            }

            for (Iterator<Map.Entry<String, Double>> iterator = adPrice.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Double> next = iterator.next();
                System.out.println(next.getKey() + "-" + next.getValue() + "-" + adImp.get(next.getKey()) + "-" + adClick.get(next.getKey()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println(i);
            e.printStackTrace();
        }
        reader.close();
    }

}
