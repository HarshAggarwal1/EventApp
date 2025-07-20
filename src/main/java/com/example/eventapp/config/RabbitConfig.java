package com.example.eventapp.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "event.exchange";
    public static final String QUEUE = "event.queue";
    public static final String ROUTING_KEY = "event.routingKey";

    @Bean public TopicExchange exchange() { return new TopicExchange(EXCHANGE); }
    @Bean public Queue queue() { return new Queue(QUEUE); }
    @Bean public Binding binding(Queue q, TopicExchange ex) {
        return BindingBuilder.bind(q).to(ex).with(ROUTING_KEY);
    }
    @Bean public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate t = new RabbitTemplate(cf);
        t.setMessageConverter(messageConverter());
        return t;
    }
}
