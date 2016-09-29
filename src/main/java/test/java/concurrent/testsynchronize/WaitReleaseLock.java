package test.java.concurrent.testsynchronize;

public class WaitReleaseLock {

	/**
	 * �ͷ��� �ͷ�����
	 * 
	 */
	public static void main(String[] args) {
		ThreadA4 t = new ThreadA4(true);
		Thread b = new Thread(t);
		Thread a = new Thread(t);
		b.start();
		a.start();
	}

}

class ThreadA4 implements Runnable {
	private boolean needWait = false;

	public ThreadA4(boolean needWait) {
		this.needWait = needWait;
	}

	/**
	 * �߳�0 ����ѭ��ʱ�����wait(),�ͷ�����Դ���߳�1 �������ʱ�߳�0������һ��ѭ��֮��needWait��Ϊflase,�����ǹ������
	 * �߳�1������ʱ,�Ͳ�������wait����
	 * 
	 * ��wait��Ϊsleep(2000),��Ȼ�����ͷ���Դ.�߳�0����������߳�1
	 */
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()
						+ ",  i====" + i);
				try {
					if (needWait) {
						this.needWait = false;
						// Thread.sleep(2000);
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class S4 {

}