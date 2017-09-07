package test.java.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		readIos();
		readAndroid();
		
//		
//		
	}
	static void readIos(){
		Jedis jedis = new Jedis("192.168.1.198", 6382);
		jedis.set("ios654366087", "{\"5\":\"1231\",\"3\":\"100\",\"11\":\"10\",\"180\":\"10\"}");
		System.out.println(jedis.get("ios654366087"));
		jedis.close();
	}
	
	static void  readAndroid (){
		Jedis jedis = new Jedis("192.168.1.197", 6381);
		jedis.set("ad654366087", "{\"2\":\"111\",\"1\":\"1231\",\"8\":\"100\",\"11\":\"10\"}");
		System.out.println(jedis.get("ad654366087"));
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