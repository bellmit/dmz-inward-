import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dmz
 * @date 2017/11/28
 */
public class ReceiverWithACKPointToPoint {
    private static String QUEUE_NAME="DMZ_RABBIT";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.110.41");
        factory.setPort(5672);
        factory.setUsername("namibank");
        factory.setPassword("namibank123");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //In order to defeat that we can use the basicQos method with the prefetchCount = 1 setting.
        //        This tells RabbitMQ not to give more than one message to a worker at a time. Or, in other words,
        //don't dispatch a new message to a worker until it has processed and acknowledged the previous one. Instead,
        //it will dispatch it to the next worker that is not still busy

        channel.basicQos(1); // accept only one unack-ed message at a time (see below)

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");


                try {
                    System.out.println(" [x] Received '" + message + "'");
                } finally {
                    //
                    //It's a common mistake to miss the basicAck. It's an easy error,
                    // but the consequences are serious.
                    // Messages will be redelivered when your client quits (which may look like random redelivery),
                    // but RabbitMQ will eat more and more memory as it won't be able to release any unacked messages.
                    //In order to debug this kind of mistake you can use rabbitmqctl to print the messages_unacknowledged field:
                    //
                    //sudo rabbitmqctl list_queues name messages_ready messages_unacknowledged
                    channel.basicAck(envelope.getDeliveryTag(), false);

                }
            }
        };

        // autoAck = false开启
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}


