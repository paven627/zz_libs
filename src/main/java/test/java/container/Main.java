package test.java.container;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;
 
/**
 * PriorityQueue不是线程安全的。
 * PriorityBlockingQueue是阻塞式的Java线程安全队列。
 */
 
public class Main {
 
    public static void main(String[] args) {
        try {
            new Main().test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void test() throws InterruptedException {
        t();
    }
 
    private void t() throws InterruptedException {
        int capacity = 15;
 
        PriorityBlockingQueue<Integer> mQueue = new PriorityBlockingQueue(capacity, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
 
        //随机生成10个测试数据。
        for (int i = 0; i < capacity; i++) {
//            int n = (int) (Math.random() * 10);
            mQueue.add(i);
        }
 
        Iterator<Integer> iterator = mQueue.iterator();
        while (iterator.hasNext()) {
            int n = mQueue.take();
            System.out.println(n);
        }
    }
}