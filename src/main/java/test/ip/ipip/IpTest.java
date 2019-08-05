package test.ip.ipip;

import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import net.ipip.ipdb.District;
import net.ipip.ipdb.DistrictInfo;
import net.ipip.ipdb.IDC;
import net.ipip.ipdb.IDCInfo;

import java.util.Arrays;
import java.util.Map;

public class IpTest {
	static String path = "C:\\Users\\bin.deng\\Desktop\\ipipfreedb\\ipipfree.ipdb";
	static String ip ="36.17.63.221";
	public static void main(String[] args) {
		city();
		district();
//		idc();
	}

	private static void idc() {
		try {
			IDC db = new IDC(path);

			System.out.println(Arrays.toString(db.find("1.1.1.1", "CN")));

			IDCInfo info = db.findInfo("8.8.8.8", "CN");

			System.out.println(info.getCountryName());

			Map m = db.findMap("114.114.114.114", "CN");

			System.out.println(m);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void district() {
		try {
			District db = new District(path);

			System.out.println(Arrays.toString(db.find(ip, "CN")));

			DistrictInfo info = db.findInfo(ip, "CN");
			if (info != null) {
//				System.out.println(info);
				System.out.println(info.getCountryName());
				System.out.println(info.getCityName());
//				System.out.println(info.getDistrictName() == null ? "":info.getDistrictName());
			}

			Map m = db.findMap(ip, "CN");

			System.out.println(m);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void city() {
		try {
			// City类可用于IPDB格式的IPv4免费库，IPv4与IPv6的每周高级版、每日标准版、每日高级版、每日专业版、每日旗舰版
			City db = new City(path);

			// db.find(address, language) 返回索引数组
			System.out.println(Arrays.toString(db.find(ip, "CN")));

			// db.findInfo(address, language) 返回 CityInfo 对象
			CityInfo info = db.findInfo(ip, "CN");
//			System.out.println(info);
			System.out.println(info.getCityName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
