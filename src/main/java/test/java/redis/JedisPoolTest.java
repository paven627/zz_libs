package test.java.redis;

import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("http",null,"60.205.230.151",6379,null,null,null);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMaxWaitMillis(1000);
		JedisPool pool = new JedisPool(config, uri);
		
		for (int i = 1; i < 12; i++) {
			Jedis jedis = pool.getResource();
			jedis.auth("barfoo");
			String string = jedis.get("a");
			System.out.println(i+","+string);
			jedis.close();
		}
		pool.close();
	}
}
