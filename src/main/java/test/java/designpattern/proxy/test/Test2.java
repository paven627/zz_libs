package test.java.designpattern.proxy.test;

public class Test2 {
	public static void main(String[] args) {
		Proxy p = new Proxy();
		p.f();
		p.g();
	}
}

interface ProxyBase{
	void f();
	void g();
}
class Proxy implements ProxyBase{
	private ProxyBase impl;
	public Proxy(){
		impl = new Implementation();
	}
	@Override
	public void f() {
		impl.f();
	}
	@Override
	public void g() {
		impl.g();
	}
}
class Implementation implements ProxyBase{
	@Override
	public void f() {
		System.out.println("Implementation.f");
	}
	@Override
	public void g() {
		System.out.println("Implementation.g");
	}
}
