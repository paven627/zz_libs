import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// Apr 10, 2019 11:30:00 PM
		Date d = sdf.parse("2019-04-10 00:00:00");
		System.out.println(d);

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