package test.java.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * 动态
 * 
 */
public class ConnectionProxy implements InvocationHandler {
	private Connection realConnection;

	public ConnectionProxy(Connection realConnection) {
		super();
		this.realConnection = realConnection;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("调用方法:" + method.getName());
		if (method.getName().equals("close")) {
			Connection invoked = (Connection) proxy;
			Pool.close(invoked);
			return null;
		} else {
			// 调用的属性的方法,不是传来参数的方法
			Object result = method.invoke(realConnection, args);
			return result;
		}

	}

}
