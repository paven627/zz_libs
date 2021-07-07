package test.java.container;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author dengbin
 */
public class PriorityBlockingQueueTest {
    static PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();

    static java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static void main(String[] args) {
        Random random = new Random(100);

        new Thread(() -> {
            while (true) {
                try {
                    User data = queue.poll();
                    System.out.println(sdf.format(new Date()) + "," + data);
                    if (data != null) {

                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DataSyncConsumeThread").start();


        for (int i = 0; i < 5; i++) {
            User u = new User(random.nextInt(10));
            queue.add(u);
        }

        System.out.println(queue);

        User poll = queue.poll();
        System.out.println(poll);
        User peek = queue.peek();
        System.out.println(peek);
    }
}

class User implements Comparable<User> {
    public int id;

    public User(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(User o) {
        return id - o.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}

