package test.java.redis;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {



	public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		URI uri = new URI("http", null, "60.205.230.151", 6379, null, null, null);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(15);
		config.setMaxWaitMillis(1000);
		JedisPool pool = new JedisPool(config, uri);
		
		final List<Future> list = new ArrayList<>();
		for (int i = 1; i < 13; i++) {
			Future<String> result = threadPool.submit(new Callable<String>() {
				
				@Override
				public String call() throws Exception {
					Jedis jedis = pool.getResource();
					jedis.auth("barfoo");
					String string = jedis.get("a");
//					jedis.close();
//					Thread.sleep(1000);
					return string;
				}
			});
			list.add(result);
			System.out.println(i);
		}
		
		for (Future future : list) {
			Object object = future.get();
			System.out.println(object);
		}
		pool.close();
		threadPool.shutdown();
	}
}
