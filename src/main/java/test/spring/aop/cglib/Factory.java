package test.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import test.spring.aop.aspectj.xml.model.Employee;

/**
 * CGLIB 创建一个目标类的子类,继承原本的类,在回调方法中实现额外的添加功能 但是 CGLIB 只能实现代理非 final 的类和方法
 * 适用于没有实现接口的像代理的类
 */
public class Factory {
	Object proxyTarget;

	public Object createProxy(Object proxyTarget) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(proxyTarget.getClass());
		enhancer.setCallback(new Interceptor(proxyTarget));
		return enhancer.create();
	}

	public static void main(String[] args) {
		Factory fa = new Factory();
		Employee emp = new Employee();
		emp.setEmpName("邓斌");
		Employee pro = (Employee) fa.createProxy(emp);
		
		pro.mmGame();
	}
}
