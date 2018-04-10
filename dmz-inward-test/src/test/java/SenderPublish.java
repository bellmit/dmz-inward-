import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dmz
 * @date 2017/12/8
 */
public class SenderPublish {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.110.41");
        factory.setPort(5672);
        factory.setUsername("namibank");
        factory.setPassword("namibank123");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "logging...";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        // routingKey ignore for fanout
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
