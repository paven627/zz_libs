package test.spring.jms.springtest.noconverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

// 运行
public class QueueMsg {

	private JmsTemplate jmsTemplate;

	// 监听的目标队列,可以在 在spring配置文件中为jmsTemplate中配置一个默认目标
	private Queue destination;

	// 本示例监听的队列的名称,如果不为jmsTemplate中配置默认目标,可以指定发送和接收监听的队列为此名称
	private String queueName = "test.queue";

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public Queue getDestination() {
		return destination;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Queue destination) {
		this.destination = destination;
	}

	// 发送3条消息,接收方每隔一秒读取一条,可以看到运行效果是一次发送了3条,而每隔1秒才收到一条消息
	public void sendMsg() {
		for (int i = 0; i < 3; i++) {
			jmsTemplate.send(new MessageCreator() {
				@Override
				public Message createMessage(Session session)
						throws JMSException {
					MapMessage msg = (MapMessage) session.createMapMessage();
					msg.setInt("id", 1);
					msg.setStringProperty("name", "罗莎");
					return msg;
				}
			});
			System.out.println("发送一条消息完毕");
		}
	}

	public void receirveMsg() throws JMSException {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 1 接收 jmstemplate在spring中配置的默认目标
			Message msg = jmsTemplate.receive();

			// 2 如果没有默认目标,也可以指定监听目标
			// Message msg = jmsTemplate.receive(destination);
			if (msg != null) {
				String name = msg.getStringProperty("name");
				String rosa = msg.getStringProperty("name2");
				System.out.println("名字:" + name);
				System.out.println("name2:" + rosa);
			}
		}
	}
}