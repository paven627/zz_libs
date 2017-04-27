package test.java.thread;

public class ThreadGroupTest {
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup tg = new ThreadGroup("My Group");

		WorkThread thrd = new WorkThread(tg, "MyThread #1", 1);
		WorkThread thrd2 = new WorkThread(tg, "MyThread #2", 1);
		WorkThread thrd3 = new WorkThread(tg, "MyThread #3", 1);

		thrd.start();
		thrd2.start();
		thrd3.start();


		System.out.println(tg.activeCount() + " 个活动线程");

		Thread thrds[] = new Thread[tg.activeCount()];
		tg.enumerate(thrds);
		for (Thread t : thrds) {
			System.out.println(t.getName());
		}
		System.out.println(tg.activeCount() + " 个活动线程");
		tg.interrupt();
	}
}
