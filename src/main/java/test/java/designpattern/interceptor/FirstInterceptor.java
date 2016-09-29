package test.java.designpattern.interceptor;


public class FirstInterceptor implements IInterceptor{
	@Override
	public void intercept(ActionInvocation invocation) {
		System.out.println(1);
		invocation.invoke();
		System.out.println(-1);
	}
}
