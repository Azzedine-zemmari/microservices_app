package com.services.consumer_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private AuditRepository auditRepository;

    public ConsumerService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @RabbitListener(queues = "Queue")
    public void receiveMessage(String producerId) {
        System.out.println("Received message: " + producerId);

        AuditLog log = new AuditLog();
        log.setProducerReferenceId(producerId);
        log.setStatus("RECEIVED");  
        auditRepository.save(log);
    }
}
