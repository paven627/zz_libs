package test.java.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class ShardRedis {
	public static void main(String[] args) {
		// shardRedis 会根据key的hash, 自动将key存在相应的 真是 redis实例中, 对外接口只是使用
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo si = new JedisShardInfo("192.168.1.196", 6381);
		shards.add(si);
		si = new JedisShardInfo("192.168.1.198", 6382);
		shards.add(si);
		si = new JedisShardInfo("192.168.1.198", 6381);
		shards.add(si);

		si = new JedisShardInfo("192.168.1.196", 6382);
		shards.add(si);
		si = new JedisShardInfo("192.168.1.197", 6382);
		shards.add(si);
		si = new JedisShardInfo("192.168.1.197", 6381);
		shards.add(si);

		// 池对象
		ShardedJedisPool pool = new ShardedJedisPool(new GenericObjectPoolConfig(), shards, Hashing.MURMUR_HASH,
				Sharded.DEFAULT_KEY_TAG_PATTERN);

		// for (int i = 0; i < 20; i++) {
		// ShardedJedis jedis = pool.getResource();
		// jedis.set(i + "", i + "");
		// jedis.close();
		// }

		// for (int i = 0; i < 20; i++) {
		// ShardedJedis jedis = pool.getResource();
		// System.out.println(jedis.get(i + ""));
		// jedis.close();
		// }
		ShardedJedis jedis = pool.getResource();
//		jedis.set("an654366087", "{\"1\":\"111\",\"5\":\"1231\"}");
//		
		String string = jedis.get("an654366087");
		System.out.println(string);
		
		pool.destroy();
		pool.close();
	}

}
