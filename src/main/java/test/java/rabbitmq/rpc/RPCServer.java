package test.java.rabbitmq.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import test.java.rabbitmq.ConnectionUtil;

import java.io.IOException;
 import java.util.concurrent.TimeoutException;

 public class RPCServer {

     private static final String RPC_QUEUE_NAME = "rpc_queue";

     public static void main(String[] args) throws IOException, TimeoutException {
         Connection connection = ConnectionUtil.getConnection();
         final Channel channel = connection.createChannel();
         channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
         channel.basicQos(1);

         Consumer consumer = new DefaultConsumer(channel) {
             @Override
             public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                 super.handleDelivery(consumerTag, envelope, properties, body);
                 AMQP.BasicProperties properties1 = new AMQP.BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();
                 String mes = new String(body, "UTF-8");
                 int num = Integer.valueOf(mes);
                 System.out.println("接收数据：" + num);
                 num = fib(num);
                 channel.basicPublish("", properties.getReplyTo(), properties1, String.valueOf(num).getBytes());
                 channel.basicAck(envelope.getDeliveryTag(), false);
             }
         };
         channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
         while (true) {
             synchronized (consumer) {
                 try {
                     consumer.wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }

     /*
         获取斐波那契数列的第n个值得大小
      */
     private static int fib(int n) {
         System.out.println(n);
         if (n == 0)
             return 0;
         if (n == 1)
             return 1;
         return fib(n - 1) + fib(n - 2);
     }
 }