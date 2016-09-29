package test.spring.aop.spring.advice.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 后置通知 ,方法成功返回之后执行
 * 
 */
public class TvAfter implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		System.out.println("target :" + target);
		// 第三个参数由被通知的方法传入的参数,即customer和tv两个参数
		System.out.println(args[0] + ",欢迎下次再来");
	}

}
