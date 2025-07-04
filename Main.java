
import java.time.LocalDate;
import models.*;
import tests.*;

/*
 * HI!
 * 
 * i made the logic as simple as possible, i had different approches but i choosed the simplest one to make it easy to be reviewd
 * java is my first language and i've learnt OOP with it so, i guess this is the first love
 * 
 * thanks for review and iam excited to work together
 * 
 * and yeah i wrote the tests by ai to be honest but the rest work is 100% mine
 * 
 * thanks again and have a good day
 * 
 */


public class Main {
    public static void main(String[] args) {

        System.out.println("=== E-COMMERCE SYSTEM ===\n");
        
        // Create products
        ExpirableShippableProduct cheese = new ExpirableShippableProduct(
            "Cheese", 100.0, 10, LocalDate.now().plusDays(7), 0.2); 
        
        ExpirableShippableProduct biscuits = new ExpirableShippableProduct(
            "Biscuits", 150.0, 5, LocalDate.now().plusDays(30), 0.7);
        
        NonExpirableShippableProduct tv = new NonExpirableShippableProduct(
            "TV", 500.0, 3, 15.0); 
        
        NonExpirableProduct scratchCard = new NonExpirableProduct(
            "Mobile Scratch Card", 25.0, 20);
        

        // Create customer
        Customer customer = new Customer("Kareem Essam", 1000.0); // 3awzeen beldolar b2a haha
        
        System.out.println("Customer: " + customer);
        System.out.println("\nAvailable Products:");
        System.out.println("- " + cheese);
        System.out.println("- " + biscuits);
        System.out.println("- " + tv);
        System.out.println("- " + scratchCard);
        
        // Test Case 1: Normal checkout
        System.out.println("\n=== TEST CASE 1: Normal Checkout ===");
        Tests.testNormalCheckout(customer, cheese, tv, scratchCard);
    
        // Test Case 2: Empty cart
        System.out.println("\n=== TEST CASE 2: Empty Cart Error ===");
        Tests.testEmptyCart(customer);
        
        // Test Case 3: Insufficient balance
        System.out.println("\n=== TEST CASE 3: Insufficient Balance Error ===");
        Tests.testInsufficientBalance(customer, tv);
        
        // Test Case 4: Out of stock
        System.out.println("\n=== TEST CASE 4: Out of Stock Error ===");
        Tests.testOutOfStock(customer, cheese);
        
        // Test Case 5: Expired product
        System.out.println("\n=== TEST CASE 5: Expired Product Error ===");
        Tests.testExpiredProduct(customer);
        
        // Test Case 6: Non-shippable items only
        System.out.println("\n=== TEST CASE 6: Non-shippable Items Only ===");
        Tests.testNonShippableOnly(customer, scratchCard);
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }
    

}
