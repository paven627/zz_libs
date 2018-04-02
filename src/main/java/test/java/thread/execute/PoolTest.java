
package test.java.thread.execute;

import java.util.concurrent.*;

public class PoolTest {
//   static  ThreadPoolExecutor exec =  new ThreadPoolExecutor(coreSize*2+1, 200,
//            1, TimeUnit.MINUTES,
//            new ArrayBlockingQueue<Runnable>(20),new ThreadFactory() {
//
//
//        @Override
//        public Thread newThread(Runnable r) {
//            return null;
//        }
//    });

    static ThreadPoolExecutor exec = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1,
            20, 1l, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        }
    }
    );

    public static void main(String[] args) {
    }
}




