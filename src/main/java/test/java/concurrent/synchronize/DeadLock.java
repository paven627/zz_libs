package test.java.concurrent.synchronize;

public class DeadLock {
	public static void main(String[] args) {
		S s = new S();
		Thread b = new Thread(new ThreadB(s));
		Thread a = new Thread(new ThreadA(s, b));
		b.start();
		a.start();
	}

}

class S {
	public int a = 0;
}

class ThreadA implements Runnable {
	private S s = null;
	private Thread b = null;

	public ThreadA(S s, Thread b) {
		this.s = s;
		this.b = b;
	}

	public void run() {
		System.out.println("Now Begin ThreadA-----------");
		synchronized (s) {
			System.out.println("now threadA===="
					+ Thread.currentThread().getName());
			try {
				b.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("a=== " + s.a);
		}
	}
}

class ThreadB implements Runnable {
	private S s = null;

	public ThreadB(S s) {
		this.s = s;
	}

	public void run() {
		System.out.println("Now Begin ThreadB-----------");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadB go on");
		synchronized (s) {
			s.a = 6;
			System.out.println("now in ThreadB ==="
					+ Thread.currentThread().getName() + ",s.a===" + s.a);
		}
	}
}