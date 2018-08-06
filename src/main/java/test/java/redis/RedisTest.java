package test.java.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
//		readIos();
//		readAndroid();

        Jedis jedis = new Jedis("192.168.1.182", 6379);
        String ping = jedis.ping();
        String advertMateriel4257 = jedis.get("advertMateriel4257");
        System.out.println(advertMateriel4257);
    }

    static void readIos() {
        Jedis jedis = new Jedis("192.168.1.198", 6382);
        jedis.set("ios654366087", "{\"5\":\"1231\",\"3\":\"100\",\"11\":\"10\",\"180\":\"10\"}");
        System.out.println(jedis.get("ios654366087"));
        jedis.close();
    }

    static void readAndroid() {
        Jedis jedis = new Jedis("192.168.1.197", 6381);
        jedis.set("ad654366087", "{\"2\":\"111\",\"1\":\"1231\",\"8\":\"100\",\"11\":\"10\"}");
        System.out.println(jedis.get("ad654366087"));
        jedis.close();
    }
}
