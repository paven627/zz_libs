package test.java.concurrent.threadlocal;

public class Test implements Runnable {
	static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Test()).start();
		}
	}

	@Override
	public void run() {
		m1();
		m2();
		Test2.test();
	}

	private void m1() {
		Connection con = new Connection();
		connection.set(con);
	}

	private void m2(){
		Connection conn = connection.get();
		Thread t = Thread.currentThread();
		System.out.println(t.getName() +"  "+ conn);
	}
}
