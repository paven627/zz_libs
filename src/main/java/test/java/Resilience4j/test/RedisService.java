package test.java.Resilience4j.test;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test.java.Resilience4j.test.circuitbreak.CircuitBreakService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    Logger logger = LoggerFactory.getLogger(RedisService.class);


    @Resource
    CircuitBreakService breakerSevice;

    public RedisService() {
    }


    @PostConstruct
    public void init() {
    }

//    public String callRedis(boolean exception) {
//        CircuitBreaker breaker = breakerSevice.get("redis");
//        Try<String>
//                result = Try.of(CircuitBreaker.decorateCheckedSupplier(breaker, () -> redisBus(exception)
//        ))
////                .recover(throwable -> {
////                    throwable.printStackTrace();
////
////                    return "Recovery";
////                })
//                ;
//
//        logger.info("Sucess: " + result.isSuccess() + " , State: " + breaker.getState());
//        CircuitBreaker.Metrics metrics = breaker.getMetrics();
//        printMetrics(metrics);
//        return result.get();
//
//    }

    public String redisBus(boolean exception) {
//        try {
        if (exception) {
            throw new RuntimeException("my exception");
        } else {
            logger.info("call redis");
        }
//        } catch (RuntimeException e) {
//            logger.error(e.getMessage());
//        }
        return "redis ok";
    }

    public String test() {
        CircuitBreaker circuitBreaker = breakerSevice.get("redis_data");

        //第一次错误
        circuitBreaker.onError(0, TimeUnit.MILLISECONDS, new RuntimeException());
        logger.info("第一次错误后:" + circuitBreaker.getState());

        //增加reset,会重新计数, 不引发失败
//        circuitBreaker.reset();

        //第二次错误
        circuitBreaker.onError(0, TimeUnit.MILLISECONDS, new RuntimeException());
        logger.info("第二次错误后:" + circuitBreaker.getState());
        Try<String>
                result = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> 2/0 +""))
                .onFailure(Exception.class , e -> System.out.println("failure:"+ e.getMessage() ))
                .recover(exception -> {
                    System.out.println(" recover:"+ exception.getMessage());
                    return " recover ";
                });
//                .map(value -> value + " world");

        logger.info("success:" + result.isSuccess());

        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        String format = String.format("failed: %s, NotPermittedCalls: %s, success: %s" +
                        "failureRate: %s, slowCallRate: %s",
                metrics.getNumberOfFailedCalls(),
                metrics.getNumberOfNotPermittedCalls(), metrics.getNumberOfSuccessfulCalls(),
                metrics.getFailureRate(), metrics.getSlowCallRate());
        logger.info(format);


        String x = result.get();
        logger.info(x);
        return x;
    }

    private void printMetrics(CircuitBreaker.Metrics metrics) {
        String format = String.format("failed: %s, NotPermittedCalls: %s, success: %s" +
                        "failureRate: %s, slowCallRate: %s",
                metrics.getNumberOfFailedCalls(),
                metrics.getNumberOfNotPermittedCalls(), metrics.getNumberOfSuccessfulCalls(),
                metrics.getFailureRate(), metrics.getSlowCallRate());
        logger.info(format);
    }


//    public String circuitBreakerNotAOP(){
//        // 通过注册器获取熔断器的实例
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
//        // 使用熔断器包装连接器的方法
//        CheckedFunction0<String> checkedSupplier = CircuitBreaker.
//                decorateCheckedSupplier(circuitBreaker, remoteServiceConnector::process);
//        // 使用Try.of().recover()调用并进行降级处理
//        Try<String> result = Try.of(checkedSupplier).
//                recover(CallNotPermittedException.class, throwable -> {
//                    logger.info("熔断器已经打开，拒绝访问被保护方法~");
//                    return "";
//                })
//                .recover(throwable -> {
//                    logger.info(throwable.getLocalizedMessage() + ",方法被降级了");
//                    return "";
//                });
//        return result.get();
//    }
}
