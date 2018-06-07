package test.java.concurrent.testsynchronize;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestSynchronize {

	ReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		TestSynchronize test = new TestSynchronize();
//		 test.withoutLock();
		 test.testSynchronize();
	}

	private synchronized void synchPrint(String str) {
		print(str);
	}

	private void testSynchronize() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String str = "aaaaaaaa";
				synchPrint(str);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String str = "bbbbbbbb";
				synchPrint(str);
			}
		}).start();
	}

	private void print(String str) {
		while (true) {
			for (int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i));
			}
			System.out.println();
		}
	}

	private void withoutLock() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String str = "aaaaaaaa";
				print(str);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String str = "bbbbbbbb";
				print(str);
			}
		}).start();
	}
}
