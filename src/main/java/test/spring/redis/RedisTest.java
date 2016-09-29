package test.spring.redis;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public class RedisTest {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"/test/spring/redis/applicationContext.xml");

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Before
	public void before() {
		userDao = (IUserDao) ctx.getBean("userDao");
	}

	@Test
	public void test1() {
		System.out.println(1);
	}

	@Test
	public void test() {
		userDao.add(new User(1, "人1"));
		userDao.add(new User(2, "人2"));
	}

	@Test
	public void get() {
		User user = userDao.get(1);
		User user2 = userDao.get(2);
		System.out.println(user);
		System.out.println(user2);
	}
}
