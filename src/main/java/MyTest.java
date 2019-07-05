import java.util.concurrent.ThreadLocalRandom;

public class MyTest {

	// 从文件读到内存
	private static void setValueFromFilePath()
			throws Throwable {
//
//		List<String> list = new ArrayList<>();
//		for (String s : words) {
//			list.add(s);
//		}
//		String s = "小姨欺负哑巴窝囊，谁知他竟是装哑十年！只待十年一到便可";
//		long l1 = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			for (String s1 : list) {
//				int i1 = s.indexOf(s1);
//				if (i1 >= 0) {
//					System.out.println();
//				}
//			}
//		}
//		long l2 = System.currentTimeMillis();
//		System.out.println(l2 - l1);
	}


	static String[] keys = new String[]{"id"};
	static Class cls;

	//生成map结构的缓存结构

	public static void main(String[] args) throws Throwable {
//		setValueFromFilePath("C:\\workspace\\resource\\181\\otherAdPlatform.properties");
//		setValueFromFilePath();

		boolean b = positionApiFlowControl("1");
		System.out.println(b);
	}

	private static boolean positionApiFlowControl(String posPercentStr) {
		if (posPercentStr == null) {
			return commonApiFlowControl();
		}
		int posPercent = Integer.parseInt(posPercentStr);
		if (posPercent >= 100) {
			return true;
		}
		if (posPercent == 0) {
			return false;
		}
		return ThreadLocalRandom.current().nextInt(100) < posPercent;
	}

	//  通用API流量控制 , true 请求, false 不请求
	private static boolean commonApiFlowControl() {
		int dsp_request_percent = 60;
		if (dsp_request_percent >= 100) {
			return true;
		}
		if (dsp_request_percent == 0) {
			return false;
		}
		return ThreadLocalRandom.current().nextInt(100) < dsp_request_percent;
	}

	public static void gongzi(String[] args) throws Exception {
//		int month = 1;
		int gongzi = 30000;
		float rate = 0.03f;
		float lastmonth = 0;
		for (int month = 1; month < 13; month++) {
			if (month > 1) {
				rate = 0.1f;
			}
			float dangyue = ((gongzi * month) - (5000 * month) - (5641.90f * month) - (1000 * month)) * rate -
					lastmonth;
			if (month > 1) {
				dangyue -= 2520;
			}
			lastmonth += dangyue;
//			System.out.println("上月:" + lastmonth);
			System.out.println("月:" + month + "," + dangyue);
		}
	}

}