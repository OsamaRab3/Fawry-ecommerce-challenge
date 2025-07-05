package exceptions;

public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException() {
        super("Product has expired");
    }

    public ExpiredProductException(String message) {
        super(message);
    }

    public ExpiredProductException(String productName, String expirationDate) {
        super("Product '" + productName + "' expired on " + expirationDate);
    }
}
