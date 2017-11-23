package test.java.reference;

import static org.junit.Assert.*;

import java.lang.ref.SoftReference;

import org.junit.Test;

/**
 * SoftReference 于 WeakReference 的特性基本一致，最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM
 * 内存不足时才会被回收(虚拟机保证), 这一特性使得 SoftReference 非常适合缓存应用
 *
 */
public class SoftReferenceTest {
	@Test
	public void softReference() {
		Object referent = new Object();
		SoftReference<Object> softRerference = new SoftReference<Object>(referent);

		assertNotNull(softRerference.get());

		referent = null;
		System.gc();

		/**
		 * soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用
		 */
		assertNotNull(softRerference.get());
	}

}
