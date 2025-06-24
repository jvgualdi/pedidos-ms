package tec.jvgualdi.pedidos_ms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(@NotNull List<String> products, @NotNull Long customerId, @NotNull @Positive BigDecimal totalPrice) {

}
