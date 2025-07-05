package services;

import interfaces.Shippable;
import models.Products.ShippableProduct;
import models.Products.ShippableExpirableProduct;
import java.util.List;
import java.util.ArrayList;

public class ShippingService {
    private static final double BASE_SHIPPING_COST = 10.0;
    private static final double WEIGHT_MULTIPLIER = 2.0;
    
    public double calculateShippingCost(List<Shippable> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = shippableItems.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();
        
        return BASE_SHIPPING_COST + (totalWeight * WEIGHT_MULTIPLIER);
    }
    
    public double calculateShippingCost(Shippable item) {
        if (item == null) {
            return 0.0;
        }
        
        return BASE_SHIPPING_COST + (item.getWeight() * WEIGHT_MULTIPLIER);
    }
    
    public boolean canShip(Shippable item) {
        if (item instanceof ShippableExpirableProduct) {
            ShippableExpirableProduct expirableProduct = (ShippableExpirableProduct) item;
            return !expirableProduct.isExpired();
        }
        return true;
    }
    
    public List<Shippable> filterShippableItems(List<Shippable> items) {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (Shippable item : items) {
            if (canShip(item)) {
                shippableItems.add(item);
            }
        }
        
        return shippableItems;
    }
    
    public String generateShippingLabel(Shippable item, String customerAddress) {
        return String.format("Shipping Label for %s\nWeight: %.2f kg\nTo: %s", 
                item.getName(), item.getWeight(), customerAddress);
    }
}

