package services;

import models.Cart;
import models.Customer;
import models.CartItem;
import exceptions.EmptyCartException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;
import exceptions.ExpiredProductException;
import interfaces.Product;

public class CheckoutService {
    
    public void checkout(Customer customer) {
        Cart cart = customer.getCart();
        
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }
        
        
        validateCartItems(cart);
        
        double totalAmount = cart.getTotalPrice();
        
        if (!customer.hasSufficientBalance(totalAmount)) {
            throw new InsufficientBalanceException(totalAmount, customer.getBalance());
        }
        
        processCheckout(customer, cart);
    }
    
    private void validateCartItems(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (!product.isAvailable()) {
                throw new OutOfStockException(product.getName());
            }
            
            if (item.getQuantity() > product.getQuantity()) {
                throw new OutOfStockException("Requested quantity exceeds available stock for " + product.getName());
            }
        }
    }
    
    private void processCheckout(Customer customer, Cart cart) {
        double totalAmount = cart.getTotalPrice();
        
        customer.deductFromBalance(totalAmount);
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.reduceQuantity(item.getQuantity());
        }
        
        // Clear the cart
        cart.clear();
    }
    
    public double calculateTotal(Cart cart) {
        return cart.getTotalPrice();
    }
}

