package tec.jvgualdi.pedidos_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.jvgualdi.pedidos_ms.domain.Orders;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
}
