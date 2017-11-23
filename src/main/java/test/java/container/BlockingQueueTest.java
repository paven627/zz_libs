package test.java.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	
	public static void main(String[] args) {
		BlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.add("a");
		q.offer("");
	}
}
