

package com.michzarnowski.michal_zarnowski_a1.model;


public class PizzaOrder {
    
    private String[] toppings;
    private String size;
    private boolean delivery;

    public PizzaOrder() {
    }
    
    public String[] getToppings() {
        return toppings;
    }
    
    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public boolean isDelivery() {
        return delivery;
    }
    
    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }
    
    public double getPrice() {
        double price = 0.0;
        
        //add delivery cost
        if(isDelivery())
            price += 2.0;
        
        //add size cost
        String size = getSize();
        price += 5.0; //default small cost
        
        if(size.equals("medium"))
            price += 2.0;
        else if(size.equals("large"))
            price += 4.0;
        
        //add topping cost
        String[] toppings = getToppings();
        int amountOfToppings = toppings.length;
        price += amountOfToppings * 1.0;
        
        return price;
    }
    
    @Override
    public String toString(){
        String format = "Your %s pizza for %s will have:\n";
        
        //Add toppings
        if(toppings.length == 0)
            format += "No toppings";
        else {
            for(int i = 0; i < toppings.length; i++){
                format += toppings[i] + "\n";
            }
        }
        
        //Convert delivery to String
        String delivered = null;
        if(delivery)
            delivered = "delivery";
        else
            delivered = "pick-up";
        
        return String.format(format, size, delivered);
    }

}
