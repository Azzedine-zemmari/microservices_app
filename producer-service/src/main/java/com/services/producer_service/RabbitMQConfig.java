package com.services.producer_service;
import javax.naming.Binding;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration     
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "Exchange";
    public static final String ROUTING_KEY = "RoutingKey";
    public static final String QUEUE_NAME = "Queue";


    @Bean 
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue exampleQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public org.springframework.amqp.core.Binding binding(Queue queue, DirectExchange exchange) {
        return org.springframework.amqp.core.BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
