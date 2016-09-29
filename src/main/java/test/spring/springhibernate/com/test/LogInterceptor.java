package test.spring.springhibernate.com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



public class LogInterceptor implements InvocationHandler{
	
	private Object obj;
	
	// ÿһ������ִ��ǰ,��ִ�д˷���
	public void beforeMethod(){
		System.out.println("������ķ��� start.....");
	}

	
	public LogInterceptor(Object obj) {
		super();
		this.obj = obj;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("���÷���: "+method.getName());
		beforeMethod();
		return method.invoke(obj, args);
	}
	
	
}
