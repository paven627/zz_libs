package test.java.reference;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Test;

/**
 * WeakHashMap 使用 WeakReference 作为 key， 一旦没有指向 key 的强引用, WeakHashMap 在Java GC
 * 后将自动删除相关的 entry
 *
 */
public class WeakHashMapTest {

	@Test
	public void weakHashMap() throws InterruptedException {
		Map<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();
		Object key = new Object();
		Object value = new Object();
		weakHashMap.put(key, value);

		assertTrue(weakHashMap.containsValue(value));

		key = null;
		System.gc();

		/**
		 * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
		 */
		Thread.sleep(1000);

		/**
		 * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
		 */
		assertFalse(weakHashMap.containsValue(value));
	}
}
