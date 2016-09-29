package test.java.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class ThreadException {
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException {
		int count = 10;
		CountDownLatch latch = new CountDownLatch(count);// 两个工人的协作
		for (int i = 1; i <= count; i++) {
			Worker worker1 = new Worker("worker" + count, 5000, latch);
			worker1.start();//
		}
		latch.await();// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
	}

	static class Worker extends Thread {
		String workerName;
		int workTime;
		CountDownLatch latch;

		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}

		public void run() {
			System.out.println("Worker " + workerName + " do work begin at "
					+ sdf.format(new Date()));
			try {
				doWork();// 工作了
				System.out.println("Worker " + workerName
						+ " do work complete at " + sdf.format(new Date()));
			} finally {
				if (latch.getCount() == 3) {
					try {
						testException();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				latch.countDown();// 工人完成工作，计数器减一
				System.out.println("this latch is " + latch.getCount());
			}

		}

		private void testException() throws Exception {
			throw new Exception("测试异常");
		}

		private void doWork() {
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
