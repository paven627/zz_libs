import com.alibaba.fastjson.JSONObject;
import com.moji.launchserver.entity.City;
import com.moji.launchserver.util.LoadCityUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
	static String s = "{\"common\":{\"mnc\":\"11\",\"language\":\"CN\",\"app_version\":\"50070604\"," +
			"\"idfa_open\":\"1\"," +
			"\"unix\":\"1535507953383.735\",\"mcc\":\"460\",\"package_name\":\"com.moji.MojiWeather\"," +
			"\"net\":\"3g\"," +
			"\"identifier\":\"9FB78921-C665-40E8-8484-B71372E2E4FF\",\"platform\":\"iPhone\"," +
			"\"token\":\"<1a85d77c79ff0a5b23ee708a1ad1dec265fdb286cc8bf7d99a15a18a1ace9be1>\",\"uid\":652221653," +
			"\"idfv\":\"475C11B8-50D6-43B4-A5FF-C4889223CB26\",\"height\":2208,\"versionType\":\"1\"," +
			"\"locationcity\":\"1\",\"width\":1242,\"os_version\":\"8.3\",\"vip\":\"0\",\"pid\":\"9000\"," +
			"\"device\":\"iPhone7,1\"},\"params\":{\"openType\":false,\"position\":\"POS_GAME_GATE\"," +
			"\"city_id\":5483," +
			"\"tabType\":0,\"assistType\":0,\"property_type\":3,\"hotLaunch\":false," +
			"\"unqid\":\"5c2f4710-abdf-4885-8de0-be4476a4d42b\",\"ad:1,\"weather_index_part\":-1,\"purpose\":3," +
			"\"ad_partner\":0,\"net\":\"2G\",\"carrier\":3,\"ad_index\":301," +
			"\"requestId\":\"F2DjUg1CjBnBTgC7xmM69ISATZ9kGky1\",\"uid\":652221653,\"adverting_type\":3," +
			"\"stat_value\":1," +
			"\"ad_type\":1,\"materiel_id\":\"5259\",\"ad_id\":10010571,\"price\":\"0\",\"stat_type\"\":\"-\"}}";

	static String data = "{\"adclick_log_ali932\":\"2018-08-29 11:38:45  - " +
			"{'common':{'identifier':'861980032814049'," +
			"'app_version':'1006000002','os_version':'23','device':'RedmiNote3','platform':'Android','pid':'5599'," +
			"'language':'CN','uid':'332719865','width':1080,'height':1920,'sid':'','snsid':''}," +
			"'params':{'ad_id':10013997,'ad_index':308,'ad_index_part':-1,'ad_partner':0,'ad_type':1," +
			"'adverting_type':3,'assistType':0,'carrier':1,'city_id':5339,'materiel_id':'7291','net':'wifi'," +
			"'openType':false,'position':'POS_WEATHER_HOME_INDEX_ENTRY','price':'0','property_type':1,'purpose':1," +
			"'requestId':'1535513924092308','stat_type':1,'stat_value':1,'tabType':0,'uid':332719865," +
			"'unqid':'ef1e891e-4810-495c-86a0-b7a7cc4fa72c','weather':'-'}}\"}";

	private static String splitFilter = "  - ";

	public static void main(String[] args) throws Exception {
		LoadCityUtil.init();
		City provinceByCity = LoadCityUtil.getProvinceByCity(601);
		System.out.println(provinceByCity);
	}


	private static String MD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}
}