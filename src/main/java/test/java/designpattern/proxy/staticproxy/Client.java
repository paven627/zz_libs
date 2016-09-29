package test.java.designpattern.proxy.staticproxy;

public class Client {
	
	public static void main(String[] args) {
		ConnectionProxy proxy = new ConnectionProxy();
		proxy.getConnection();
		proxy.close();
	}
}
