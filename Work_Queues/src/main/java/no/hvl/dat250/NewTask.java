package no.hvl.dat250;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class NewTask {

    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
            String message = String.join(" ", argv);
            channel.basicPublish("", "hello", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        }
    }
}







