package com.moji;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.moji.launchserver.AdCommonInterface.AdPosition;
import com.moji.launchserver.AdCommonInterface.AdRequest;
import com.moji.launchserver.AdCommonInterface.AdRequestCommon;
import com.moji.launchserver.AdCommonInterface.AdRequestCommon.Builder;
import com.moji.launchserver.AdCommonInterface.AdResponse;
import com.moji.launchserver.AdCommonInterface.AdType;
import com.moji.launchserver.AdCommonInterface.CarrierType;
import com.moji.launchserver.AdCommonInterface.DayOrNight;
import com.moji.launchserver.AdCommonInterface.IsShortPrediction;
import com.moji.launchserver.AdCommonInterface.Language;
import com.moji.launchserver.AdCommonInterface.Platform;

public class SocketTest {

	/*
	 * http://c.admaster.com.cn/c/a50231,b03753846,c3194,i0,m201,0a__OS__,
	 * 0c__IMEI__,0d__AndroidID__,0e__DUID__,n__MAC__,o__OUID__,z__IDFA__,
	 * f__IP__,t__TS__,r__TERM_ _,l__LBS__,h
	 * 
	 */
//	static String ip = "127.0.0.1";
	 static final public String ip = "192.168.1.181";
	// static String ip = "adlaunch.moji.com";
	// static int port = 80;
	// static int port = 8899;
	// static int port = 8081;
	// static int port = 9092;

	// static String ip = "103.249.255.147";
	static int port = 8080;
	// static int port = 8900;

	// 两个测试环境 -------------------------------
	// static String ip = "192.168.1.184";
	// static int port = 8080;

	// static String ip = "192.168.1.184";
	// static String ip = "adlaunch.moji.com";
	// static int port = 8181;
	// static int port = 9092;

	// static String ip = "103.17.43.206";
	// static int port = 8899;
	// --------------------------------------

	// static String ip = "192.168.1.67";
	// static int port = 8080;

	// static String ip = "103.17.43.210";
	// static int port = 8899;
	public static void main(String[] args) throws UnknownHostException, IOException {
		// InetAddress add = InetAddress.getByName("adlaunch.moji.com");
		// ip = add.getHostAddress();

		// socket.setSoTimeout(10000);
		// socket.setTcpNoDelay(true);
		for (int i = 0; i < 1; i++) {
			//
			// Socket socket = new Socket(ip, port);
			// AdRequest and = splash(AdType.SPLASH, Platform.IOS,
			// AdPosition.POS_SPLASH, 600);
			// print(socket, and);

		}

		// 时景blocking
		// Socket socket = new Socket(ip, port);
		// AdRequest and = splash(AdType.TAB_TYPE, Platform.IOS,
		// AdPosition.POS_BLOCKING_TAB_TIME_PAGE, 600);
		// print(socket, and);

		// Socket socket = new Socket(ip, port);
		// AdRequest ios = splash(AdType.SPLASH, Platform.IOS,
		// AdPosition.POS_SPLASH, 600);
		// print(socket, ios);
		//
		// // 其他广告
		AdRequest otherand = other(AdType.OTHERS_TYPE, Platform.IOS, AdPosition.POS_FEED_STREAM_INFORMATION);
		Socket socketOA = new Socket(ip, port);
		print(socketOA, otherand);

		// AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID,
		// AdPosition.POS_BLOCKING_TAB_TIME_PAGE);
		// Socket socketOI = new Socket(ip, port);
		// print(socketOI, otherOI);

		// AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.IOS,
		// AdPosition.POS_WEATHER_FRONT_PAGE_MIDDLE);
		// Socket socketOI = new Socket(ip, port);
		// print(socketOI, otherOI);

		// AdRequest otherand = other(AdType.OTHERS_TYPE, Platform.IOS,
		// AdPosition.POS_WEATHER_HOME_INDEX_ENTRY);
		// AdRequest otherand = other(AdType.OTHERS_TYPE, Platform.IOS,
		// AdPosition.POS_INDEX_ARTICLE_UPPER_BANNER);

		// AdRequest otherand = other(AdType.OTHERS_TYPE, Platform.IOS,
		// AdPosition.POS_INDEX_ARTICLE_RECOMMENDATION);
		// Socket socketOA = new Socket(ip, port);
		// print(socketOA, otherand);

		// AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID,
		// AdPosition.POS_WEATHER_FRONT_PAGE_MIDDLE,
		// AdPosition.POS_WEATHER_FRONT_PAGE_BOTTOM);
		// Socket socketOI = new Socket(ip, port);
		// print(socketOI, otherOI);
		//
		//
		// //穿衣助手道具
		// AdRequest daojuA = daoju(AdType.DISPLAY_WINDOW, Platform.ANDROID,
		// AdPosition.POS_AVATAR_SUIT_CLOTHES);
		// Socket socketdaojuA = new Socket(ip, port);
		// print(socketdaojuA, daojuA);
		//
		// AdRequest daojuI = daoju(AdType.DISPLAY_WINDOW, Platform.IOS,
		// AdPosition.POS_DRESS_ASSISTANT_PROPS);
		// Socket socketdaojuI = new Socket(ip, port);
		// print(socketdaojuI, daojuI);

		// //穿衣助手卡片1-3
		// AdRequest cardA = card(AdType.DISPLAY_WINDOW, Platform.ANDROID,
		// AdPosition.POS_DRESS_ASSISTANT_CARD_TWO);
		// Socket socketcardA = new Socket(ip, port);
		// print(socketcardA, cardA);
		// //
		// AdRequest cardI = card(AdType.DISPLAY_WINDOW, Platform.IOS,
		// AdPosition.POS_DRESS_ASSISTANT_CARD_ONE);
		// Socket socketcardI = new Socket(ip, port);
		// print(socketcardI, cardI);

		// 三件套 卡片, 道具
		// AdRequest cardA = suit(AdType.BGAVATAR, Platform.ANDROID,
		// AdPosition.POS_AVATAR_SUIT_CLOTHES);
		// Socket socketcardA = new Socket(ip, port);
		// print(socketcardA, cardA);
		//
		// AdRequest cardI = suit(AdType.BGAVATAR, Platform.IOS,
		// AdPosition.POS_AVATAR_SUIT_CLOTHES);
		// Socket socketcardI = new Socket(ip, port);
		// print(socketcardI, cardI);

	}

	private static AdRequest suit(AdType adType, Platform osType, AdPosition position) {
		AdRequest.Builder build = AdRequest.newBuilder();
		build.setType(adType);
		// build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
		// build.setType(AdType.BGAVATAR);
		// build.setType(AdType.SPLASH);

		build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		// build.setType(AdType.DISPLAY_WINDOW);
		build.setDayOrNight(DayOrNight.NIGHT);
		build.setAlreadyShowId(0l);
		build.setVersion(1);
		build.addLastAdIds(0l);
		build.setSessionId("a1000003001");
		build.setLatitude(39.972675f);
		build.setLongitude(116.490528f);
		build.setIsShortPrediction(IsShortPrediction.YES);
		build.setIsDebug(true);
		List<AdPosition> adPositions = new ArrayList<>();

		adPositions.add(position);
		build.addAllPosition(adPositions);

		AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();
		adc.setAndroidId("android");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
		adc.setPkgname("com.moji.MojiWeather");
		adc.setUid(654366087);
		adc.setCityId(19001);
		adc.setPublishType("2");
		adc.setAppVersion(1006030000);
		adc.setScreenHeight(1136);
		adc.setScreenWidth(640);
		adc.setLang(Language.SIMPLIFIED_CH);

		adc.setChannelId(9000);
		adc.setOsType(osType);
		adc.setOsVersion("6.1.0");

		adc.setPhoneType("IPHONE 6S PLUS");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setWma("wma");
		adc.setAvatarId(2);

		adc.setIsWifi(true);
		adc.setMcc(21);
		adc.setMnc(343);
		adc.setCarrier(CarrierType.CARRIER_MOBILE);

		build.setRequestCommon(adc);
		build.setDisplayTimes(1);
		build.setVersion(1);
		build.setIsDebug(true);
		AdRequest request = build.build();
		return request;
	}

	private static AdRequest card(AdType adType, Platform osType, AdPosition position) {
		AdRequest.Builder build = AdRequest.newBuilder();
		build.setType(adType);
		// build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
		// build.setType(AdType.BGAVATAR);
		// build.setType(AdType.SPLASH);

		build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		// build.setType(AdType.DISPLAY_WINDOW);
		build.setDayOrNight(DayOrNight.NIGHT);
		build.setAlreadyShowId(0l);
		build.setVersion(1);
		build.addLastAdIds(0l);
		build.setSessionId("a1000003001");
		build.setLatitude(39.972675f);
		build.setLongitude(116.490528f);
		build.setIsShortPrediction(IsShortPrediction.YES);
		build.setIsDebug(true);
		List<AdPosition> adPositions = new ArrayList<>();

		adPositions.add(position);
		build.addAllPosition(adPositions);

		AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();
		adc.setAndroidId("android");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
		adc.setPkgname("com.moji.MojiWeather");
		adc.setUid(654366087);
		adc.setCityId(600);
		adc.setPublishType("2");
		adc.setAppVersion(1006030000);
		adc.setScreenHeight(1136);
		adc.setScreenWidth(640);
		adc.setLang(Language.SIMPLIFIED_CH);

		adc.setChannelId(9000);
		adc.setOsType(osType);
		adc.setOsVersion("6.1.0");

		adc.setPhoneType("IPHONE 6S PLUS");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setWma("wma");
		adc.setAvatarId(2);

		adc.setIsWifi(true);
		adc.setMcc(21);
		adc.setMnc(343);
		adc.setCarrier(CarrierType.CARRIER_MOBILE);

		build.setRequestCommon(adc);
		build.setDisplayTimes(1);
		build.setVersion(1);
		build.setIsDebug(true);
		AdRequest request = build.build();
		return request;
	}

	private static AdRequest daoju(AdType adType, Platform osType, AdPosition position) {
		AdRequest.Builder build = AdRequest.newBuilder();
		build.setType(adType);
		// build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
		// build.setType(AdType.BGAVATAR);
		// build.setType(AdType.SPLASH);

		build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		// build.setType(AdType.DISPLAY_WINDOW);
		build.setDayOrNight(DayOrNight.NIGHT);
		build.setAlreadyShowId(0l);
		build.setVersion(1);
		build.addLastAdIds(0l);
		build.setSessionId("a1000003001");
		build.setLatitude(39.972675f);
		build.setLongitude(116.490528f);
		build.setIsShortPrediction(IsShortPrediction.YES);
		build.setIsDebug(true);
		List<AdPosition> adPositions = new ArrayList<>();

		adPositions.add(position);
		build.addAllPosition(adPositions);

		AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();
		adc.setAndroidId("android");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
		adc.setPkgname("com.moji.MojiWeather");
		adc.setUid(654366087);
		adc.setCityId(600);
		adc.setPublishType("2");
		adc.setAppVersion(1006030000);
		adc.setScreenHeight(1136);
		adc.setScreenWidth(640);
		adc.setLang(Language.SIMPLIFIED_CH);

		adc.setChannelId(9000);
		adc.setOsType(osType);
		adc.setOsVersion("6.0.1");

		adc.setPhoneType("IPHONE 6S PLUS");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setWma("wma");
		adc.setAvatarId(2);

		adc.setIsWifi(true);
		adc.setMcc(21);
		adc.setMnc(343);
		adc.setCarrier(CarrierType.CARRIER_MOBILE);

		build.setRequestCommon(adc);
		build.setDisplayTimes(1);
		build.setVersion(1);
		build.setIsDebug(true);
		AdRequest request = build.build();
		return request;
	}

	private static AdRequest other(AdType adType, Platform osType, AdPosition... position) {
		AdRequest.Builder build = AdRequest.newBuilder();
		build.setType(adType);
		// build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
		// build.setType(AdType.BGAVATAR);
		// build.setType(AdType.SPLASH);

		build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		// build.setType(AdType.DISPLAY_WINDOW);
		build.setDayOrNight(DayOrNight.NIGHT);
		build.setAlreadyShowId(0l);
		build.setVersion(1);
		build.addLastAdIds(0l);
		build.setSessionId("a1000003001");
		build.setLatitude(39.972675f);
		build.setLongitude(116.490528f);
		build.setIsShortPrediction(IsShortPrediction.YES);
		build.setFeedTab(20);
		List<AdPosition> adPositions = new ArrayList<>();
		for (AdPosition p : position) {
			adPositions.add(p);
		}
		build.addAllPosition(adPositions);

		AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();

		adc.setPkgname("com.moji.MojiWeather");
		adc.setUid(654366083);
		adc.setCityId(151);
		adc.setPublishType("2");
		adc.setAppVersion(1006030000);
		adc.setScreenHeight(1136);
		adc.setScreenWidth(640);
		adc.setLang(Language.SIMPLIFIED_CH);

		adc.setChannelId(5072);
		adc.setOsType(osType);

		phoneParam(osType, adc);
		adc.setWma("wma");
		adc.setAvatarId(2);
		adc.setIsWifi(true);
		adc.setMcc(21);
		adc.setMnc(343);
		adc.setCarrier(CarrierType.CARRIER_MOBILE);

		build.setRequestCommon(adc);
		build.setDisplayTimes(1);
		build.setVersion(1);
		build.setIsDebug(true);
		AdRequest request = build.build();
		return request;
	}

	private static void phoneParam(Platform osType, Builder adc) {
		if (osType == Platform.ANDROID) {
			android(adc);
		} else {
			ios(adc);
		}
	}

	private static void ios(Builder adc) {
		adc.setOsVersion("6.0.1");
		adc.setPhoneType("IPHONE 6S PLUS");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
	}

	private static void android(Builder adc) {
		adc.setOsVersion("6.0.1");
		adc.setAndroidId("android");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setPhoneType("SM-N9150");
		adc.setUa(
				"Mozilla/5.0+(Linux;+Android+6.0.1;+SM-N9150+Build/MMB29M;+wv)+AppleWebKit/537.36+(KHTML,+like+Gecko)+Version/4.0+Chrome/52.0.2743.98+Mobile+Safari/537.36mojia/1007000401");
	}

	private static void print(Socket socket, AdRequest request) throws IOException, InvalidProtocolBufferException {
		byte type = 0;
		byte[] endMark = { -128, -128, -128, -128, -128 };
		byte[] req = byteMerger(type, request.toByteArray(), endMark);
		// for (byte b : req) {
		// System.out.print((int) b + ",");
		// }
		// System.out.println();

		int bodyLen = req.length;
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.write(req, 0, bodyLen);

		InputStream input = socket.getInputStream();
		if (input != null) {
			byte[] by = toByteArray(input);
			// for (byte b : by) {
			// System.out.print((char) b);
			// }
			AdResponse adResponse = AdResponse.parseFrom(by);
			System.out.println(adResponse.toString());
			System.out.println(adResponse.getErrorMessage());
			// System.out.println(s);
			out.close();
			input.close();
		}
	}

	private static AdRequest splash(AdType adType, Platform osType, AdPosition position, int cityId) {
		AdRequest.Builder build = AdRequest.newBuilder();
		build.setType(adType);
		// build.setFeedTab(0);com/moji/launchserver/main/SocketTest.java:74
		// build.setType(AdType.BGAVATAR);
		// build.setType(AdType.SPLASH);

		build.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		// build.setType(AdType.DISPLAY_WINDOW);
		build.setDayOrNight(DayOrNight.NIGHT);
		build.setAlreadyShowId(0l);
		build.setVersion(1);
		build.addLastAdIds(0l);
		build.setSessionId("a1000003001");
		build.setLatitude(39.972675f);
		build.setLongitude(116.490528f);
		build.setIsShortPrediction(IsShortPrediction.YES);
		build.setIsDebug(true);
		List<AdPosition> adPositions = new ArrayList<>();

		adPositions.add(position);
		build.addAllPosition(adPositions);

		AdRequestCommon.Builder adc = AdRequestCommon.newBuilder();
		adc.setAndroidId("anxxxxx1123123");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E230");
		adc.setPkgname("com.moji.MojiWeather");
		adc.setUid(654366087);
		adc.setCityId(cityId);
		adc.setPublishType("2");
		adc.setAppVersion(1006130000);
		adc.setScreenHeight(1136);
		adc.setScreenWidth(640);
		adc.setLang(Language.SIMPLIFIED_CH);

		adc.setChannelId(9000);
		adc.setOsType(osType);
		adc.setOsVersion("6.1.0");

		adc.setPhoneType("IPHONE 6S PLUS");
		adc.setIdentifier("5F4546C3-680E-4F71-93AF-3EABCD637018");
		adc.setWma("wma");
		// adc.setAvatarId(2);

		adc.setIsWifi(true);
		adc.setMcc(21);
		adc.setMnc(343);
		adc.setCarrier(CarrierType.CARRIER_MOBILE);

		build.setRequestCommon(adc);
		build.setDisplayTimes(3);
		build.setVersion(1);
		build.setIsDebug(true);
		AdRequest request = build.build();
		return request;
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
