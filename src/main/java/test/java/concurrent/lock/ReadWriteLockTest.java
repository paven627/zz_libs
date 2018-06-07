package test.java.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        q3.read();
                    }
                }

            }.start();
            new Thread() {
                public void run() {
                    while (true) {
                        q3.write(new Random().nextInt(10000));
                    }
                }

            }.start();
        }

    }
}

class Queue3 {
    private Object data = null;

    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void read() {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Before  Read ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " After  Read " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void write(Object data) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Before Write ");
            Thread.sleep(1000);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " After  Write  " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }

    }
}
