package test.java.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
//    rabbit.mq.url=dev-rabbitmq.apuscn.com
//    rabbit.mq.port=6672
//    rabbit.mq.exchange=dsp-creative-consumption
//    rabbit.mq.routingkey=consumption
//    rabbit.mq.queue=dsp-creative-consumption-flow
//    rabbit.mq.username=dev_consumer
//    rabbit.mq.password=dev123
//    rabbit.mq.virtualHost=dev

    private static final String RABBIT_HOST = "dev-rabbitmq.apuscn.com";

    private static final String RABBIT_USERNAME = "dev_consumer";

    private static final String RABBIT_PASSWORD = "dev123";
    static String vhost = "dev";
    private static final int port = 6672;
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(RABBIT_HOST);
            connectionFactory.setPort(port);
            connectionFactory.setUsername(RABBIT_USERNAME);
            connectionFactory.setPassword(RABBIT_PASSWORD);
            connectionFactory.setVirtualHost(vhost);
            try {
                connection = connectionFactory.newConnection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}

