package test.java.internet.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author dengbin
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s = whenComplete();
        System.out.println("s:"+ s);
    }

    public static String whenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture asyncFuture = new CompletableFuture();
        System.out.println("1: " + Thread.currentThread());
        CompletableFuture.runAsync(() -> getResult(asyncFuture));
        System.out.println("3: " + Thread.currentThread());

        CompletableFuture<String> completableFuture = asyncFuture.whenComplete((str, ex) -> {
            System.out.println("4: " + Thread.currentThread());
            str = "s";
            System.out.println("result is " + str);
        });

//        System.out.println("5: " + Thread.currentThread());
//        System.out.println(Thread.currentThread() + "," + asyncFuture.get());
//        asyncFuture.join();

        return completableFuture.get();
    }

    private static String getResult(CompletableFuture asyncFuture) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2:" + Thread.currentThread());
        asyncFuture.complete("future result");
        return "1";
    }
}
