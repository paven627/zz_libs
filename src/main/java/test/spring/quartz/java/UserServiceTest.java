package test.spring.quartz.java;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	// Spring 对 jdk Timer的支持
	@Test
	public void testTimer() throws Exception {
		new ClassPathXmlApplicationContext("/test/spring/quartz/timer.xml");
		while (true)
			;
	}

	@Test
	public void testQuartz() throws Exception {
		new ClassPathXmlApplicationContext("quartz.xml");
		while (true)
			;
	}
}
