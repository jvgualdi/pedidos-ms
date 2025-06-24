package tec.jvgualdi.pedidos_ms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(@NotNull Long customerId, @Positive BigDecimal total, @NotBlank String orderId) {

    public OrderCreatedEvent(@NotNull Long customerId, @Positive BigDecimal total, @NotBlank String orderId) {
        this.customerId = customerId;
        this.total = total;
        this.orderId = orderId;
    }
}
