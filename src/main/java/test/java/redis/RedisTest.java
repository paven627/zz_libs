package test.java.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("60.205.230.151", 6379);
		jedis.set("test3", "test1");
		String string = jedis.get("test");
		System.out.println(string);
		Long dbSize = jedis.dbSize();
		System.out.println(dbSize);

		jedis.mset("id", "1", "name", "邓斌");
		System.out.println(jedis.mget("id","name"));
		jedis.close();
		
	}
}

class User {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}