package test.java.concurrent.threadlocal;

public class Test2 {
	public static void test(){
		Thread t = Thread.currentThread();
		Connection conn = Test.connection.get();
		System.out.println(t.getName() +"  "  +conn);
	}
}
