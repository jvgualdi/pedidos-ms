package tec.jvgualdi.pedidos_ms.exception;

public class InvalidOrderUpdateException extends RuntimeException {
    public InvalidOrderUpdateException(String message) {
        super(message);
    }

    public InvalidOrderUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
