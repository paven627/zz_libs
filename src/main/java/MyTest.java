import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest {
    static Map<String, Object> a = new HashMap();

    static int timeperiod = 10;

    public static void main(String[] args) throws ParseException {

        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {


            service.submit(new TT());
        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = sdf.parse("2018-06-05 16:26:37");
//        int date = parse.getHours();
//        long time = parse.getMinutes();
//        System.out.println(date);
//        System.out.println(time );
//
//        System.out.println("2018-06-05 16:26:37".substring(0,10));
//
//        System.out.println(Integer.valueOf("05"));

//        for (int i = 0; i < 59; i++) {
//            System.out.println((i / timeperiod ) * timeperiod);
//        }

        Logger.getLogger(MyTest.class).info(123);

    }

}

class TT implements  Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}