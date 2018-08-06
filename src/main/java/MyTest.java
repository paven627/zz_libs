import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moji.launchserver.entity.AdStatEntity;
import com.moji.launchserver.entity.ThirdResponseData;
import com.moji.launchserver.thirdapi.util.ThirdAPIUtil;
import com.moji.launchserver.util.MD5;
import com.moji.launchserver.util.ShortUrl;
import joptsimple.internal.Strings;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.BigDecimal;
import java.util.*;

public class MyTest {
    static Date date = getDate();

    private static Date getDate() {
        System.out.println("1111");
        return new Date();
    }

    public static void main(String[] args) throws Exception {
//        sTest();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String md5Str = MD5.getMD5Str("1021" + "2018moji.com" + 1);
        }
        System.out.println(System.currentTimeMillis() - l);
    }


    private static void sTest() {
        long l = System.currentTimeMillis();
        int num = 10000000;
        for (int i = 0; i < num; i++) {
            String s = "key_advertisement_consumption" + "_" + "2018";
            if (i == 1) {
                System.out.println(s);
            }
        }
        long l1 = System.currentTimeMillis() - l;
        System.out.println(l1);


        long l2 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            StringBuilder key_advertisement_consumption = new StringBuilder("key_advertisement_consumption");
            key_advertisement_consumption.append("_").append("2018");
            if (i == 1) {
                System.out.println(key_advertisement_consumption.toString());
            }
        }
        System.out.println(System.currentTimeMillis() - l2);
    }


}