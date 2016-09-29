package test.java.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.SentinelCommands;

public class PoolTest {
	public static void main(String[] args) {
		// Jedis jedis = new Jedis("192.168.146.129");
		// Jedis jedis = new Jedis("192.168.146.129", 26379);
		// jedis.auth("redis1");
		// String a = jedis.get("a");
		// System.out.println(a);
		se();
	}

	private static void se() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setTestOnBorrow(true);
		config.setMaxIdle(10);
		config.setMaxTotal(20);
		Set<String> ips = new HashSet<String>();
		ips.add("192.168.146.129:26379");
		ips.add("192.168.146.129:26479");
		ips.add("192.168.146.129:26579");
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", ips, config,
				10000, "redis1", 0);
		// JedisSentinelPool pool = new JedisSentinelPool("mymaster", ips,
		// "redis1");

		Jedis jedis = pool.getResource();
		jedis.auth("redis1");
		System.out.println(jedis);
		jedis.set("bb", "123");
		jedis.close();
		pool.close();

		// assertTrue(pool.isClosed());
	}
}
