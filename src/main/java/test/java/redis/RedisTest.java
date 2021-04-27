package test.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;

public class RedisTest {
//	static Jedis jedis = new Jedis("test-redis-zz.apuscn.com", 6379);
	static Jedis jedis = new Jedis("10.11.8.40", 6379);

	public static void main(String[] args) throws InterruptedException {
//		readIos();
//		readAndroid();

//        List<String> mget = jedis.hmget("key1", "f1", "f3");
//        System.out.println(mget);
//        System.out.println(mget.get(0));
//        System.out.println(mget.get(1));

//		incrTest();

//        pipelinetest();


        jedis.set("a", "a");
        jedis.set("b", "b");

        Boolean hexists = jedis.hexists("dsp_creative_1", "firstimp");
        System.out.println(hexists);

        hexists = jedis.hexists("dsp_creative_1", "firstimp1");
        System.out.println(hexists);


    }

	private static void pipelinetest(){
        Pipeline pipelined = jedis.pipelined();
        List<Response<List<String>>> resls = new ArrayList<>();
        resls.add(pipelined.hmget("key1", "f1"));
        resls.add(pipelined.hmget("key1", "f2"));
        resls.add(pipelined.hmget("key1", "f3"));
        pipelined.sync();
        for (Response<List<String>> resl : resls) {
            List<String> strings = resl.get();
            System.out.println(strings);
        }
    }

	private static void incrTest() {
		long l = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			jedis.incr("http://v.pic.fastapi.net/magic/6f/6fa3b110a59c336c2930ee9885acb940.jpg?p" +
					"=aHR0cDovL3QxMC5iYWlkdS5jb20vaXQvdT0yMzY2Mjc0MDAsMzMyNTAwOTY5OSZmbT03Ng" +
					".VFRUUjpkZWdyZWU9MC41AWRlc2M95ZKM55Sw546J5omL6ZWv5LiA6Iis5aSa5bCR6ZKxAWZvbnRfc2l6ZV9yYXRpbz0xLjIBaD0xODABbWNvbG9yPTAwMDAwMAFzaW5nbGVsaW5lPTEBdGNvbG9yPWZmZmZmZgF0eHQ9546J5omL6ZWv5Lu35qC8AXc9NzIw");

		}
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l);
	}


	static void readAndroid() {
		Jedis jedis = new Jedis("192.168.1.197", 6381);
		jedis.set("ad654366087", "{\"2\":\"111\",\"1\":\"1231\",\"8\":\"100\",\"11\":\"10\"}");
		System.out.println(jedis.get("ad654366087"));
		jedis.close();
	}
}
