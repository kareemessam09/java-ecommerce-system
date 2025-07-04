package models;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private List<CartItem> items;
    
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName() +
                    ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }
        
         if (!product.isValid()) {
            throw new IllegalArgumentException("The Product: " + product.getName() + " is Expired");
        }
    
        //already exists
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                //new quantity
                int newQuantity = item.getQuantity() + quantity;

                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException("Not enough stock for " + product.getName() +
                            ". Available: " + product.getQuantity() + ", Requested : " + newQuantity);
                }
            
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }
        

        items.add(new CartItem(product, quantity)); 
        
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    
    public void clear() {
        items.clear();
    }
}
