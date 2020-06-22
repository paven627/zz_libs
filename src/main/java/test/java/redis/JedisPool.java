package test.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JedisPool {

    static redis.clients.jedis.JedisPool pool = new redis.clients.jedis.JedisPool("127.0.0.1", 6379);

    public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {

        Jedis resource = pool.getResource();
        System.out.println(resource);
        long l = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            resource.set("dsp_test1", "1000");
        }
        System.out.println(System.currentTimeMillis() - l);

        l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            resource.hset("dsp_test2", "123", "1000");
        }
        System.out.println(System.currentTimeMillis() - l);


    }
}
