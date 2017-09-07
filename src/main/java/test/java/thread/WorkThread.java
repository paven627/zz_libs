package test.java.thread;

public class WorkThread extends Thread {

	private int sleep;

	public WorkThread(ThreadGroup group, String name, int sleepSeconds) {
		super(group, name);
	}

	@Override
	public void run() {
		System.out.println("workthread运行");
		try {
			Thread.sleep(sleep * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
