package models;


public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity) {
        validateName(name);
        validatePrice(price);
        validateQuantity(quantity);

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
    }
    
    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
    
    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }
    
    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }
    
    
    public abstract boolean isValid();


    // i did this to show off, not ai <<<<<<::::
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return name.equals(product.name) && Double.compare(product.price, price) == 0;
    }
    
    @Override
    public String toString() {
        return name + " ($" + price + ", qty: " + quantity + ")";
    }
}
