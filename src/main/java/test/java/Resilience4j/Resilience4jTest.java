package test.java.Resilience4j;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author dengbin
 */
public class Resilience4jTest implements  HelloService {
    Resilience4jTest backendService;


    public static String doSomething() {
        try {
            System.out.println("doing something. .......");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public static String doSomethingSlowly() {
        try {
            System.out.println("doSomethingSlowly .......");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    private static String doSomethingThrowException() throws InterruptedException {
        System.out.println("doSomethingThrowException......");
        Thread.sleep(200);
        return "ok";
    }


    /**
     * 熔断器
     * 主要是实现针对接口异常的断路统计以及断路处理。
     */
    @Test
    public void testCircuitBreaker() {
        // Create a CircuitBreaker (use default configuration)

        //  默认参数:
        //  .failureRateThreshold(50)
        //    .waitDurationInOpenState(Duration.ofMillis(1000))
        //    .ringBufferSizeInHalfOpenState(2)
        //    .ringBufferSizeInClosedState(2)
        //    .build();

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
                .custom().failureRateThreshold(1)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker
                .of("backendName", circuitBreakerConfig);
        String result = circuitBreaker.executeSupplier(() -> doSomething());
        System.out.println(result);
        circuitBreaker.reset();
    }


    /**
     * Timelimiter
     * 主要是实现超时的控制。
     */
    @Test
    public void testTimelimiter() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(600))
                .cancelRunningFuture(true)
                .build();
        TimeLimiter timeLimiter = TimeLimiter.of(config);

        Supplier<Future<String>> futureSupplier = () -> {
            return executorService.submit(Resilience4jTest::doSomething);
        };
        Callable<String> restrictedCall = TimeLimiter.decorateFutureSupplier(timeLimiter, futureSupplier);
        Try.of(restrictedCall::call)
                .onFailure(throwable -> System.out.println("We might have timed out or the circuit breaker has opened" +
                        "."));
    }

    /**
     * Bulkhead
     * Bulkhead目前来看是用来控制并行(parallel)调用的次数。
     */
    @Test
    public void testBulkhead() {
        Bulkhead bulkhead = Bulkhead.of("test", BulkheadConfig.custom()
                .maxConcurrentCalls(1)
                .build());
        Supplier<String> decoratedSupplier = Bulkhead.decorateSupplier(bulkhead, Resilience4jTest::doSomethingSlowly);
        IntStream.rangeClosed(1, 2)
                .parallel()
                .forEach(i -> {
                    String result = Try.ofSupplier(decoratedSupplier)
                            .recover(throwable -> "Hello from Recovery").get();
                    System.out.println(result);
                });
    }

    /**
     * RateLimiter
     * 用来做流控。
     */
    @Test
    public void testRateLimiter() {
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
                .decorateSupplier(rateLimiter, Resilience4jTest::doSomething);

        IntStream.rangeClosed(1, 5)
                .parallel()
                .forEach(i -> {
                    Try<String> aTry = Try.ofSupplier(restrictedSupplier);
                    System.out.println(aTry.isSuccess());
                });
    }

    /**
     * Fallback
     * fallback基本上是高可用操作的标配。
     */
//    @Test
//    public void testFallback() {
//        // Execute the decorated supplier and recover from any exception
//        String result = Try.ofSupplier(() -> Resilience4jTest.doSomethingThrowException())
//                .recover(throwable -> "Hello from Recovery").get();
//        System.out.println(result);
//    }


//    @Test
//    public void testCircuitBreakerAndFallback() {
//        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
//        Supplier<String> decoratedSupplier = CircuitBreaker
//                .decorateSupplier(circuitBreaker, Resilience4jTest::doSomethingThrowException);
//        String result = Try.ofSupplier(decoratedSupplier)
//                .recover(throwable -> "Hello from Recovery").get();
//        System.out.println(result);
//    }

    /**
     * Retry
     * retry用来控制重试。
     */
    @Test
    public void testRetry() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendName");
        // Create a Retry with at most 3 retries and a fixed time interval between retries of 500ms
        Retry retry = Retry.ofDefaults("backendName");

        // Decorate your call to BackendService.doSomething() with a CircuitBreaker
        Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, Resilience4jTest::doSomething);

        // Decorate your call with automatic retry
        decoratedSupplier = Retry
                .decorateSupplier(retry, decoratedSupplier);

        // Execute the decorated supplier and recover from any exception
        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery").get();
        System.out.println(result);
    }

    @Override
    public String hello() {
        return "hello";
    }
}
