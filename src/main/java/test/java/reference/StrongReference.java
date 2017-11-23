package test.java.reference;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;


/**
 * 强引用当没有任何对象指向它时Java GC 执行后将会被回收
 * @author bin.deng
 *
 */
public class StrongReference {
	
	@Test
	public void strongReference() {
		Object referent = new Object();

		/**
		 * 通过赋值创建 StrongReference
		 */
		Object strongReference = referent;

		assertSame(referent, strongReference);

		referent = null;
		System.gc();

		/**
		 * StrongReference 在 GC 后不会被回收
		 */
		assertNotNull(strongReference);
		
	}
}
