package exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(double required, double available) {
        super("Insufficient balance. Required: " + required + ", Available: " + available);
    }
}
