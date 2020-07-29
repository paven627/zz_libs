package test.java.Resilience4j.test.ratelimiter;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Component
public class RateLimiterService {
    Logger logger = LoggerFactory.getLogger(RateLimiterService.class);

    private RateLimiterConfig ratelimiterConfig;
    private static RateLimiterRegistry rateLimiterRegistry;

    @PostConstruct
    private void init() {
        ratelimiterConfig = RateLimiterConfig.custom()
                .limitForPeriod(3)     // 一个刷新时间内的请求数
                .limitRefreshPeriod(Duration.ofSeconds(6))     // 请求许可数刷新的周期
                // 线程等待请求许可的时间
                .timeoutDuration(Duration.ofSeconds(2))
                .build();

//        Map<String, RateLimiterConfig> configs = new HashMap<>();
//        configs.put("redis", ratelimiterConfig);

        RateLimiterService.rateLimiterRegistry =
                RateLimiterRegistry.custom().withRateLimiterConfig(ratelimiterConfig).build();

        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("qps");
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("redis", "redis");

        RateLimiter.Metrics metrics = rateLimiter.getMetrics();
        rateLimiter.getEventPublisher()
//                .onSuccess(event -> {
//                    String format = String.format("eventType: %s, waitingThread: %s,  availablePermissions: " +
//                                    "%s ",
//                            event.getEventType(), metrics.getNumberOfWaitingThreads(),
//                            metrics.getAvailablePermissions());
//                    logger.info(format);
//                })
//                .onFailure(event -> {
//                    String format = String.format("eventType: %s, waitingThread: %s,  availablePermissions: %s ",
//                            event.getEventType(),
//                            metrics.getNumberOfWaitingThreads(),
//                            metrics.getAvailablePermissions());
//                    logger.info(format);
//                })
                .onEvent(event -> {
                    String format = String.format("eventType: %s, limiterName: %s, numberOfPermits: %s, 可用数:%s,等待线程:%s",
                            event.getEventType(),
                            event.getRateLimiterName(),
                            event.getNumberOfPermits(),
                            metrics.getAvailablePermissions(), metrics.getNumberOfWaitingThreads()
                    );

                    logger.info(format);
//                    metrics.getAvailablePermissions()
                });







    }

    public static RateLimiter get(String name) {
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(name);
        return rateLimiter;
    }
}