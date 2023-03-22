package taxify.sprint3;

/**
 * class Taxi extends abstract class Vehicle 
 * taxi specific information, including taxi specific cost (different from shuttle)
 */
public class Taxi extends Vehicle {
    
    public Taxi(int id, ILocation location) {
        super(id, location);
    }        
    
    @Override
    public int calculateCost() {
        return super.calculateCost() * 2;
    }
    
    @Override
    public String toString() {
        return "Taxi    " + super.toString();
    }
}
