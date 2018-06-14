import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest  {
    static Map<String, Object> a = new HashMap();

    static int timeperiod = 10;
    private Timer timer = new Timer();

    private static ExecutorService service = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws ParseException {
        System.out.println(11);

    }

    @Test
    public void test1() {
        System.out.println("1234");
    }

}