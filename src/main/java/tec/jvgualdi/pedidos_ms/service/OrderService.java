package tec.jvgualdi.pedidos_ms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tec.jvgualdi.pedidos_ms.domain.Orders;
import tec.jvgualdi.pedidos_ms.domain.Status;
import tec.jvgualdi.pedidos_ms.dto.OrderCreatedEvent;
import tec.jvgualdi.pedidos_ms.dto.OrderRequest;
import tec.jvgualdi.pedidos_ms.dto.OrderResponse;
import tec.jvgualdi.pedidos_ms.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderPublisher orderPublisher;

    public OrderService(OrderRepository orderRepository, OrderPublisher orderPublisher) {
        this.orderRepository = orderRepository;
        this.orderPublisher = orderPublisher;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderDto){
        var order = new Orders();
        order.setId(UUID.randomUUID());
        order.setCustomerId(orderDto.customerId());
        order.setProducts(orderDto.products());
        order.setTotalPrice(orderDto.totalPrice());
        order.setStatus(Status.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);
        orderPublisher.publish(createOrderEvent(order));
        return new OrderResponse(Status.CREATED, "Order created successfully");
    }

    public OrderResponse getOrderById(UUID id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderResponse(order.getStatus(), "Order retrieved successfully");
    }

    public OrderResponse updateOrderStatus(UUID id, Status status) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return new OrderResponse(status, "Order status updated successfully");
    }

    public OrderResponse deleteOrder(UUID id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() == Status.APPROVED) {
            throw new RuntimeException("Cannot delete an approved order");
        }
        orderRepository.delete(order);
        return new OrderResponse(Status.CANCELED, "Order deleted successfully");
    }

    private OrderCreatedEvent createOrderEvent(Orders order) {
        return new OrderCreatedEvent(
                order.getCustomerId(),
                order.getTotalPrice(),
                order.getId().toString()
        );
    }
}
