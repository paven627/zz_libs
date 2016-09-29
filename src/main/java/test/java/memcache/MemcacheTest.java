package test.java.memcache;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcacheTest {
	private static MemCachedClient cachedClient;
	// 单例模式实现客户端管理类

	public static void main(String[] args) {
		cachedClient = new MemCachedClient();
		// 获取连接池实例
		SockIOPool pool = SockIOPool.getInstance();

		// 设置缓存服务器地址，可以设置多个实现分布式缓存
		pool.setServers(new String[] { "192.168.1.101:11111" });

		// 设置初始连接5
		pool.setInitConn(5);
		// 设置最小连接5
		pool.setMinConn(5);
		// 设置最大连接250
		pool.setMaxConn(250);
		// 设置每个连接最大空闲时间3个小时
		pool.setMaxIdle(1000 * 60 * 60 * 3);

		pool.setMaintSleep(30);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.initialize();


//		boolean add = cachedClient.add("a", "b");
//		System.out.println(add);

		System.out.println(cachedClient.get("a"));

	}
}
