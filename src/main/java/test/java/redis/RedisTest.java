package test.java.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	static Jedis jedis = new Jedis("test", 6379);

	public static void main(String[] args) {
//		readIos();
//		readAndroid();
        String a = jedis.get("a");
        System.out.println(a);
        System.out.println(jedis.get("test"));


//		incrTest();

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
