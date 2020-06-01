package ru.itis.lazyMarket.clients.rabbitClients;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RabbitMQListener {

    Logger logger = Logger.getLogger(RabbitMQListener.class);
    Random random = new Random();

    @RabbitListener(queues = "query-example-5-1")
    public void worker1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "query-example-5-2")
    public void worker2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }

}
