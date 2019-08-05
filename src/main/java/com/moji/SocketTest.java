package com.moji;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.moji.launchserver.AdCommonInterface;
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
import com.moji.launchserver.util.AdStatUtil;

public class SocketTest {
//	ad.api.moji.com   	 打点域名

	 static String ip = "172.16.21.76"; // 线上调试
	 static int port = 9000;

//	static String ip = "192.168.42.24";

	//		static String ip = "127.0.0.1";
//	static String ip = "192.168.9.76";
//	 static String ip = "192.168.9.79";
//	 static String ip = "192.168.9.31";

//	static String ip = "103.17.43.219";

	// static String ip = "adlaunch.moji.com";
//	 static int port = 80;


//		static int port = 8080;
//	static int port = 8080;
	static String sessionid ="aaaaaaaaaaaaa";

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


		// 时景详情icon
//        AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_TIME_SCENE_DETAIL_ICON, 600,
//        1,"com.moji.mjweather", 1007080601);

//		AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition
//				.POS_FIFTEEN_DAY_FORECAST_TOP_BANNER, 600,1, "com.moji.mjweather", 1007080601);


		//上方
//		AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition
//				.POS_VOICE_BROADCAST_ABOVE, 600,1, "com.moji.mjweather", 1007080601);

		//下方+
//		AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_VOICE_BROADCAST_UNDER
//				, 400, 10, "com.moji.mjweather", 1007080600l);

		AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_FEED_STREAM_DETAILS
				, 600,1, "com.moji.mjweather", 1007080600l);

		//每日详情中部
//		AdRequest otherOI = other(AdType.OTHERS_TYPE, Platform.ANDROID, AdPosition.POS_TIME_VIEW_COMMENTS_LIST
//				, 600,1, "com.moji.mjweather", 1007090601);

//		AdRequest otherOI = other(AdType.SPLASH, Platform.ANDROID, AdPosition.POS_SPLASH
//				, 3175,1, "com.moji.mjweather", 1007090601);

////
//		50070506
//		1007070401
//		com.moji.MjWeather

//		Proxy proxy = createProxy("127.0.0.1", 1080);
//		Socket socketOI = new Socket(proxy);
//		socketOI.connect(new InetSocketAddress(ip, port));




		socketOI = new Socket(ip, port);
		print(socketOI, otherOI);

		// 50070204
//		 feedclick();

//		com.moji.mjweather

	}

	private static Proxy createProxy(String proxyAddr, int proxyPort) {
		// 设置认证
//		Authenticator.setDefault(new Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("username", "password"
//						.toCharArray());
//			}
//		});
//		// 设置HTTP代理
		Proxy proxy = new Proxy(Proxy.Type.SOCKS,
				new InetSocketAddress(proxyAddr, proxyPort));
		return proxy;
	}

	static Socket socketOI;


	private static void ios(Builder adc) {
		adc.setOsVersion("8.1.2");
		adc.setPhoneType("iPhone10,3");
//		adc.setIdentifier("856779034959966");
		adc.setIdentifier("91A4ACB4-8F2B-481C-A93B-3BC05A67B2C0");
		adc.setUa(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) " +
						"Mobile/13E230");
	}

	private static void android(Builder adc) {
		adc.setOsVersion("28");
		adc.setAndroidId("2465b6aa503c15bf");
		adc.setIdentifier("865043046165746");
//		adc.setIdentifier("123");
		adc.setPhoneType("OPPO R7S");
		adc.setUa(
				"Mozilla/5.0+(Linux;+Android+6.0.1;+SM-N9150+Build/MMB29M;+wv)+AppleWebKit/537.36+(KHTML," +
						"+like+Gecko)+Version/4.0+Chrome/52.0.2743.98+Mobile+Safari/537.36mojia/1007000401");
	}


	private static AdRequest other(AdType adType, Platform osType, AdPosition position, int cityId, int channelId,
								   String packageName, long version) {
		AdRequest.Builder request = AdRequest.newBuilder();
		AdRequestCommon.Builder common = AdRequestCommon.newBuilder();

		common.setRegisterChannelId("100");
		common.setAvatarId(8);
		request.setFeedTab(0);

		request.setType(adType);
		request.setCommentNumber(14);
		// request.setImageName("dbe90dac8e6cd0fabf6a2c7cf51bbc7f");
		request.setDayOrNight(DayOrNight.NIGHT);
		request.setVersion(1);

//		request.setSessionId("m1ba23dddddddddddd");
		request.setSessionId(sessionid);
		request.setLatitude(31.284018f);
		request.setLongitude(121.44974f);
		request.setIsShortPrediction(IsShortPrediction.NO);
		request.setIsMember(0);
		List<AdPosition> adPositions = new ArrayList<>();
		adPositions.add(position);
		request.addExsitedAdIds(10017381);
		request.addExsitedAdIds(10017354);
//        request.addExsitedAdIds(2623);
//
		request.addLastAdIds(10017381);
		request.addLastAdIds(10017354);
//        request.addLastAdIds(2623);
		request.setAlreadyShowId(10017381);
		request.setAlreadyShowId(10017354);

		request.addAllPosition(adPositions);


		common.setPkgname(packageName);
		common.setUid(654366082);
		common.setCityId(cityId);
		common.setPublishType("1");

		common.setAppVersion(version);
		common.setScreenWidth(375);
		common.setScreenHeight(812);
//		common.setScreenWidth(1080);
//		common.setScreenHeight(1920);
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
		common.setRegisterTime(1557484511);
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
			List<AdCommonInterface.AdUtilDetail> l = new ArrayList<>();
//			for (AdCommonInterface.AdUtilDetail detail : adUtilDetailList) {
//				System.out.println(System.identityHashCode(adUtilDetail));
//			}


//			System.out.println(l);
			System.out.println(adResponse.toString());
//			System.out.println(adResponse.getErrorMessage());
			// System.out.println(s);
			out.close();
			input.close();
		}
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
