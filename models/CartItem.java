package models;

public class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem(Product product, int quantity) {
        validateQuantity(quantity);

        this.product = product;
        this.quantity = quantity;
    }
    

    private void validateQuantity(int quantity) {
         if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }

    public Product getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void editQuantinty(int newQuantity) {
        quantity = newQuantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    @Override
    public String toString() {
        return quantity + "x " + product.getName() + " $" + getTotalPrice();
    }
}
