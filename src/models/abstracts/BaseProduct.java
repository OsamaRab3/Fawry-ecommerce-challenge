package models.abstracts;

import interfaces.Product;

public abstract class BaseProduct implements Product {
    protected String name;
    protected double price;
    protected int quantity;

    public BaseProduct(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() 
    { return name; }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean isAvailable() {
        return quantity > 0 && !isProductExpired();

    }

    @Override
    public void reduceQuantity(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough quantity available");
        }
        this.quantity -= amount;
    }

    protected abstract boolean isProductExpired();
}