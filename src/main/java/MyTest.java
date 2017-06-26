import java.util.HashMap;
import java.util.Map;

public class MyTest {
	public static void main(String[] args) {
		Map a = new HashMap();
		a.put(1, 1);
		System.out.println(a);
		
		Map b = a;
		System.out.println(a);
		System.out.println(b);
		a = new HashMap();
		System.out.println(a);
		System.out.println(b);
		
	}

}