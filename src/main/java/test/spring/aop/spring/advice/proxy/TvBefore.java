package test.spring.aop.spring.advice.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 方法,前置通知,代理目标的每一个方法被调用之前都会调用本通知
 * 
 */
public class TvBefore implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// 方法的参数有两个,第一个为客户的名字,第二个为电视机的名字,这里取得代理方法的第一个参数
		System.out.println(args[0] + ",欢迎光临 !");
	}

}
