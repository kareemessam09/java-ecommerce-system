package tests;

import java.time.LocalDate;
import models.*;
import services.*;

public class Tests {
    
    public static void testNormalCheckout(Customer customer, Product cheese, Product tv, Product scratchCard) {
        try {
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            
            System.out.println("Cart contents:");
            for (CartItem item : cart.getItems()) {
                System.out.println("- " + item);
            }

            System.out.println();

            
            checkoutService.checkout(customer, cart);
            System.out.println("✓ Checkout successful");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public static void testEmptyCart(Customer customer) {
        try {
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            checkoutService.checkout(customer, cart);
            System.out.println("✗ Should have failed");
            
        } catch (Exception e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage());
        }
    }
    
    public static void testInsufficientBalance(Customer customer, Product tv) {
        try {
            // Create customer with low balance
            Customer poorCustomer = new Customer("Poor Customer", 50.0);
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            cart.add(tv, 1);
            
            checkoutService.checkout(poorCustomer, cart);
            System.out.println("✗ Should have failed");
            
        } catch (Exception e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage());
        }
    }
    
    public static void testOutOfStock(Customer customer, Product cheese) {
        try {
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            // Try to add more than available (cheese quantity is reduced from previous test)
            cart.add(cheese, 20);
            
            checkoutService.checkout(customer, cart);
            System.out.println("✗ Should have failed");
            
        } catch (Exception e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage());
        }
    }
    
    public static void testExpiredProduct(Customer customer) {
        try {
            // Create expired product
            ExpirableShippableProduct expiredMilk = new ExpirableShippableProduct(
                "Milk", 50.0, 5, LocalDate.now().minusDays(1), 0.5);
            
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            cart.add(expiredMilk, 1);
            
            checkoutService.checkout(customer, cart);
            System.out.println("✗ Should have failed");
            
        } catch (Exception e) {
            System.out.println("✓ Correctly caught error: " + e.getMessage());
        }
    }
    
    public static void testNonShippableOnly(Customer customer, Product scratchCard) {
        try {
            ShoppingCart cart = new ShoppingCart();
            CheckoutService checkoutService = new CheckoutService();
            
            cart.add(scratchCard, 3);
            
            System.out.println("Cart contents (non-shippable only):");
            for (CartItem item : cart.getItems()) {
                System.out.println("- " + item);
            }
            
            checkoutService.checkout(customer, cart);
            System.out.println("✓ Checkout successful (no shipping required)");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
