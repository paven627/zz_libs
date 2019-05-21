import com.moji.launchserver.AdCommonInterface;
import com.moji.launchserver.entity.ThirdResponseData;
import com.moji.launchserver.entity.ThirdResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MyTest {

	public static void main(String[] args) {

		for (int i = 0; i <50; i++) {
			System.out.println("get /kafka/ad_kafka/partition_" + i);
		}
//		List<ThirdResponseEntity> listResponse = new ArrayList<>();
//		ThirdResponseEntity e = new ThirdResponseEntity();
//		List<ThirdResponseData> datas = new ArrayList<>();
//		ThirdResponseData d= new ThirdResponseData();
//		d.setImgurl("a");
//		datas.add(d);
//		e.setData(datas);
//		listResponse.add(e);
//		System.out.println(listResponse);
//		for (Iterator<ThirdResponseEntity> iterator = listResponse.iterator(); iterator.hasNext(); ) {
//			ThirdResponseEntity entity =  iterator.next();
//			for (ThirdResponseData data : entity.getData()) {
//				if (data.getImgurl() == null || data.getImgurl().equals("")) {
//					iterator.remove();
//				}
//			}
//		}
//		System.out.println(listResponse);
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