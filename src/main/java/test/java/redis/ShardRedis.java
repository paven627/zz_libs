package test.java.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class ShardRedis {
	public static void main(String[] args) {
		// shardRedis 会根据key的hash, 自动将key存在相应的 真是 redis实例中, 对外接口只是使用
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo si = new JedisShardInfo("10.200.4.141", 6379);
		shards.add(si);
		si = new JedisShardInfo("10.200.4.141", 6479);
		shards.add(si);

		si = new JedisShardInfo("10.200.4.141", 6579);
		shards.add(si);

		// 池对象
		ShardedJedisPool pool = new ShardedJedisPool(
				new GenericObjectPoolConfig(), shards, Hashing.MURMUR_HASH,

				Sharded.DEFAULT_KEY_TAG_PATTERN);

		// for (int i = 0; i < 20; i++) {
		// ShardedJedis jedis = pool.getResource();
		// jedis.set(i + "", i + "");
		// jedis.close();
		// }

		for (int i = 0; i < 20; i++) {
			ShardedJedis jedis = pool.getResource();
			System.out.println(jedis.get(i + ""));
			jedis.close();
		}

		pool.destroy();
		pool.close();
	}

	public static void test3() {
		Jedis jedis = new Jedis("10.200.4.141", 6579);
	}

}
