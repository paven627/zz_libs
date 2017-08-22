package test.java.redis;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

public class LockRedis {
	Jedis jedis = new Jedis("127.0.0.1", 6379);
	public static void main(String[] args) {
		LockRedis test = new LockRedis();
		test.test();
	}
	ShardedJedis sjedis ;
	
	public void test() {
		
//		sjedis.set("aaa", "aaa",10 , TimeUnit.SECONDS, false);
//		sjedis.setnx
	}
	
}
