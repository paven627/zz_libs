package test.java.reference;

import static org.junit.Assert.*;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.junit.Test;

public class RererenceQueueTest {
	@Test
	public void referenceQueue() throws InterruptedException {
		Object referent = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		WeakReference<Object> weakReference = new WeakReference<Object>(referent, referenceQueue);

		assertFalse(weakReference.isEnqueued());
		Reference<? extends Object> polled = referenceQueue.poll();
		assertNull(polled);

		referent = null;
		System.gc();

		assertTrue(weakReference.isEnqueued());
		Reference<? extends Object> removed = referenceQueue.remove();
		assertNotNull(removed);
	}
}
