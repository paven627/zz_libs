import java.net.InetAddress;
import java.net.UnknownHostException;

public class DnsParse {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress address = InetAddress.getByName("wxh-PC");// wxh-PC是我的计算机名
		// System.out.println(address);
		// System.out.println("-----");
		// InetAddress address1 = InetAddress.getLocalHost();
		// System.out.println(address1);

		long t1 = System.currentTimeMillis();
		InetAddress[] addresses = InetAddress.getAllByName("m.adx.bcadx.com");
		long t2 = System.currentTimeMillis();
		System.out.println(t2- t1);
		System.out.println(addresses.length);
		for (InetAddress addr : addresses) {
			System.out.println(addr);
		}
	}
}
