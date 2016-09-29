package test.spring.jms.nospringtest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsReceiver {

	public void receive() throws JMSException, InterruptedException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		// 需要在activemq的控制台中创建queue：testqueue
		Destination destination = session.createQueue("testqueue");
		MessageConsumer consumer = session.createConsumer(destination);

		while (true) {
			TextMessage message = (TextMessage) consumer.receive(100);
//			System.out.println(1);	// 会不停打印
			if (null != message) {
				System.out.println("收到消息：" + message.getText());
//				Thread.sleep(1000);
//			}else {
//				break;
			}

		}
//		 session.close();
//		 connection.close();
	}
}
