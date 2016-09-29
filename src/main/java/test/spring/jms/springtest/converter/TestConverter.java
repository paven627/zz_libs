package test.spring.jms.springtest.converter;

import javax.jms.JMSException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.jms.springtest.converter.sender.QueueMsg;

public class TestConverter {

	@Test
	public void test() throws JMSException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		QueueMsg senderQueue = (QueueMsg) ctx.getBean("sender2");
		senderQueue.sendMsg();
		senderQueue.receirveMsg();
	}

}
