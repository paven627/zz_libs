package test.java.concurrent.mydeadlock;

public class Test2 {
	public static void main(String[] args) {
		MyThread mt = new MyThread(1);
		MyThread mt2 = new MyThread(2);

		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt2);
		t1.start();
		t2.start();
	}

}

class MyThread implements Runnable {
	public MyThread(int num) {
		this.num = num;
	}

	static MyObject o1 = new MyObject();
	static MyObject o2 = new MyObject();
	//
	int num = 0;

	public void run() {
		if (num == 1) {
			synchronized (o1) {
				System.out.printf("o1��ס%s ��ʼ\n", Thread.currentThread()
						.getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("o2��ס");
				}
			}
		}
		if (num == 2) {
			synchronized (o2) {
				System.out.printf("o2��ס %s ��ʼ\n", Thread.currentThread()
						.getName());
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
}

class MyObject {

}