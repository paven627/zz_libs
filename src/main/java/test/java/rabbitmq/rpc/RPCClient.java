package test.java.rabbitmq.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import test.java.rabbitmq.ConnectionUtil;

import java.io.IOException;
import java.util.UUID;
 import java.util.concurrent.ArrayBlockingQueue;
 import java.util.concurrent.BlockingQueue;
 import java.util.concurrent.TimeoutException;

 public class RPCClient {

     private Connection connection;
     private Channel channel;
     private String requestQueueName = "rpc_queue";
     private String replyQueueName;

     public RPCClient() throws IOException, TimeoutException {
         connection = ConnectionUtil.getConnection();
         channel = connection.createChannel();

         replyQueueName = channel.queueDeclare().getQueue();
     }

     public String call(String message) throws IOException, InterruptedException {
         final String corrId = UUID.randomUUID().toString();

         AMQP.BasicProperties props = new AMQP.BasicProperties
                 .Builder()
                 .correlationId(corrId)
                 .replyTo(replyQueueName)
                 .build();

         channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

         final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

         channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
             @Override
             public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                 if (properties.getCorrelationId().equals(corrId)) {
                     response.offer(new String(body, "UTF-8"));
                 }
             }
         });

         //close();
         return response.take();
     }

     public void close() throws IOException {
         connection.close();
     }

 }