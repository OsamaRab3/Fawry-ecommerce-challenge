package exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Product is out of stock");
    }

    public OutOfStockException(String message) {
        super(message);
    }
}
