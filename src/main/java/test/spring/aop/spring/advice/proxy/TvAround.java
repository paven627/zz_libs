package test.spring.aop.spring.advice.proxy;

import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;



/**
 * 每一个方法当中都会执行本通知
 *
 */
public class TvAround implements MethodInterceptor{
	private Set<String> customers = new HashSet<String>();
	
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		//取得方法的参数
		String customerName =  method.getArguments()[0].toString();
		String tvName = method.getArguments()[1].toString();
		
		if(customers.contains(customerName)){
			throw new Exception("对不起,一名顾客只能购买一台特价电视 "+tvName);
		}
		//调用buyTv中的butTv()方法
		Object result = method.proceed();
		customers.add(customerName);
		
		return result;
	}
	
}
