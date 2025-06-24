package tec.jvgualdi.pedidos_ms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(nullable = false, name = "customer_id")
    private Long customerId;
    @Column(nullable = false)
    private List<String> products;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false, name = "total_price")
    private BigDecimal totalPrice;
    @Column(nullable= false, name = "create_at", updatable = false)
    private LocalDateTime createdAt;

}
