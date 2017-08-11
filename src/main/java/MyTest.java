import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyTest {
	static DecimalFormat df = new DecimalFormat("#.0000");

	static int pv = 3921;
	static int clicks = 136;

	

	private static final Log logger = LogFactory.getLog(MyTest.class);

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		logger.error("11111111");;
	}

	/**
	 * 计算位数
	 * 
	 * @param str
	 * @return
	 */
	public static int calculatePlaces(String str) {
		int m = 0;
		char arr[] = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if ((c >= 0x0391 && c <= 0xFFE5)) // 中文字符
			{
				m = m + 2;
			} else if ((c >= 0x0000 && c <= 0x00FF)) // 英文字符
			{
				m = m + 1;
			}
		}
		return m;
	}
}