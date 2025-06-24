package tec.jvgualdi.pedidos_ms.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tec.jvgualdi.pedidos_ms.dto.OrderCreatedEvent;
import tec.jvgualdi.pedidos_ms.dto.OrderRequest;

@Component
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(OrderCreatedEvent order) {
        rabbitTemplate.convertAndSend(queueName, order);
    }
}
