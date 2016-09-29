import java.util.Random;

import io.netty.util.internal.ThreadLocalRandom;

public class MyTest {
	public static void main(String[] args) {
		int i1 = 0;
		int i2 = 0;
		int rate = 1;
		for (int i = 0; i < 1000000; i++) {
			boolean nextInt = rollIfRequest(rate);
			if (nextInt) {
				i1++;
			} else {
				i2++;
			}
		}
		System.out.println( (new Double(i1)/ new Double(i1+i2)));
		System.out.println(i1);
	}
	
	private static boolean rollIfRequest(Integer rate) {
		int nextInt = ThreadLocalRandom.current().nextInt(100);
		return !(nextInt >= rate);
	}
}
