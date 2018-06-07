package test.java.concurrent.testsynchronize;

public class ProducerConsumer {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		new Thread(p).start();
		new Thread(c).start();
	}
}

class WoTou {
	int id;

	WoTou(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "wotou: " + id;
	}
}

class SyncStack {
	int index = 0;
	WoTou[] arrWT = new WoTou[6];

	public synchronized void push(WoTou wt) {
		while (index == arrWT.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// this.notify();
		arrWT[index] = wt;
		index++;
	}

	public synchronized WoTou pop() {
		while (index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// this.notify();
		index--;
		return arrWT[index];
	}
}

class Producer implements Runnable {
	SyncStack ss = null;

	Producer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			WoTou wt = new WoTou(i);
			ss.push(wt);
			System.out.println("�����һ����ͷ" + wt);
			try {
				Thread.sleep( ((int) Math.random()+1) * 200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	Consumer(SyncStack ss) {
		this.ss = ss;
	}

	SyncStack ss = null;

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			WoTou wt = ss.pop();
			System.out.println("�����һ����ͷ" + wt);
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
