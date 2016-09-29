package test.spring.jms.springtest.noconverter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestJMS {
	public void sendMsg() throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		conn = cf.createConnection();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = new ActiveMQQueue("noSpringQueue");
		MessageProducer producer = session.createProducer(dest);
		TextMessage message = session.createTextMessage();
		message.setText("nihao");
		producer.send(message);
		session.close();
		conn.close();
	}

	public void receive() {
		ConnectionFactory fac = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		Connection conn = null;
		Session session = null;
		try {
			conn = fac.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = new ActiveMQQueue("noSpringQueue");
			MessageConsumer consumer = session.createConsumer(dest);
			conn.start();
			Message msg = consumer.receive();
			TextMessage text = (TextMessage) msg;
			System.out.println("收到消息");
			System.out.println(text.getText());

		} catch (Exception e) {

		} finally {

		}
	}

	@Test
	public void test() throws JMSException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// TopicMessageProducer senderTopic = (TopicMessageProducer) ctx
		// .getBean("senderQueue");
		QueueMsg senderQueue = (QueueMsg) ctx
				.getBean("senderQueue");

		senderQueue.sendMsg();
		// topicMessageProducer
		senderQueue.receirveMsg();
	}

}
