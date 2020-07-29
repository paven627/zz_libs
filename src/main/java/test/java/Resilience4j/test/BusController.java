package test.java.Resilience4j.test;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import test.java.Resilience4j.test.ratelimiter.RateLimiterService;

import javax.annotation.Resource;

@Controller
public class BusController {
    private Logger logger = LoggerFactory.getLogger(BusController.class);

    @Resource
    RedisService redisService;

    @Resource
    RateLimiterService rateLimiterService;

    // 熔断
    @RequestMapping(value = "/cb", method = RequestMethod.GET)
    @ResponseBody
    public String runCtr(@RequestParam boolean exception) {

//        return redisService.callRedis(exception);
        return redisService.test();
    }


    // 限流调用
    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    @ResponseBody
    public String callRatelimiter(@RequestParam boolean exception) {

        RateLimiter limit = rateLimiterService.get("qps");

        CheckedFunction0<String> supplier = RateLimiter.decorateCheckedSupplier(limit, () ->
                redisService.redisBus(exception)

        );
        Try<String> result = Try.of(supplier).onFailure(ex -> System.out.println(ex.getMessage()+
                ", service down !")).recover(ex -> "1");

//        if (result.isFailure() && result.getCause() instanceof RequestNotPermitted) {
//            return "rate limit";
//        }
        return result.get();

// Create a custom RateLimiter configuration
//        RateLimiterConfig config = RateLimiterConfig.custom()
//                .timeoutDuration(Duration.ofMillis(100))
//                .limitRefreshPeriod(Duration.ofSeconds(1))
//                .limitForPeriod(1)
//                .build();
//// Create a RateLimiter
//        RateLimiter rateLimiter = RateLimiter.of("backendName", config);
//
//// Decorate your call to BackendService.doSomething()
//        Supplier<String> restrictedSupplier = RateLimiter
//                .decorateSupplier(rateLimiter, backendService::doSomething);
//
//// First call is successful
//        Try<String> firstTry = Try.ofSupplier(restrictedSupplier);
//        assertThat(firstTry.isSuccess()).isTrue();
//
//// Second call fails, because the call was not permitted
//        Try<String> secondTry = Try.of(restrictedSupplier);
//        assertThat(secondTry.isFailure()).isTrue();
//        assertThat(secondTry.getCause()).isInstanceOf(RequestNotPermitted.class);
    }
}
