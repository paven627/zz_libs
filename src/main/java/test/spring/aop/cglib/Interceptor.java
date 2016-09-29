package test.spring.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 实现 cglib 接口,方法的回调函数,拦截到代理目标的方法后要做的事
 */
public class Interceptor implements MethodInterceptor {
	Object target;

	public Interceptor(Object proxyTarget) {
		this.target = proxyTarget;
	}

	/**
	 * obj 代理的对象 method 拦截到的真实的方法 args 方法的参数 proxy 代理到的方法
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("带套");
		method.invoke(target, args);
		System.out.println("洗澡");
		return null;
	}

}
