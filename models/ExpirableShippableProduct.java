package models;

import interfaces.Shippable;
import java.time.LocalDate;


public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {
    private double weight; 
    
    public ExpirableShippableProduct(String name, double price, int quantity, 
                                   LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
        validateWeight(weight);
        this.weight = weight;
    }
    

    private void validateWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (weight: " + weight + "kg)";
    }
}
