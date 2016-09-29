package test.spring.aop.spring.advice.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.aop.spring.advice.model.IBuyTV;

// 使用 Spring 2.5 的时候 使用 Junit 测试,必须使用Junit4.4 版本,否则会报错
//      org/junit/Assume$AssumptionViolatedException
public class TestTv {

	@Test
	public void buyTv() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"aop/spring/advice/beans.xml");
		IBuyTV tv = (IBuyTV) ctx.getBean("buyTv");
		try {
			tv.buyTv("dengbin", "TCL");
			tv.buyTv("dengbin", "LG");
			tv.buyTv("dengbin", "LG");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ProxyFactoryBean b = new ProxyFactoryBean();

		Logger log = Logger.getLogger("");
		Logger log2 = Logger.getLogger(getClass());
		log.debug("aaaaa");
		log2.debug("bbb");

	}

	public static void main(String[] args) throws Exception {
		TestTv t = new TestTv();
		t.buyTv();
	}
}
