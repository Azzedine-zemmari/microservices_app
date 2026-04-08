package com.services.producer_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private MessageRepository messageRepository;
    private RabbitTemplate rabbitTemplate;

    public ProducerService(MessageRepository messageRepository, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Message createAndSend(String content) {
        Message message = new Message(content);
        messageRepository.save(message);
        rabbitTemplate.convertAndSend("Exchange", "RoutingKey", content);
        return message;
    }
}