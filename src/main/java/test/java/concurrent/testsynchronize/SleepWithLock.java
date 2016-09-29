package test.java.concurrent.testsynchronize;

/**
 * ͬ����
 */
public class SleepWithLock {

	public static void main(String[] args) {
		S3 s1 = new S3();
		Thread b = new Thread(new ThreadA3(s1));
		Thread a = new Thread(new ThreadA3(s1));
		b.start();
		a.start();
	}

}

class ThreadA3 implements Runnable {
	private S3 s3 = null;

	public ThreadA3(S3 s3) {
		this.s3 = s3;
	}

	/**
	 * ʹ����synchronized(s1) ֮��,��������ס,��ʹ��ǰ�߳̽��� sleep
	 * Ҳ��ռ����Դ��ʹ��һ�߳�����,���뵱ǰ�߳����н����Ż�������һ�߳� ���ȡ���˷����� synchronized(s3) ,
	 * ���������߳̽������
	 */
	@Override
	public void run() {
		synchronized (s3) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ", i=="
						+ i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class S3 {

}