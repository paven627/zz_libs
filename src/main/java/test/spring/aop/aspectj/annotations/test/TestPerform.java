package test.spring.aop.aspectj.annotations.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import test.spring.aop.aspectj.annotations.Performer;



@ContextConfiguration(locations="/aop/aspectj/annotations/beans.xml")
public class TestPerform extends AbstractJUnit4SpringContextTests{
	
	@Resource(name="actor")
	Performer audi = null;
	
	
	@Test
	public void testWork() throws Exception {
		audi.perform(12);
	}
}
