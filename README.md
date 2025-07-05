# Fawry E-Commerce Application

A Java-based e-commerce system built for the Fawry Internship Challenge, demonstrating advanced software engineering principles including SOLID design patterns, comprehensive product management, shopping cart functionality, checkout processing, and shipping services.

## Project Overview

This application showcases a complete e-commerce solution with:
- **Product Management**: Support for different product types (shippable, expirable)
- **Shopping Cart**: Add/remove items, calculate totals
- **Customer Management**: Customer information and balance tracking
- **Checkout System**: Process orders with validation
- **Shipping Service**: Calculate shipping costs and generate labels
- **Exception Handling**: Comprehensive error handling for various scenarios


## SOLID Principles Implementation

This application demonstrates all five SOLID principles:

### 1. Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:
- `Cart`: Manages shopping cart operations
- `Customer`: Handles customer data and balance
- `CheckoutService`: Processes checkout logic
- `ShippingService`: Calculates shipping costs
- `BaseProduct`: Manages product properties

### 2. Open/Closed Principle (OCP)
The system is open for extension but closed for modification:
- New product types can be added by extending `BaseProduct`
- New services can be implemented without modifying existing code
- New exception types can be added without changing existing exception handling

### 3. Liskov Substitution Principle (LSP)
Subtypes can be substituted for their base types:
- `ShippableProduct` and `ShippableExpirableProduct` can be used wherever `Product` is expected
- All product implementations properly override base class methods
- Interface implementations maintain contract consistency

### 4. Interface Segregation Principle (ISP)
- `Product` interface: Core product functionality
- `Shippable` interface: Shipping-specific functionality
- `Expirable` interface: Expiration-specific functionality

### 5. Dependency Inversion Principle (DIP)
High-level modules don't depend on low-level modules:
- Services depend on interfaces (`Product`, `Shippable`) rather than concrete implementations
- Business logic is decoupled from data access
- Exception handling uses abstract exception types

## How to Run

1. Navigate to the `src` directory:
   ```bash
   cd src
   ```

2. Compile the application:
   ```bash
   javac -cp . ECommerceApplication.java
   ```

3. Run the application:
   ```bash
   java ECommerceApplication
   ```

## Code Examples and Working Proof

### Example 1: Product Creation and Management

```java

ShippableProduct laptop = new ShippableProduct("Gaming Laptop", 1299.99, 3, 3.2);
ShippableProduct headphones = new ShippableProduct("Wireless Headphones", 89.99, 15, 0.3);
ShippableExpirableProduct milk = new ShippableExpirableProduct("Organic Milk", 4.99, 20, 1.0, 
        LocalDate.now().plusDays(7));
ShippableExpirableProduct bread = new ShippableExpirableProduct("Fresh Bread", 2.49, 8, 0.5, 
        LocalDate.now().plusDays(3));
```

### Example 2: Customer and Cart Operations

```java
Customer customer = new Customer("Osama Rabea", "osrab3@gmail.com", 2000.0);

Cart cart = customer.getCart();
cart.addItem(new CartItem(laptop, 1));
cart.addItem(new CartItem(headphones, 2));
cart.addItem(new CartItem(milk, 3));
cart.addItem(new CartItem(bread, 2));

double total = cart.getTotalPrice();
```

### Example 3: Shipping Calculations

```java
ShippingService shippingService = new ShippingService();
double shippingCost = shippingService.calculateShippingCost(laptop); 

String label = shippingService.generateShippingLabel(laptop, "123 Street, Cairo, Egypt");
```

## Application Output

The following is the real output from running the application, proving that all functionality works correctly:

```
=== Fawry E-Commerce Application Demo ===

Creating Products...
Products created successfully!
   - Gaming Laptop ($1299.99, 3 in stock)
   - Wireless Headphones ($89.99, 15 in stock)
   - Organic Milk ($4.99, expires: 2025-07-12)
   - Fresh Bread ($2.49, expires: 2025-07-08)

Shopping Cart Operations...
Items added to cart:
   - 1x Gaming Laptop = $1299.99
   - 2x Wireless Headphones = $179.98
   - 3x Organic Milk = $14.97
   - 2x Fresh Bread = $4.98
Cart Summary:
   Total Items: 4
   Total Price: $1499.92
   Customer Balance: $2000.00

Processing Checkout...
Checkout successful!
Remaining balance: $500.08

Shipping Service Demo...
Shippable Items:
   - Gaming Laptop (Weight: 3.2kg, Shipping: $16.40)
   - Wireless Headphones (Weight: 0.3kg, Shipping: $10.60)
Total Shipping Cost: $17.00

Shipping Labels:
Shipping Label for Gaming Laptop
Weight: 3.20 kg
To: 123 Street, Cairo, Egypt
---
Shipping Label for Wireless Headphones
Weight: 0.30 kg
To: 123 Street, Cairo, Egypt
---

Product Availability Check...
Updated Product Stock:
   - Gaming Laptop: 2 remaining
   - Wireless Headphones: 13 remaining
   - Organic Milk: 17 remaining
   - Fresh Bread: 6 remaining

Expiration Status Check...
   - Organic Milk expires on 2025-07-12 (Expired: false)
   - Fresh Bread expires on 2025-07-08 (Expired: false)

Demo completed successfully!
Thank you for using Fawry E-Commerce System!
```

## Architecture Highlights

### Key Classes and Their Responsibilities:

- **ECommerceApplication**: Main application and demo runner
- **BaseProduct**: Abstract base class implementing common product functionality
- **ShippableProduct**: Concrete implementation for shippable products
- **ShippableExpirableProduct**: Concrete implementation for products that can be shipped and expire
- **Cart**: Shopping cart with item management and total calculation
- **Customer**: Customer information and balance management
- **CheckoutService**: Handles checkout process with comprehensive validation
- **ShippingService**: Calculates shipping costs and generates labels

## Exception Handling

The application includes custom exceptions for:
- **EmptyCartException**: When attempting checkout with empty cart
- **ExpiredProductException**: When product has expired
- **InsufficientBalanceException**: When customer has insufficient balance
- **OutOfStockException**: When product is out of stock

All exceptions provide meaningful error messages and proper error handling throughout the application.

## Future Enhancements

Potential improvements for the system:
- Database integration for persistent storage
- RESTful API implementation
- User authentication and authorization
- Payment gateway integration
- Unit and integration testing
