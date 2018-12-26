package test.java.redis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanResult;

public class RedisStringTest {
	Jedis jedis = new Jedis("192.168.1.182");

	@Test
	public void testScan(){
		ScanResult<String> scan = jedis.scan("0");
	}

	@Test
	public void test1Normal() {
		long start = System.currentTimeMillis();
		String result = jedis.set("abc", "aaaa");
		System.out.println(result);
		long end = System.currentTimeMillis();
		System.out.println("Simple SET: " + ((end - start) / 1000.0)
				+ " seconds");
		jedis.disconnect();
		jedis.close();

	}

	@Test
	public void test() {
		// setex_value 被替换为: setex_Setrange
		jedis.set("setex", "a");
		long result = jedis.setrange("setex", 6, "Setrange");
		System.out.println(result);
		System.out.println(jedis.get("setex"));
	}

	@Test
	public void test4combPipelineTrans() {
		long start = System.currentTimeMillis();
		Pipeline pipeline = jedis.pipelined();
		pipeline.multi();
		for (int i = 0; i < 100; i++) {
			pipeline.set("i" + i, "" + i);
		}
		pipeline.exec();
		System.out.println(1);
		List<Object> results = pipeline.syncAndReturnAll();
		System.out.println(results);
		long end = System.currentTimeMillis();
		System.out.println("Pipelined transaction: " + ((end - start) / 1000.0)
				+ " seconds");
		jedis.disconnect();
	}
}
