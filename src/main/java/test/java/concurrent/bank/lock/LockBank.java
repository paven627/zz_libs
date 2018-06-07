package test.java.concurrent.bank.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * core java ʾ��
 */
public class LockBank {

	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;

	public LockBank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}

	public void transfer(int from, int to, double amount)
			throws InterruptedException {
		bankLock.lock(); // ��ס
		try {
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			System.out.println(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf("��� : %10.2f%n", getTotalBalance());

		} finally {
			// �쳣ʱ�ͷ���
			bankLock.unlock();
		}

	}

	private Object getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		LockBank test = new LockBank(10, 1000);
		test.transfer(1, 3, 1000);
	}

}
