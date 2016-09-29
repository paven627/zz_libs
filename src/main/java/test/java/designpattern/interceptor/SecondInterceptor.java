package test.java.designpattern.interceptor;


public class SecondInterceptor implements IInterceptor{
	@Override
	public void intercept(ActionInvocation invocation) {
		System.out.println(2);
		invocation.invoke();
		System.out.println(-2);
	}
}
