package com.moji;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.TextFormat;
import com.googlecode.protobuf.format.JsonFormat;
import com.moji.launchserver.AdCommonInterface.AdPosition;
import com.moji.launchserver.AdCommonInterface.AdRequest;
import com.moji.launchserver.AdCommonInterface.AdRequestCommon;
import com.moji.launchserver.AdCommonInterface.AdResponse;
import com.moji.launchserver.AdCommonInterface.AdType;
import com.moji.launchserver.AdCommonInterface.CarrierType;
import com.moji.launchserver.AdCommonInterface.DayOrNight;
import com.moji.launchserver.AdCommonInterface.IsShortPrediction;
import com.moji.launchserver.AdCommonInterface.Language;
import com.moji.launchserver.AdCommonInterface.Platform;
import com.moji.launchserver.netty.handler.protobuf.NewTextFormat;

public class SocketTest {
    
    static BufferedReader in = null;
    
    public static void main(String[] args)
        throws UnknownHostException, IOException {
        for (int i = 0; i < 1; i++) {
            // new Thread(){
            // public void run(){
            // try {
            callServer();
            
            
            try{
                
            }catch(Exception ex){
               ex.printStackTrace();
            }
            // }
            // catch (IOException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // }
            // }.start();
            
        }
        
        // System.in.read();
    }
    
    public static void callServer()
        throws IOException {
//    	        Socket socket = new Socket("103.17.43.217", 8899);
 //      Socket socket = new Socket("192.168.1.71", 9091);
//    	        Socket socket = new Socket("103.235.239.69", 9500);
    	Socket socket = new Socket("192.168.1.67", 8080);


//        socket.setSoTimeout(1000);
        // socket.sets
        
        // partenrctl.properties positiondetail.properties third_api.json versionctl.properties
        
        // Socket socket = new Socket("localhost", 8899);
        
        
        for (int i = 0; i < 1; i++) {
            AdResponse.Builder response = AdResponse.newBuilder();
            AdRequest.Builder build = AdRequest.newBuilder();
          build.setType(AdType.OTHERS_TYPE);
//            build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
//          build.setType(AdType.BGAVATAR);
//           build.setType(AdType.SPLASH);

            build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
//             build.setType(AdType.DISPLAY_WINDOW);
            build.setDayOrNight(DayOrNight.NIGHT);
            build.setAlreadyShowId(0l);
            build.setVersion(1);
            build.addLastAdIds(0l);
            build.setSessionId("a1000003001");
            build.setLatitude(39.972675f);
            build.setLongitude(116.490528f);
            build.setIsShortPrediction(IsShortPrediction.YES);
            List<AdPosition> adPositions = new ArrayList<>();
 //                      adPositions.add(AdPosition.POS_FEED_STREAM_DETAILS);
//             adPositions.add(AdPosition.POS_DISCOUNT_ENTRY);
//            adPositions.add(AdPosition.POS_WEATHER_BACKGROUND);
//             adPositions.add(AdPosition.POS_DRESS_ASSISTANT);
            // adPositions.add(AdPosition.POS_FEED_STREAM_CARD_ENTRY);
//             adPositions.add(AdPosition.POS_WEATHER_FRONT_PAGE_MIDDLE);
//             adPositions.add(AdPosition.POS_DRESSING_INDEX);
            
//                adPositions.add(AdPosition.POS_TIME_SCENE_TOP_TWO);
//             adPositions.add(AdPosition.POS_MY_PAGE_DYNAMIC_MENU);
//             adPositions.add(AdPosition.POS_WEATHER_HOME_INDEX_ENTRY);
//              
              //feed流
//            adPositions.add(AdPosition.POS_FEED_STREAM_CARD_ENTRY);
//            
            
//            adPositions.add(AdPosition.POS_FEED_STREAM_INFORMATION);
//           adPositions.add(AdPosition.POS_WEATHER_HOME_INDEX_ENTRY);
//             scp -P20443 launch-0.0.1.jar   jintao@192.168.1.76:/home/jintao
            
            
            
             adPositions.add(AdPosition.POS_VOICE_BROADCAST_UNDER);
            //adPositions.add(AdPosition.POS_TIME_SCENE_BANNER_BOTTOM);
            build.addAllPosition(adPositions);

            
            AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();
            adc.setAndroidId("android");
            adc.setUa("Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
            adc.setPkgname("com.moji.MojiWeather");
            adc.setUid(654366087);
            adc.setCityId(600);
            adc.setPublishType("2");
//            adc.setAppVersion(1006010105);
//            5.5.3.2
            adc.setAppVersion(1006030000);
            adc.setScreenHeight(1136);
            adc.setScreenWidth(640);
            adc.setLang(Language.SIMPLIFIED_CH);
            
            adc.setChannelId(9000);
            adc.setOsType(Platform.ANDROID);
            adc.setOsVersion("6.0.1");

            adc.setPhoneType("IPHONE 6S PLUS");
            adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
            adc.setWma("wma");
            adc.setAvatarId(2);
            adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");

            adc.setIsWifi(true);
            adc.setMcc(21);
            adc.setMnc(343);
            adc.setCarrier(CarrierType.CARRIER_MOBILE);

            build.setRequestCommon(adc);
            build.setDisplayTimes(1);
            build.setVersion(1);
            long l = System.currentTimeMillis();
            AdRequest request = build.build();
//            System.out.println(request.toString());
//            Map<FieldDescriptor, Object> map =request.getAllFields();
//            Iterator<FieldDescriptor> it = map.keySet().iterator();
//            while(it.hasNext()){
//                FieldDescriptor f = it.next();
//                String s = f.getName();
//                System.out.println(s);
//            }
//           String str =  NewTextFormat.shortDebugString(request);
//            System.out.println(str);
//            System.out.println(request.toString());
            byte type = 0;
            int length = request.toByteArray().length;
            byte[] endMark = {-128, -128, -128, -128, -128};
            byte[] req = byteMerger(type, request.toByteArray(), endMark);
            int bodyLen = req.length;
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(req, 0, bodyLen);


            AdResponse.newBuilder();
            while (true) {
                InputStream input = socket.getInputStream();
                if (input != null) {
                    byte[] by = toByteArray(input);
                    for (byte b : by) {
//                        System.out.print(b);
                    }
                    AdResponse adResponse = AdResponse.parseFrom(by);
                    System.out.println(adResponse.toString());
                    long e = System.currentTimeMillis();
//                     String s = new String(adResponse.getAdUtilDetail(0).getAdUtilDescription().getTitle());
//                     System.out.println(s);
                    // System.out.println("" + adResponse.getAdUtilDetail(0).getAdUtilDescription().getPositionName());
//                    System.out.println(adResponse.getAsAvatarDetail().getAdAvatarDescription().getSentences(0).getContent());
//                    System.out.println(adResponse.getAdSplashDetail().getAdSplashDescription(0).getImageInfo().getImageUrl());

                    System.out.println(adResponse.getErrorMessage());
//                    System.out.println(s);
//                     out.close();
//                     input.close();
                }
            }
        } 
    }
    
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        
        for (int i = 0; i < 4; i++) {
            b[i] = (byte)(n >> (24 - i * 8));
        }
        return b;
    }
    
    public static byte[] byteMerger(byte type, byte[] byte1, byte[] byte2) {
        byte[] byte3 = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
        byte[] byte4 = new byte[byte3.length + 1];
        byte4[0] = type;
        for (int i = 1; i < byte4.length; i++) {
            byte4[i] = byte3[i - 1];
        }
        return byte4;
    }
    
    public static byte[] toByteArray(InputStream input)
        throws IOException {
        byte[] buffer1 = new byte[5];
        input.read(buffer1, 0, 5);
        int length = byteArrayToInt(buffer1);
        byte[] buffer = new byte[length];
//        System.out.println("buffer.length before->" + buffer.length);
//        System.out.println("input available->" + input.available());
        // n = input.read(buffer);
        int readbytes = 0;
        while (readbytes < length) {
            int read = input.read(buffer, readbytes, length - readbytes);
            if (read == -1) {
                break;
            }
            readbytes += read;
        }
        return buffer;
        // System.out.println("buffer.length after->"+n);
        // output.write(buffer, 0, n);
        // return output.toByteArray();
    }
    
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 1; i < 5; i++) {
            int shift = (5 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }
}
