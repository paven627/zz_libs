package com.moji;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat.Encoding;

import com.google.protobuf.InvalidProtocolBufferException;
import com.moji.launchserver.AdCommonInterface;
import com.moji.launchserver.AdCommonInterface.Action;
import com.moji.launchserver.AdCommonInterface.ActionType;
import com.moji.launchserver.AdCommonInterface.AdPosition;
import com.moji.launchserver.AdCommonInterface.AdRequest;
import com.moji.launchserver.AdCommonInterface.AdRequestCommon;
import com.moji.launchserver.AdCommonInterface.AdRequestCommon.Builder;
import com.moji.launchserver.AdCommonInterface.AdResponse;
import com.moji.launchserver.AdCommonInterface.AdType;
import com.moji.launchserver.AdCommonInterface.CarrierType;
import com.moji.launchserver.AdCommonInterface.ContentType;
import com.moji.launchserver.AdCommonInterface.DayOrNight;
import com.moji.launchserver.AdCommonInterface.FeedClick;
import com.moji.launchserver.AdCommonInterface.IsShortPrediction;
import com.moji.launchserver.AdCommonInterface.Language;
import com.moji.launchserver.AdCommonInterface.Platform;
import com.moji.launchserver.entity.AdResponseEntity;
import com.moji.launchserver.service.WeatherRPCService;
import com.opensymphony.module.sitemesh.tapestry.Title;

public class SocketTest {

//	 static String ip = "103.235.239.69"; // 线上调试
//	 static int port = 9500;


//    static String ip = "127.0.0.1";
//	 static String ip = "192.168.1.181";
	 static String ip = "192.168.1.184";
//	 static String ip = "192.168.1.67";

    // static String ip = "adlaunch.moji.com";
//	 static int port = 80;


    static int port = 8080;
//	 static int port = 8081;
//	 static int port = 8082;

    public static void main(String[] args) throws UnknownHostException, IOException {
        // InetAddress add = InetAddress.getByName("adlaunch.moji.com");
        // ip = add.getHostAddress();

        // socket.setSoTimeout(10000);
        // socket.setTcpNoDelay(true)


//		1006010676
//		1007001000
//
//		adc.setAppVersion(1007001000);
//		adc.setAppVersion(1007010001);
//		50070603

        //广点通分界版本 1007001001

//        POS_MY_PAGE_DYNAMIC_MENU
//        POS_MY_PAGE_DYNAMIC_MENU_TWO

//        AdRequest otherOI = other(AdType.SPLASH, Platform.ANDROID, AdPosition.POS_SPLASH, 600,
//                204, "com.moji.mjweather", 1007050601);


//        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.IOS, AdPosition.POS_FEED_STREAM_DETAILS, 600,
//                204, "com.moji.MjWeather", 50070606);

//        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_FEED_STREAM_DETAILS, 600,
//                204, "com.moji.mjweather", 1007060601);

//        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_WEATHER_FRONT_PAGE_BOTTOM, 600,
//                204, "com.moji.MjWeather", 1007040601);

        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.IOS, AdPosition.POS_MY_PAGE_DYNAMIC_MENU_TWO, 600,
                204, "com.moji.mjweather", 50070704);


//        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.IOS, AdPosition
//                        .POS_MY_PAGE_DYNAMIC_MENU_RELAX, 600,
//                9000, "com.moji.MjWeather", 50070608);
////
//		50070506
//		1007040601
//		com.moji.MjWeather

        Socket socketOI = new Socket(ip, port);
        print(socketOI, otherOI);

        // 50070204
//		 feedclick();

//		com.moji.mjweather

    }

    static Socket socketOI;


    private static AdRequest other(AdType adType, Platform osType, AdPosition position, int cityId, int channelId,
                                   String packageName, int version) {
        AdRequest.Builder request = AdRequest.newBuilder();
        AdRequestCommon.Builder common = AdRequestCommon.newBuilder();

        common.setAvatarId(8);
        request.setFeedTab(5);

        request.setType(adType);
        request.setCommentNumber(14);
        // request.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
        request.setDayOrNight(DayOrNight.NIGHT);
        request.setVersion(1);

        request.setSessionId("a10000030011");
         request.setLatitude(31.284018f);
         request.setLongitude(121.44974f);
        request.setIsShortPrediction(IsShortPrediction.NO);

        List<AdPosition> adPositions = new ArrayList<>();
        adPositions.add(position);
        request.addExsitedAdIds(10014107);
//        request.addExsitedAdIds(2622);
//        request.addExsitedAdIds(2623);
//
        request.addLastAdIds(10014107);
//        request.addLastAdIds(2622);
//        request.addLastAdIds(2623);
        request.setAlreadyShowId(200010000618l);

        request.addAllPosition(adPositions);


        common.setPkgname(packageName);
        common.setUid(654366082);
        common.setCityId(cityId);
        common.setPublishType("1");
        common.setAppVersion(version);
        common.setScreenWidth(414);
        common.setScreenHeight(736);
        common.setLang(Language.SIMPLIFIED_CH);
        //
        common.setChannelId(channelId);
        common.setOsType(osType);
        //
        phoneParam(osType, common);
        // common.setWma("wma");
        common.setNetType("wifi");

        common.setIsWifi(true);
        common.setMcc(21);
        common.setMnc(343);
        common.setCarrier(CarrierType.CARRIER_MOBILE);
        //
        // // common.setIsUserAvatarShow(true);
        common.setIsAvatarShow(true);
        common.setRegisterTime(1535024127);
        request.setRequestCommon(common);
        // request.setDisplayTimes(1);
//        request.setVersion(1);
        request.setIsDebug(true);
//		request.setIsHotLaunch(true);
        AdRequest r = request.build();
        return r;
    }

    private static void phoneParam(Platform osType, Builder adc) {
        if (osType == Platform.ANDROID) {
            android(adc);
        } else {
            ios(adc);
        }
    }

    private static void ios(Builder adc) {
        adc.setOsVersion("11.4.1");
        adc.setPhoneType("iPhone8,2");
        adc.setIdentifier("3E3261FD-5894-4631-A64A-55660A259DD9");
//        adc.setIdentifier("12C1BDA2-DFD6-4124-8352-ADBEC1EACD07");
        adc.setUa(
                "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) " +
						"Mobile/13E230");
    }

    private static void android(Builder adc) {
        adc.setOsVersion("11.0");
        adc.setAndroidId("60d562786bd59675");
        // adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
        adc.setIdentifier("352425060557234");
        adc.setPhoneType("SM-N9150");
        adc.setUa(
                "Mozilla/5.0+(Linux;+Android+6.0.1;+SM-N9150+Build/MMB29M;+wv)+AppleWebKit/537.36+(KHTML,+like+Gecko)" +
						"+Version/4.0+Chrome/52.0.2743.98+Mobile+Safari/537.36mojia/1007000401");
    }

    private static void print(Socket socket, AdRequest request) throws IOException, InvalidProtocolBufferException {
        byte type = 0;
        byte[] endMark = {-128, -128, -128, -128, -128};
        byte[] req = byteMerger(type, request.toByteArray(), endMark);
        // for (byte b : req) {
        // System.out.print( b);
        // }
        System.out.println();

        int bodyLen = req.length;
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(req, 0, bodyLen);

        InputStream input = socket.getInputStream();
        if (input != null) {
            byte[] by = toByteArray(input);
//			for (byte b : by) {
//				System.out.print((int) b);
//			}
            System.out.println();
            System.out.println();
            System.out.println();
            AdResponse adResponse = AdResponse.parseFrom(by);

            List<AdCommonInterface.AdUtilDetail> adUtilDetailList = adResponse.getAdUtilDetailList();
            for (AdCommonInterface.AdUtilDetail adUtilDetail : adUtilDetailList) {
                if(adUtilDetail.hasAdUtilDescription()) {
                    AdCommonInterface.AdUtilDescription adUtilDescription = adUtilDetail.getAdUtilDescription();
                    adUtilDescription.toBuilder().setTitle(new String(adUtilDescription.getTitle())).build();
                }
            }

            System.out.println(adResponse.toString());
            System.out.println(adResponse.getErrorMessage());
            // System.out.println(s);
//			out.close();
//			input.close();
        }
    }

    private static void print2(Socket socket, AdRequest request) throws IOException, InvalidProtocolBufferException {
        byte type = 0;
        byte[] endMark = {-128, -128, -128, -128, -128};
        byte[] req = byteMerger(type, request.toByteArray(), endMark);
        // for (byte b : req) {
        // System.out.print( b);
        // }
        System.out.println();

        int bodyLen = req.length;
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(req, 0, bodyLen);

        InputStream input = socket.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        byte[] b = new byte[20];

        input.read(b);

        for (byte c : b) {
            System.out.print((char) c);
        }
//		while ((line = in.readLine()) != null) {
//			System.out.println(line);
//		}
//		out.close();
//		input.close();
//		in.close();
//		socket.close();
    }

    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];

        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
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

    public static byte[] toByteArray(InputStream input) throws IOException {
        byte[] buffer1 = new byte[5];
        input.read(buffer1, 0, 5);
        int length = byteArrayToInt(buffer1);
        byte[] buffer = new byte[length];
        // System.out.println("buffer.length before->" + buffer.length);
        // System.out.println("input available->" + input.available());
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
