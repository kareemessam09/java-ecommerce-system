package models;
import java.time.LocalDate;


public class ExpirableProduct extends Product {
    private LocalDate expirationDate;
    
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }
    
    @Override
    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate) || LocalDate.now().equals(expirationDate);
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (expires: " + expirationDate + ")";
    }
}
