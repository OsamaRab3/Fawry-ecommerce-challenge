package models.Products;
import  models.abstracts.BaseProduct;

import java.time.LocalDate;
import interfaces.Shippable;
import interfaces.Expirable;

public class ShippableExpirableProduct extends BaseProduct implements Shippable , Expirable {
    private double weight;
    private LocalDate expirationDate;
    
    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    protected boolean isProductExpired() {
        return isExpired();
    }
}
