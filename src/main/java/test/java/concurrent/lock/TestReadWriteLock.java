package test.java.concurrent.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �����ͬ��
 * д������Խ���Ϊ��ȡ��,����Ϊ,�Ȼ��д����,Ȼ���ö�ȡ��,Ȼ���ͷ�д����.����,�Ӷ�ȡ����д��������
 * 
 *  JDK ReadWriteLock ʾ��ģ��
 */
public class TestReadWriteLock {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private Map<String, String> cache = new HashMap<String, String>();

	// ����� synchronized �汾����
	public synchronized String synGet(String key) {
		if (!cache.containsKey(key)) {
			cache.put(key, "123");
		}
		return cache.get(key);
	}

	// �������汾
	public String lockGet(String key) {
		lock.readLock().lock();
		String value = null;
		try {
			if (!cache.containsKey(key)) {
				lock.readLock().unlock();
				lock.writeLock().lock();
				if(value == null){
					String data = "value";		// ��ѯ��ݿ����
					cache.put(key, data);
				}
			}
			lock.writeLock().unlock();
			lock.readLock().lock();
			value = cache.get(key);
		} finally {
			lock.readLock().unlock();
		}
		return value;
	}
	
	
	public static void main(String[] args) {
		TestReadWriteLock test = new TestReadWriteLock();
		String s1 = test.lockGet("key");
		System.out.println(s1);
		
		String s2 = test.synGet("key");
		System.out.println(s2);
	}
}
