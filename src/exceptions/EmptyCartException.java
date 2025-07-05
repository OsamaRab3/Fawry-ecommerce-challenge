package exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cart is empty");
    }

    public EmptyCartException(String message) {
        super(message);
    }
}
