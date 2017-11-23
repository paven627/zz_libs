package test.java.reference;

import static org.junit.Assert.assertNull;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.junit.Test;


/**
 * PhantomReference 唯一的用处就是跟踪 referent何时被 enqueue 到 ReferenceQueue 中.
 *
 */
public class PhantomReferenceTest {
	@Test
	public void phantomReferenceAlwaysNull() {
		Object referent = new Object();
		PhantomReference<Object> phantomReference = new PhantomReference<Object>(referent,
				new ReferenceQueue<Object>());	

		/**
		 * phantom reference 的 get 方法永远返回 null
		 */
		assertNull(phantomReference.get());
	}
}
