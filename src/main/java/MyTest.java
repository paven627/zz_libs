import java.util.concurrent.ThreadLocalRandom;

public class MyTest {


	static String[] keys = new String[]{"id"};
	static Class cls;

	//生成map结构的缓存结构

	public static void main(String[] args) throws Throwable {
//		setValueFromFilePath("C:\\workspace\\resource\\181\\otherAdPlatform.properties");
//		setValueFromFilePath();

//		boolean b = positionApiFlowControl("1");
//		System.out.println(b);

		for (int i = 0; i <30; i++) {
			int i1 = ThreadLocalRandom.current().nextInt(10);
			System.out.println(i1);
		}
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