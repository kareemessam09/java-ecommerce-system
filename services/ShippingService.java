package services;

import interfaces.Shippable;
import java.util.List;


public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 7.0; 
    
   
    public double processShipment(List<Shippable> shippableItems, List<Integer> quantities) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        
        System.out.println("** Shipment reciept **");
        
        double totalWeight = 0.0;
        for (int i = 0; i < shippableItems.size(); i++) {
            Shippable item = shippableItems.get(i);
            int quantity = quantities.get(i);
            double itemWeight = item.getWeight() * quantity;
            totalWeight += itemWeight;
            
            // Format weight display
            String weightDisplay = String.format("%.0fg", item.getWeight() * 1000); 
            System.out.println(quantity + "x " + item.getName() + " " + weightDisplay);
        }
        
        System.out.printf("Total package weight %.1fkg%n \n", totalWeight);

        

        return (totalWeight * SHIPPING_RATE_PER_KG);
    }
}
