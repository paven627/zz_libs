import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Ad implements Comparator<Ad> {
    private Integer price;
    private String name;

    public Ad(Integer price, String name) {
        this.price = price;
        this.name = name;
    }
    public Ad() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

class Prize {
    private int id;//奖品id
    private String prize_name;//奖品名称
    private int prize_amount;//奖品（剩余）数量
    private int prize_weight;//奖品权重

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prize prize = (Prize) o;
        return id == prize.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public int getPrize_amount() {
        return prize_amount;
    }

    public void setPrize_amount(int prize_amount) {
        this.prize_amount = prize_amount;
    }

    public int getPrize_weight() {
        return prize_weight;
    }

    public void setPrize_weight(int prize_weight) {
        this.prize_weight = prize_weight;
    }
}
class Father {
    public void dosomething(HashMap hashmap){
        System.out.println("father");
    }
}
class Son extends  Father {
    public void dosomething(Map map) {
        System.out.println("son");
    }
}

public class MyTest {
    static Logger logger = Logger.getLogger(MyTest.class);
    public static final BigDecimal MINUTEOFHOUR = new BigDecimal(60);

    private static KafkaConsumer<String, String> consumer;
    private static String topic = "apus_dsp";

    public int getPrizeIndex(List<Prize> prizes) {
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (Prize p : prizes) {
                sumWeight += p.getPrize_weight();
            }

            //产生随机数
            double randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < prizes.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getPrize_weight())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1).getPrize_weight())) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        BitSet b = new BitSet(1);
        System.out.println(b.length());
        b.set(1000);
        System.out.println(b.length());

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