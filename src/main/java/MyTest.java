import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class MyTest {
    static Logger logger = Logger.getLogger(MyTest.class);


    public static void main(String[] args) {
        int i = 1;
        System.out.println(1 + (i++));

        int i1 = 1;
        System.out.println(1 + (++i1));



//        int i1 = ++i;
//        System.out.println(i1);
//        System.out.println(1 + i1);

//        JedisPoolConfig config = new JedisPoolConfig();
////        config.setMaxWaitMillis(10000);
//        JedisPool pool = new JedisPool(config, "10.11.8.41");
//        long maxBorrowWaitTimeMillis = pool.getMaxBorrowWaitTimeMillis();
//        System.out.println(maxBorrowWaitTimeMillis);
//        Jedis resource = null;
//        try {
//            logger.info(System.currentTimeMillis());
//            resource = pool.getResource();
//        } catch (Exception e) {
//            logger.info(System.currentTimeMillis());
//        }
//        logger.info(resource);
//        resource.close();
    }

    private TreeSet<Integer> getSetForOrderByPrice(List<Integer> list) {
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                if(StringUtils.isNotBlank(args.getDealId())) {
//                    // deal id匹配
//                    if (args.getDealId().equalsIgnoreCase(o1.getDealid())) {
//                        return -1;
//                    }
//                }

//                double b1 = getBidPrice(o1,args);
//                double b2 = getBidPrice(o2,args);
//                if (b2 > b1) {
//                    return 1;
//                } else if (b2 < b1) {
//                    return -1;
//                }
                return 1;
            }
        });
        return set;
    }

    private static boolean positionApiFlowControl(String posPercentStr) {
        if (posPercentStr == null) {
            return commonApiFlowControl();
        }
        int posPercent = Integer.parseInt(posPercentStr);
        if (posPercent >= 100) {
            return true;
        }
        if (posPercent == 0) {
            return false;
        }
        return ThreadLocalRandom.current().nextInt(100) < posPercent;
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