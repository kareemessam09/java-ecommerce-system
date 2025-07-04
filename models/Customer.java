package models;

public class Customer {
    private String name;
    private double balance;
    
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public boolean canAfford(double amount) {
        return balance >= amount;
    }
    
    public void newBalance(double amount) {
        if (canAfford(amount)) {
            this.balance -= amount;
        } else {
            throw new IllegalStateException("Insufficient balance. Required: $" + amount +
                ", Available: $" + this.getBalance());
            }
    }
    
    @Override
    public String toString() {
        return name + " (balance: $" + balance + ")";
    }
}
