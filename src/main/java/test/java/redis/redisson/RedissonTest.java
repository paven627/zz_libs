package test.java.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonTest {
    static RedissonClient redisson;

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.11.8.40:6379");
        redisson = Redisson.create(config);


        RLock lock = redisson.getLock("lock");
        lock.lock(20, TimeUnit.SECONDS);
//        System.out.println(1);
//        try {
//            lock.tryLock(3, 30, TimeUnit.SECONDS);
//            System.out.println(2);
//        } catch (InterruptedException e) {
//            lock.unlock();
//        }

        writeLock();
        redisson.shutdown();
    }

    public static void writeLock() {
        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");

        rwlock.writeLock().lock(10, TimeUnit.SECONDS);
        new Thread(() -> {
            RReadWriteLock rwlock2 = redisson.getReadWriteLock("anyRWLock");
            System.out.println(1);
//                boolean b = rwlock2.writeLock().tryLock(3, 20, TimeUnit.SECONDS);
            rwlock2.writeLock().lock(20, TimeUnit.SECONDS);
            System.out.println(2);
        }).start();
    }
}
