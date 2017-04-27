import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MyTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String(
				"\350\257\267\346\261\202\345\271\277\345\221\212\344\275\215\346\270\240\351\201\223\346\227\240\345\271\277\345\221\212"
						.getBytes("UTF-8")));
	}
}