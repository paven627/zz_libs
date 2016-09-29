package test.java.designpattern.proxy.test;

public class Test {
	public static void main(String[] args) {
		ProxySubject sub = new ProxySubject();
		sub.request();
	}
}

abstract class Subject {
	abstract public void request();
}
class RealSubject extends Subject{
	@Override
	public void request() {
		System.out.println("真正的请求");
	}
}
//代理类
class ProxySubject extends Subject{
	private RealSubject realSubject;
	
	//实现请求方法
	@Override
	public void request() {
		preRequest();
		if(realSubject == null)
			realSubject = new RealSubject();
		realSubject.request();
		postRequest();
	}
	
	//请求之后的操作
	private void postRequest() {
		System.out.println("真正的请求之后...");
	}
	//请求之前的操作
	private void preRequest() {
		System.out.println("真正的请求之前....");
	}
	
}