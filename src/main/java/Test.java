import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {
	private static SimpleDateFormat sdfSS = new SimpleDateFormat("hhmmss");

	static Random r = new Random();
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			int nextInt = ThreadLocalRandom.current().nextInt(100);
			r.nextInt(100);
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}

}