package tec.jvgualdi.pedidos_ms.dto;

import tec.jvgualdi.pedidos_ms.domain.Status;

public record OrderResponse(Status status, String message) {

    public OrderResponse(Status status, String message){
        this.status = status;
        this.message = message;
    }
}
