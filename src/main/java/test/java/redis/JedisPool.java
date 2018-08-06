package test.java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JedisPool {
    static redis.clients.jedis.JedisPool pool = new redis.clients.jedis.JedisPool("192.168.1.182", 6379);

    public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {


        Jedis jedis = pool.getResource();

        while (true) {
            List<String> brpop = jedis.brpop(10, "alist", "blist");
            System.out.println(brpop);
        }



    }
}
