
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

public class MyTest {


    public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
        BigDecimal b = new BigDecimal(995).divide(new BigDecimal(1000));
        System.out.println(b);

        Map<String, Integer> adPrice = new HashMap<>(50);
    int i = 5;
        adPrice.put("a", i+=1);
        System.out.println(adPrice);
    }


}