package test.spring.jms.nospringtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsSender {
	public void send() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("testqueue");
		MessageProducer producer = session.createProducer(destination);
//		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		sendMsg(session, producer);
		session.close();
		connection.close();
	}

	private void sendMsg(Session session, MessageProducer producer)
			throws JMSException {

		// 创建一条文本消息
		for (int i = 0; i < 5; i++) {
			TextMessage message = session.createTextMessage("你好 ActiveMQ！" + i);
			producer.send(message);
		}

	}

}
