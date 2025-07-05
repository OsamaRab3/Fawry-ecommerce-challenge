import models.Customer;
import models.Cart;
import models.CartItem;
import models.Products.ShippableProduct;
import models.Products.ShippableExpirableProduct;
import services.CheckoutService;
import services.ShippingService;
import exceptions.EmptyCartException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;
import exceptions.ExpiredProductException;
import interfaces.Shippable;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ECommerceApplication {
    private CheckoutService checkoutService;
    private ShippingService shippingService;
    
    public ECommerceApplication() {
        this.checkoutService = new CheckoutService();
        this.shippingService = new ShippingService();
    }
    
    public static void main(String[] args) {
        ECommerceApplication app = new ECommerceApplication();
        app.runDemo();
    }
    
    public void runDemo() {
        System.out.println("=== Fawry E-Commerce Application Demo ===\n");

        System.out.println("Creating Products...");
        ShippableProduct laptop = new ShippableProduct("Gaming Laptop", 1299.99, 3, 3.2);
        ShippableProduct headphones = new ShippableProduct("Wireless Headphones", 89.99, 15, 0.3);
        ShippableExpirableProduct milk = new ShippableExpirableProduct("Organic Milk", 4.99, 20, 1.0, 
                LocalDate.now().plusDays(7));
        ShippableExpirableProduct bread = new ShippableExpirableProduct("Fresh Bread", 2.49, 8, 0.5, 
                LocalDate.now().plusDays(3));
        
        System.out.println("Products created successfully!");
        System.out.println("   - " + laptop.getName() + " ($" + laptop.getPrice() + ", " + laptop.getQuantity() + " in stock)");
        System.out.println("   - " + headphones.getName() + " ($" + headphones.getPrice() + ", " + headphones.getQuantity() + " in stock)");
        System.out.println("   - " + milk.getName() + " ($" + milk.getPrice() + ", expires: " + milk.getExpirationDate() + ")");
        System.out.println("   - " + bread.getName() + " ($" + bread.getPrice() + ", expires: " + bread.getExpirationDate() + ")\n");


        Customer customer = new Customer("Osama Rabea", "osrab3@gmail.com", 2000.0);

        System.out.println("Shopping Cart Operations...");
        Cart cart = customer.getCart();
        
        cart.addItem(new CartItem(laptop, 1));
        cart.addItem(new CartItem(headphones, 2));
        cart.addItem(new CartItem(milk, 3));
        cart.addItem(new CartItem(bread, 2));
        
        System.out.println("Items added to cart:");
        for (CartItem item : cart.getItems()) {
            System.out.println("   - " + item.getQuantity() + "x " + item.getProductName() + " = $" + item.getTotalPrice());
        }
        System.out.println("Cart Summary:");
        System.out.println("   Total Items: " + cart.getItems().size());
        System.out.println("   Total Price: $" + String.format("%.2f", cart.getTotalPrice()));
        System.out.println("   Customer Balance: $" + String.format("%.2f", customer.getBalance()));

        System.out.println("\n Processing Checkout...");
        try {
            checkoutService.checkout(customer);
            System.out.println("Checkout successful!");
            System.out.println("Remaining balance: $" + String.format("%.2f", customer.getBalance()));
        } catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExpiredProductException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nShipping Service Demo...");
        List<Shippable> shippableItems = new ArrayList<>();
        shippableItems.add(laptop);
        shippableItems.add(headphones);
        
        System.out.println("Shippable Items:");
        for (Shippable item : shippableItems) {
            double shippingCost = shippingService.calculateShippingCost(item);
            System.out.println("   - " + item.getName() + " (Weight: " + item.getWeight() + "kg, Shipping: $" + String.format("%.2f", shippingCost) + ")");
        }
        
        double totalShippingCost = shippingService.calculateShippingCost(shippableItems);
        System.out.println("Total Shipping Cost: $" + String.format("%.2f", totalShippingCost));
        
        System.out.println("\n Shipping Labels:");
        String address = "123 Streat, Cairo, Egypt";
        for (Shippable item : shippableItems) {
            String shippingLabel = shippingService.generateShippingLabel(item, address);
            System.out.println(shippingLabel);
            System.out.println("---");
        }
        
        System.out.println("\n Product Availability Check...");
        System.out.println("Updated Product Stock:");
        System.out.println("   - " + laptop.getName() + ": " + laptop.getQuantity() + " remaining");
        System.out.println("   - " + headphones.getName() + ": " + headphones.getQuantity() + " remaining");
        System.out.println("   - " + milk.getName() + ": " + milk.getQuantity() + " remaining");
        System.out.println("   - " + bread.getName() + ": " + bread.getQuantity() + " remaining");
        
        System.out.println("\n Expiration Status Check...");
        System.out.println("   - " + milk.getName() + " expires on " + milk.getExpirationDate() + " (Expired: " + milk.isExpired() + ")");
        System.out.println("   - " + bread.getName() + " expires on " + bread.getExpirationDate() + " (Expired: " + bread.isExpired() + ")");
        
        System.out.println("\n Demo completed successfully!");
        System.out.println("Thank you for using Fawry E-Commerce System!");
    }
}
