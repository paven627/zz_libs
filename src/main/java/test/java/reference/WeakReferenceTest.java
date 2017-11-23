package test.java.reference;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.lang.ref.WeakReference;

import org.junit.Test;


/**
 * WeakReference，弱引用,当所引用的对象在 JVM 内不再有强引用时, Java GC 后 weak reference 将会被自动回收
 * @author bin.deng
 *
 */
public class WeakReferenceTest {
	@Test
	public void weakReference() {
		Object referent = new Object();
		WeakReference<Object> weakRerference = new WeakReference<Object>(referent);

		assertSame(referent, weakRerference.get());

		referent = null;
		System.gc();

		/**
		 * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
		 */
		assertNull(weakRerference.get());
	}

}
