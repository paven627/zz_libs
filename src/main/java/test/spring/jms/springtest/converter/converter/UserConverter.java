package test.spring.jms.springtest.converter.converter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import test.spring.jms.springtest.converter.model.User;

/**
 * 对象与消息的转换器, 实现spring提供接口, spring 发送消息前调用 toMessage, 接收到消息后调用 fromMessage
 */
public class UserConverter implements MessageConverter {

	// 消息转自定义对象
	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		if (message instanceof MapMessage) {
			MapMessage msg = (MapMessage) message;
			User user = new User();
			user.setId(msg.getInt("id"));
			user.setName(msg.getString("name"));
			return user;
		}
		return null;
	}

	// 自定义对象转为消息
	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		User user = (User) object;
		MapMessage msg = session.createMapMessage();
		msg.setInt("id", user.getId());
		msg.setString("name", user.getName());
		return msg;
	}

}
