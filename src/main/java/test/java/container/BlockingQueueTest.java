package test.java.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	
	public static void main(String[] args) {
		BlockingQueue<String> q = new LinkedBlockingQueue<>(3);
		q.add("a");
		q.add("b");
		q.add("c");
		q.add("d");
        System.out.println(q.size());

		System.out.println(q);
//		q.offer("");
	}
}
