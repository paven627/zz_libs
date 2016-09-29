package test.spring.jms.nospringtest;

import javax.jms.JMSException;

/**
 *  先启动 ActiveMQ5.5,运行程序,
 * 通过  http://localhost:8161/admin/ 可以看到当前的消息队列和生产者消费者
 *
 */
public class Test {
	public static void main(String[] args) throws JMSException,
			InterruptedException {
		JmsSender sender = new JmsSender();
		JmsReceiver receiver = new JmsReceiver();

		// 先启动接收器,一直运行, 再启动发送器,发送消息后,接收器监听到消息打印出来
		sender.send();
		receiver.receive();
	}
}
