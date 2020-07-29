package test.java.Resilience4j;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

/**
 * @author dengbin
 */
public class TimeLimiterTest {

    HelloService helloService = new HelloServiceImpl();


    @Test
    public void test1() throws Exception {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .cancelRunningFuture(true)
                .timeoutDuration(Duration.ofMillis(500))
                .build();

// Create a TimeLimiterRegistry with a custom global configuration
        TimeLimiterRegistry timeLimiterRegistry = TimeLimiterRegistry.of(config);

// Get or create a TimeLimiter from the registryTimeLimiter will be backed by the default config
//        使用默认配置创建或取得一个TimeLimiter
//        TimeLimiter timeLimiterWithDefaultConfig = timeLimiterRegistry.timeLimiter("name1");

// Get or create a TimeLimiter from the registry,
// use a custom configuration when creating the TimeLimiter
//        TimeLimiterConfig config = TimeLimiterConfig.custom()
//                .cancelRunningFuture(false)
//                .timeoutDuration(Duration.ofMillis(1000))
//                .build();

        TimeLimiter timeLimiterWithCustomConfig = timeLimiterRegistry.timeLimiter("name2", config);


        // Given I have a helloWorldService.sayHelloWorld() method which takes too long
//        HelloWorldService helloWorldService = mock(HelloWorldService.class);

// Create a TimeLimiter
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));
// The Scheduler is needed to schedule a timeout on a non-blocking CompletableFuture
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);


// The non-blocking variant with a CompletableFuture
        CompletableFuture<String> result = timeLimiter.executeCompletionStage(
                scheduler, () -> CompletableFuture.supplyAsync(helloService::hello)).toCompletableFuture();
        System.out.println(result.get());

// The blocking variant which is basically future.get(timeoutDuration, MILLISECONDS)
        String result1 = timeLimiter.executeFutureSupplier(
                () -> CompletableFuture.supplyAsync(helloService::hello));

        System.out.println(result1);
    }

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Test
    public void test2() throws Exception {
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

        Future<String> future = executorService.submit(helloService::hello);

        Duration timeoutDuration = Duration.ofSeconds(1);
//        Future<Integer> mockFuture = (Future<Integer>) mock(Future.class);
        Supplier<Future<String>> supplier = () -> future;

        String s = timeLimiter.executeFutureSupplier(supplier);
        System.out.println(s);
    }

    public static void main(String[] args) {
        Supplier<HelloService> s = () -> new HelloServiceImpl();
        System.out.println(s.get().hello());
    }
}