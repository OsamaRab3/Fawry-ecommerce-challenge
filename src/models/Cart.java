package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(CartItem item) {
        items.add(item);
        calculateTotal();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        calculateTotal();
    }

    public List<CartItem> getItems() {

        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
        totalPrice = 0.0;
    }

    private void calculateTotal() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getTotalPrice())
                .sum();
    }
}

