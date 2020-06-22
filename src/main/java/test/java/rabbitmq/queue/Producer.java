package test.java.rabbitmq.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import test.java.rabbitmq.ConnectionUtil;

/**
 * @program: rabbit-learn
 * @description: 生产者
 * @create: 2018-04-26 16:18
 **/
public class Producer {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 0; i < 50; i++) {
            String message = "" + i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            Thread.sleep(100 * i);
        }
        channel.close();
        connection.close();
    }

}