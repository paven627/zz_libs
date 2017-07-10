import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTest {

	// static Map<Integer, Integer> map = new ConcurrentHashMap<>();
	static Map<Integer, Integer> map = new HashMap<>();
	static String a = "a";

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		System.out.println("12".hashCode());
		
		
		System.out.println(0x3FFFFFFF);
		System.out.println(0x0000003D);
	}

}