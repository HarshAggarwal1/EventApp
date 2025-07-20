package com.example.eventapp.messaging;
import com.example.eventapp.config.RabbitConfig;
import com.example.eventapp.entity.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleEvent(Event event) {
        System.out.println("[RabbitMQ] New event: " + event.getTitle() + " by " + event.getCreator().getUsername());
    }
}
