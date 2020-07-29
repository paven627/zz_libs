package test.java.Resilience4j.test.circuitbreak;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class CircuitBreakService {
    Logger logger = LoggerFactory.getLogger(CircuitBreakService.class);

    //    @Value("${circuitbreak.windowsize}")
    private int windowSize = 2;

    //    @Value("${circuitbreak.callnum}")
    private int callnum = 2;

    //    @Value("${circuitbreak.openduration}")
    private int openduration = 10000;

    //    @Value("${circuitbreak.failurerate}")
    private float failurerate = 10;

    private CircuitBreakerConfig circuitBreakerConfig;
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    private void init() {
        circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(failurerate) // 故障率默认50
                .slidingWindow(windowSize, callnum, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofMillis(openduration))
//                .permittedNumberOfCallsInHalfOpenState()
                .build();


//        io.vavr.collection.Map<String, String> retryTags = io.vavr.collection.HashMap
//                .of("redis", "value1");



        Map<String, CircuitBreakerConfig> configs = new HashMap<>();
        configs.put("redis", circuitBreakerConfig);

        circuitBreakerRegistry = CircuitBreakerRegistry.of(configs);
        CircuitBreaker breaker = circuitBreakerRegistry.circuitBreaker("redis_data", "redis");
        breaker.getEventPublisher().onSuccess(event -> {
                    CircuitBreaker.Metrics metrics = breaker.getMetrics();
                    String format = String.format("on Success: type: %s, failed: %s, NotPermittedCalls: %s, success: " +
                                    "%s" +
                                    "failureRate: %s, slowCallRate: %s",
                            event.getEventType(), metrics.getNumberOfFailedCalls(),
                            metrics.getNumberOfNotPermittedCalls(), metrics.getNumberOfSuccessfulCalls(),
                            metrics.getFailureRate(), metrics.getSlowCallRate());
                    logger.info(format);
                }
        )
//                .onError(event -> {
//                    CircuitBreaker.Metrics metrics = breaker.getMetrics();
//                    String format = String.format("on Error: type: %s, failed: %s, NotPermittedCalls: %s, success:
//                    %s" +
//                                    "failureRate: %s, slowCallRate: %s",
//                            event.getEventType(), metrics.getNumberOfFailedCalls(),
//                            metrics.getNumberOfNotPermittedCalls(), metrics.getNumberOfSuccessfulCalls(),
//                            metrics.getFailureRate(), metrics.getSlowCallRate());
//                    logger.info(format);
//                })
                .onStateTransition(event -> {
                            String format = String.format("eventType: %s, State: %s", event.getEventType(),
                                    event.getStateTransition());
                            logger.info(format);
                        }
                )
                .onEvent(event -> {
                    logger.info(event.getEventType().toString());
                });
    }

    public CircuitBreaker get(String name) {
        CircuitBreaker breaker = circuitBreakerRegistry.circuitBreaker(name);
        return breaker;
    }
}