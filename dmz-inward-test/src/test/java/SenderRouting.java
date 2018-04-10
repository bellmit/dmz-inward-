import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author dmz
 * @date 2017/12/8
 */
public class SenderRouting {

    private static final String EXCHANGE_NAME = "direct_logs";

    private static List<String> logLevel = new ArrayList<>();

    static {
        logLevel.add("warring");
        logLevel.add("error");
        logLevel.add("info");
    }
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.110.41");
        factory.setPort(5672);
        factory.setUsername("namibank");
        factory.setPassword("namibank123");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //String severity = getSeverity(argv);
        //String message = getMessage(argv);

        for (String logLevel : logLevel) {
            //channel.queueDeclare("routing_test", true, false, false, null);
            //channel.queueBind("routing_test", EXCHANGE_NAME,logLevel);
            channel.basicPublish(EXCHANGE_NAME, logLevel, null, (logLevel+" message").getBytes());
            System.out.println(" [x] Sent '" + logLevel + "':'" + logLevel+" message" + "'");

        }

        channel.close();
        connection.close();
    }
}
