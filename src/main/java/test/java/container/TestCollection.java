package test.java.container;

import java.util.HashSet;
import java.util.Set;

public class TestCollection {
   public static Set<Integer> list = new HashSet() {
        {
            add(1);
            add(2);
            add(3);
            add(4);
        }
    };


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            T t = new T();
            new Thread(t).start();
        }
    }

}

class T implements Runnable {

    @Override
    public void run() {
        boolean contains = TestCollection.list.contains(1);
        if (!contains) {
            System.out.println(contains);
        }

    }
}
