package models.Products;

import models.abstracts.BaseProduct;
import interfaces.Shippable;

public class ShippableProduct extends BaseProduct implements Shippable {
    private double weight;
    
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    
    @Override
    public double getWeight() {
        return weight;
    }
    
    @Override
    protected boolean isProductExpired() {
        return false; 
    }
}