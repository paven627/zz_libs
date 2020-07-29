package test.java.Resilience4j;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * 限流
 */
public class RateLimterTest {


    /**
     * 限制qps = 2
     */
    @Test
    public void test1() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1000))  // 阈值刷新时间
                .limitForPeriod(2)    // 频次
                .timeoutDuration(Duration.ofMillis(100))  //The default wait time a thread waits for a permission
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
// 注册中心创建
        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("backend");
        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("backend#2", config);
//        直接创建
//        RateLimiter rateLimiter = RateLimiter.of("NASDAQ :-)", config);


        rateLimiterRegistry.getEventPublisher()
                .onEntryAdded(entryAddedEvent -> {
                    RateLimiter addedRateLimiter = entryAddedEvent.getAddedEntry();
                    System.out.println("333 :" + entryAddedEvent.getEventType());
//                    System.out.println("RateLimiter {} added", addedRateLimiter.getName());
                })
                .onEntryRemoved(entryRemovedEvent -> {
                    RateLimiter removedRateLimiter = entryRemovedEvent.getRemovedEntry();
//                    LOG.info("RateLimiter {} removed", removedRateLimiter.getName());
                    System.out.println(entryRemovedEvent.getEventType());
                });


        CheckedRunnable restrictedCall = RateLimiter
                .decorateCheckedRunnable(rateLimiterWithCustomConfig, () -> {
                    System.out.println(new Date());
                });

        //修改参数
//        rateLimiter.changeLimitForPeriod(100);
//        rateLimiter.changeTimeoutDuration(Duration.ofMillis(100));
        RateLimiter.Metrics metrics = rateLimiterWithCustomConfig.getMetrics();

        //
        rateLimiterWithCustomConfig.getEventPublisher().onSuccess(event -> {
            System.out.println(event.getEventType() + ":::可用令牌数: " + metrics.getAvailablePermissions() + ", 等待线程数: "
                    + metrics.getNumberOfWaitingThreads());
        }).onFailure(event -> {
            System.out.println(event.getEventType() + ":::可用令牌数: " + metrics.getAvailablePermissions() + ", 等待线程数: "
                    + metrics.getNumberOfWaitingThreads());
        });

        rateLimiterWithCustomConfig.getEventPublisher()
                .onSuccess(event -> {
                    System.out.println("111 >>>" + event.getEventType() + ">>>" + event.getCreationTime());
                })
                .onFailure(event -> {
                    System.out.println("222 >>>" + event.getEventType() + ">>>" + event.getCreationTime());
                });

        Try.run(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));


        int numberOfThreadsWaitingForPermission = metrics.getNumberOfWaitingThreads();
        int availablePermissions = metrics.getAvailablePermissions();
        System.out.println(numberOfThreadsWaitingForPermission);
        System.out.println(availablePermissions);

        Try.run(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));


//        AtomicRateLimiter atomicLimiter = new AtomicRateLimiter("a",config);
//        long nanosToWaitForPermission = atomicLimiter.getNanosToWait();
//        System.out.println();
    }


    @Test
    public void test2() {
// Create a custom RateLimiter configuration
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(100))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1)
                .build();
        // Create a RateLimiter
        RateLimiter rateLimiter = RateLimiter.of("backendName", config);

        // Decorate your call to BackendService.doSomething()
        Supplier<String> restrictedSupplier = RateLimiter
                .decorateSupplier(rateLimiter, Resilience4jTest::doSomethingSlowly);

        // runnable 装饰器
        Runnable runnable = RateLimiter.decorateRunnable(rateLimiter, () -> {
                    Resilience4jTest.doSomething();
                }
        );
        rateLimiter.executeRunnable(Resilience4jTest::doSomethingSlowly);

//        IntStream.rangeClosed(1, 5)
////                .parallel()
//                .forEach(i -> {
//                    Try<String> aTry = Try.ofSupplier(restrictedSupplier);
//
//                    System.out.println(aTry.isSuccess());
//                });
    }

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);
    }
}
