package test.java.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("a");
		queue.put("b");
		System.out.println(queue);
		String poll = queue.poll();
		System.out.println(poll);
		System.out.println(queue.poll());

	}
}
