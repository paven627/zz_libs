package test.spring.aop.aspectj.xml.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.aop.aspectj.xml.model.Employee;


public class TestWork {

	@Test
	public void testWork() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"aop/aspectj/xml/beans.xml");
		Employee emp = (Employee) ctx.getBean("employee");
		emp.qqGame();

	}

	public static void main(String[] args) {
		TestWork t = new TestWork();
		t.testWork();
	}

}
