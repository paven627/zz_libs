import java.util.Random;

public class MyTest {
	public static void main(String[] args) {
		Random random = new Random();
		int index = random.nextInt(2);
		System.out.println(index);
	}
}