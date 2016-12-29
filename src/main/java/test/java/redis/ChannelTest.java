package test.java.redis;

import org.junit.Test;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * 订阅
 */
public class ChannelTest {
	private static Jedis jedis = new Jedis("192.168.1.102");
	static JedisPool pool = new JedisPool("192.168.1.102", 6379);

	@Test
	public void read() {
		Jedis jedis = new Jedis("192.168.1.102");
		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void proceed(Client client, String... channels) {
				super.proceed(client, channels);
			}

			@Override
			public void onMessage(String channel, String message) {
				System.out.println("名称:" + channel + ", 值:" + message);
				super.onMessage(channel, message);
			}
		};

//		pool.getResource().subscribe(jedisPubSub, "channel1");
		jedis.subscribe(jedisPubSub, "channel2");
		jedis.close();
	}

	@Test
	public void write() {
		jedis.publish("channel1", "test1");
		jedis.publish("channel1", "test2");
		jedis.publish("channel2", "test3");
	}
}
