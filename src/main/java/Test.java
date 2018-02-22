import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10, false), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= 5; i++) {
            Task command = new Task();
            threadPool.execute(command);
        }

    }

}

class Task implements Runnable {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("11111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}