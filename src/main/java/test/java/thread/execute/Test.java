package test.java.thread.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<Integer>> fs = new ArrayList<Future<Integer>>();
		for (int i = 0; i < 5; i++) {
			Future<Integer> f = exec.submit(new Spot(i));
			System.out.println(f.isDone());
			fs.add(f);
		}
		for (int i = 0; i < fs.size(); i++) {
			Future<Integer> f = fs.get(i);
			System.out.println(f.isDone());
			Integer integer = f.get();
			System.out.println("监测点:" + integer);
		}
		exec.shutdown(); // 并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
	}
}

class CountDown implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this + ",  " + i);
		}
	}

}

class Spot implements Callable<Integer> {
	int campaignId;

	public Spot(int campaignId) {
		super();
		this.campaignId = campaignId;
	}

	@Override
	public Integer call() throws Exception {
		// if (this.campaignId == 0)
		// throw new RuntimeException("test");
		System.out.println("创建Spots, 属于活动 :" + campaignId);
		return 100 + campaignId;
	}

}
