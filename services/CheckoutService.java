package services;
import java.util.ArrayList;
import java.util.List;
import models.*;
import interfaces.Shippable;


public class CheckoutService {
    private ShippingService shippingService;
    
    public CheckoutService() {
        this.shippingService = new ShippingService();
    }
    
    public void checkout(Customer customer, ShoppingCart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        
        validateCartItems(cart);
        
        double subtotal = cart.getSubtotal();
        
        List<Shippable> shippableItems = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            if (item.getProduct() instanceof Shippable) {
                shippableItems.add((Shippable) item.getProduct());
                quantities.add(item.getQuantity());
            }
        }
        
        //calc shiping price
        double shippingFees = shippingService.processShipment(shippableItems, quantities);
        
        // Calc total amount
        double totalAmount = subtotal + shippingFees;
        

        // Process payment
        customer.newBalance(totalAmount);
        

        // Reduce product quantities
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        
        // Print checkout receipt
        printReceipt(cart, subtotal, shippingFees, totalAmount, customer.getBalance());
        
        // Clear cart after successful checkout
        cart.clear();
    }
    

    private void validateCartItems(ShoppingCart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (!product.isValid()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired");
            }

            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock. " +
                        "Available: " + product.getQuantity() + ", Requested: " + item.getQuantity());
            }
        }
    }
    
    
    private void printReceipt(ShoppingCart cart, double subtotal, double shippingFees, 
                             double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        // Print items
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f%n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getTotalPrice());
        }
        
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shippingFees);

        
        
        System.out.printf("Amount %.0f%n", totalAmount);
        System.out.printf("Customer balance after payment: $%.2f%n", remainingBalance);
    }
}
