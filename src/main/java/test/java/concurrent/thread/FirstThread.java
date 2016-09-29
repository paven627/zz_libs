package test.java.concurrent.thread;

public class FirstThread {

	public static void main(String[] args) {
		MyThread t = new MyThread();
		Thread th = new Thread(t);
		Thread th2 = new Thread(t);

		th.start();
		th2.start();
	}

}

class MyThread implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("Name ==" + Thread.currentThread().getName());
		}

	}

}