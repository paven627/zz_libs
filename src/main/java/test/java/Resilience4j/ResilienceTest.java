package test.java.Resilience4j;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

/**
 * @author dengbin
 */
public class ResilienceTest {
    public static void main(String[] args) {

        // Create a CircuitBreaker with default configuration
        CircuitBreaker circuitBreaker = CircuitBreaker
                .ofDefaults("backendService");

// Create a Retry with default configuration
// 3 retry attempts and a fixed time interval between retries of 500ms
        Retry retry = Retry
                .ofDefaults("backendService");

// Create a Bulkhead with default configuration
        Bulkhead bulkhead = Bulkhead
                .ofDefaults("backendService");

        Supplier<String> supplier = () -> doSomething();

// Decorate your call to backendService.doSomething()
// with a Bulkhead, CircuitBreaker and Retry
// **note: you will need the resilience4j-all dependency for this
        Supplier<String> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withCircuitBreaker(circuitBreaker)
                .withBulkhead(bulkhead)
                .withRetry(retry)
                .decorate();

// Execute the decorated supplier and recover from any exception
        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> "Hello from Recovery").get();

// When you don't want to decorate your lambda expression,
// but just execute it and protect the call by a CircuitBreaker.
//        String result = circuitBreaker
//                .executeSupplier(backendService::doSomething);

// You can also run the supplier asynchronously in a ThreadPoolBulkhead
        ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead
                .ofDefaults("backendService");

// The Scheduler is needed to schedule a timeout
// on a non-blocking CompletableFuture
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

        CompletableFuture<String> future = Decorators.ofSupplier(supplier)
                .withThreadPoolBulkhead(threadPoolBulkhead)
                .withTimeLimiter(timeLimiter, scheduledExecutorService)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(asList(TimeoutException.class,
                        CallNotPermittedException.class,
                        BulkheadFullException.class),
                        throwable -> "Hello from Recovery")
                .get().toCompletableFuture();
    }


    private static String doSomething() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }



}
