package test.java.rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import test.java.rabbitmq.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Topics（主题通配符）
 * 可以理解为Routing的通配符模式， routing key 是以 "." 分隔的单词
 *
 * “#”：表示匹配一个或多个词；（lazy.a.b.c）
 * “*”：表示匹配一个词；（a.orange.b）
 *
 */
public class Producer {
     private static final String EXCHANGE_NAME = "test_exchange_topic";

     public static void main(String[] args) throws IOException, TimeoutException {
         Connection connection = ConnectionUtil.getConnection();
         Channel channel = connection.createChannel();
         //声明交换机
         channel.exchangeDeclare(EXCHANGE_NAME,"topic");
         String message = "消息";

         // 每个consumer发送两条消息,都只能收到一条,可证明routing key是根据 "." 分段匹配
         channel.basicPublish(EXCHANGE_NAME,"consumer1.aaa",false,false,null,message.getBytes());
         channel.basicPublish(EXCHANGE_NAME,"consumer1.aaa.bbb",false,false,null,message.getBytes());

         channel.basicPublish(EXCHANGE_NAME,"aaaa.consumer2",false,false,null,message.getBytes());
         channel.basicPublish(EXCHANGE_NAME,"aaa.consumer2.bbb",false,false,null,message.getBytes());

         channel.close();
         connection.close();
     }
 }