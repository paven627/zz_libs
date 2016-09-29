package test.java.concurrent.mydeadlock;

public class Test {
	static MyObject1 o1 = new MyObject1();
	static MyObject1 o2 = new MyObject1();

	public static void main(String[] args) {
		MyThread1 mt = new MyThread1(o1, o2);
		MyThread2 mt2 = new MyThread2(o1, o2);
		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt2);
		t1.start();
		t2.start();
	}

}

class MyThread1 implements Runnable {
	public MyThread1(MyObject1 o1, MyObject1 o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	MyObject1 o1 = null;
	MyObject1 o2 = null;

	public void run() {
		synchronized (o1) {
			System.out.printf("o1��ס%s ��ʼ\n", Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (o2) {
			System.out.println("o2��ס");
		}
	}
}

class MyThread2 implements Runnable {
	public MyThread2(MyObject1 o1, MyObject1 o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	MyObject1 o1 = null;
	MyObject1 o2 = null;

	@Override
	public void run() {
		synchronized (o2) {
			System.out.printf("o2��ס %s ��ʼ\n", Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (o1) {
				System.out.println("o1��ס");
			}
		}
	}
}

class MyObject1 {

}