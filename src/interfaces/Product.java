package interfaces;

public interface Product {
    String getName();
    double getPrice();
    int getQuantity();
    boolean isAvailable();
    void reduceQuantity(int amount);
}