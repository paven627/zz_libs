package test.spring.jms.springtest.noconverter;

import javax.jms.Topic;

import org.springframework.jms.core.JmsTemplate;

public class TopicMsg {

	private JmsTemplate jmsTemplate;

	private Topic destination;

	public void setDestination(Topic destination) {
		this.destination = destination;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public Topic getDestination() {
		return destination;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}