import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;

public class AES {
    private static String sKey = "yrhd6bcouir0t5uk";

    // 加密
    public static String Encrypt(String sSrc) throws Exception {
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

//        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return Base64.encodeBase64URLSafeString(encrypted);
    }

    // 解密
    public static String Decrypt(String sSrc) throws Exception {
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, "utf-8");
    }

//    public static void main(String[] args) throws Exception {
//
//        // 解密
//        //price=lySZA_o2SohSRSask_ptRQ&secondprice=U8_J3-FJ9SOY_q2HOvSiu    0, 0
//
//        //price=9qUSlvKQH9nzkJEQaClcDw&secondprice=U8_J3-FJ9SOY_q2HOvSiuA
//        //Le9PRS28Cs8sIlzG_ra0wQ&secondprice=FFr8_Rtr_aD9o_Gfq0KQ9_n-j1atO5rGYtUwKSvCzaw
//        String price = "af3VxASih0yEbhGHDRYG2A";
//        String secondprice= "U8_J3-FJ9SOY_q2HOvSiuA";
//        String settlePrice = "dSbbvKMLg3UpZOL_Cscyiw";
//        settlePrice = URLDecoder.decode(settlePrice, "UTF-8");
//
//        String price1 = AES.Decrypt(price);
//        System.out.println("price:" + price1);
//        String price2 = AES.Decrypt(secondprice);
//        System.out.println("secondprice:" + price2);
//        String price3 = AES.Decrypt(settlePrice);
//        System.out.println("settlePrice:" + price3);
//
//        String encrypt = Encrypt(price1);
//        System.out.println(encrypt);
//
//        BigDecimal p = findPrice(new BigDecimal(price1), new BigDecimal(price2), "true", new BigDecimal(price3)
//                , "1");
//        System.out.println(p);
//    }

    public static void main(String[] args) throws Exception {
        args = new String[]{"Vixz%2BOE0iKzd6oynEPSoEA", "0A8xG3sLNAJ13eXHbpnMmQ","kMbSsnj_a-AnWBuGo5yEiw"};
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                String price1 = AES.Decrypt(args[i]);
                System.out.println(args[i] +":   " + price1);
            }
        }
    }


    private static BigDecimal centOfYuan = BigDecimal.valueOf(1);

    private static BigDecimal findPrice(BigDecimal price, BigDecimal secondpriceDecimal, String isDeal,
                                 BigDecimal adxPrice , String statType) {
        BigDecimal priceUse;
        if (price.doubleValue() == 0) {
            return price;
        }
        boolean isdeal = "true".equals(isDeal) ? true : false;

        if (isdeal) {
            priceUse = price;
            System.out.println(String.format("price:%s, secondPrice:%s, settlePrice:%s, isDeal:%s",
                    price, secondpriceDecimal, adxPrice, isdeal));
        } else {
            double secondprice = secondpriceDecimal.doubleValue();
            if (secondprice == 0) {
                if(statType.equals("1")) {
                    priceUse = adxPrice.add(centOfYuan);
                } else if (statType.equals("2")) {
                    priceUse = price;
                } else {
                    priceUse = BigDecimal.valueOf(0);
                }
            } else {
                //对比adx
                if (adxPrice.doubleValue() >= secondprice) {
                    priceUse = adxPrice.add(centOfYuan);
                } else {
                    priceUse = secondpriceDecimal.add(centOfYuan);
                }
            }
            System.out.println(String.format("price:%s, secondPrice:%s, settlePrice:%s",
                    price, secondpriceDecimal, adxPrice));
        }

        System.out.println("priceUse :" + priceUse +", statType:" + statType);
        return priceUse;
    }
}