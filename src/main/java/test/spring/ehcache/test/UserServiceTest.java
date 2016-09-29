package test.spring.ehcache.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.ehcache.java.model.User;
import test.spring.ehcache.java.service.UserService;


public class UserServiceTest {

	UserService service;


	@Test
	public void testHibernateCache() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"hibernateCache.xml");
		service = (UserService) ctx.getBean("userService");
		User user = service.getUserById(1);
		System.out.println(user);
		System.out.println(service.getUserById(1));
		System.out.println(service.getUserById(2));
		System.out.println(service.getUserById(2));

	}

	@Test
	public void testSpringCache() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		service = (UserService) ctx.getBean("userService");
		User user = service.getUserById(1);
		System.out.println(user);
		System.out.println(service.getUserById(1));
		System.out.println(service.getUserById(2));
		System.out.println(service.getUserById(2));

	}
}
