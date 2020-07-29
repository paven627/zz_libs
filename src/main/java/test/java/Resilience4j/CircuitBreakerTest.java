package test.java.Resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 *
 * 熔断
 */
public class CircuitBreakerTest {

    /**
     * 熔断器采用装饰器模式，你可以使用CircuitBreaker.decorateCheckedSupplier() / CircuitBreaker.decorateCheckedRunnable() /
     * CircuitBreaker.decorateCheckedFunction() 装饰 Supplier / Runnable / Function / CheckedRunnable / CheckedFunction，
     * 然后使用Vavr的Try.of(…​) / Try.run(…​) 调用被装饰的函数，也可以使用map / flatMap / filter / recover / andThen链接更多的函数。
     * 函数链只有在熔断器处于关闭或半开状态时才可以被调用。
     */
    @Test
    public void test1() {
        // 创建熔断器
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");

// 用熔断器包装函数
//        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
//                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");

// 链接其它的函数
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");

        System.out.println(result);
// 如果函数链中的所有函数均调用成功，最终结果为Success<String>

        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(result.get().equals("This can be any method which returns: 'Hello world'"));
    }


    /**
     * 函数链中可以包含被不同熔断器包装的多个函数：
     */
    @Test
    public void test2() {
// 两个熔断器
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CircuitBreaker anotherCircuitBreaker = CircuitBreaker.ofDefaults("anotherTestName");

// 用两个熔断器分别包装Supplier 和 Function
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "Hello");

        CheckedFunction1<String, String> decoratedFunction = CircuitBreaker
                .decorateCheckedFunction(anotherCircuitBreaker, (input) -> input + " world");

// 链接函数
        Try<String> result = Try.of(decoratedSupplier)
                .mapTry(decoratedFunction::apply);

        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(result.get().equals("Hello world"));
    }


    /**
     * 默认失败率50打开熔断器
     * 熔断器打开
     * 这里手动模拟错误，首先设置了断路器关闭状态下的环形缓冲区大小为 2 ，即当有两条数据时就可以去统计故障率了，这里没有设置故障率，默认的故障率是 50% ，当第一次调用 onError
     * 方法后，打印断路器当前状态，发现断路器还是处于关闭状态，并未打开，接下来再次调用 onError 方法，然后再去查看断路器状态，此时发现断路器已经打开了，因为满足了 50% 的故障率了。
     * <p>
     *  <p>
     * 配置滑动窗口，该窗口用于在CircuitBreaker关闭时记录呼叫结果。
     * slideWindowSize配置滑动窗口的大小。滑动窗口可以是基于计数或基于时间的，由slideWindowType指定。
     * minimumNumberOfCalls配置在CircuitBreaker可以计算错误率之前（每个滑动窗口时段）所需的最小呼叫数。
     * 例如，如果minimumNumberOfCalls为10，则在计算失败率之前，必须至少记录10个呼叫。如果仅记录了9个呼叫，则即使所有9个呼叫均失败，CircuitBreaker也不会转换为打开。
     * 如果slideWindowSize是100，而slideWindowType的是COUNT_BASED，则记录并汇总最近的100个调用。
     * 如果slidingWindowSize为10，slidingWindowType为TIME_BASED，则将记录并汇总最近10秒的调用。
     * slideingWindowSize必须大于0。minimumNumberOfCalls必须大于0。
     * <p><p>
     * 如果slideingWindowType为COUNT_BASED，minimumNumberOfCalls不得大于slideingWindowSize。如果提供更大的值，minimumNumberOfCalls
     * 将等于slideWindowSize。
     * 如果slideWindow类型为TIME_BASED，则minimumNumberOfCalls可以为任意数量。
     * 默认的slippingWindowSize为100，minimumNumberOfCalls为100，slidingWindowType为COUNT_BASED。
     * <p><p>
     * 这里手动模拟错误，首先设置了断路器关闭状态下的环形缓冲区大小为 2 ，即当有两条数据时就可以去统计故障率了，这里没有设置故障率，默认的故障率是 50% ，当第一次调用 onError
     * 方法后，打印断路器当前状态，发现断路器还是处于关闭状态，并未打开，接下来再次调用 onError 方法，然后再去查看断路器状态，此时发现断路器已经打开了，因为满足了 50% 的故障率了。
     */
    @Test
    public void test3() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(60f) // 故障率默认50 , 改为60时, 调用两次
//                .ringBufferSizeInClosedState(2)
                .slidingWindow(2, 2, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofMillis(2000))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        //第一次错误
        circuitBreaker.onError(0, TimeUnit.MILLISECONDS, new RuntimeException());
        System.out.println("第一次错误后:" + circuitBreaker.getState());

        //增加reset,会重新计数, 不引发失败
//        circuitBreaker.reset();

        //第二次错误
        circuitBreaker.onError(0, TimeUnit.MILLISECONDS, new RuntimeException());
        System.out.println("第二次错误后:" + circuitBreaker.getState());
        Try<String>
                result = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello"))
                .map(value -> value + " world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    /**
     * 如果需要使用服务降级，可以使用 Try.recover() 链接，当 Try.of() 返回 Failure 时服务降级会被触发。
     *
     * Metrics 状态监听, 获取运行数据
     */
    @Test
    public void recover() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> checkedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
            throw new RuntimeException("BAM!");
        });
        Try<String> result = Try.of(checkedSupplier)
                .recover(throwable -> {
                    throwable.printStackTrace();
                    return "Hello Recovery";
                });
        System.out.println(result.isSuccess());
        System.out.println(result.get());

        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
// 获取故障率
        float failureRate = metrics.getFailureRate();
// 获取调用失败次数
        int failedCalls = metrics.getNumberOfFailedCalls();

        System.out.println(failedCalls);
        System.out.println(failureRate);
    }

}
