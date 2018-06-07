package test.java.concurrent.bank.lock;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SyncronizedObjectTest {
    static Map<Integer, Object> map = new ConcurrentHashMap<>();
    static Object o = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (map) {

                for (int i = 0; i < 3; i++) {
                    try {
                        System.out.println("写");
                        map.put(i, i);
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (map) {

                for (int i = 0; i < 3; i++) {
                    try {
                        System.out.println("读");
                        System.out.println(map);
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

    }
}
