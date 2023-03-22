package taxify.sprint3;

/**
 * class Shuttle extends abstract class Vehicle 
 * shuttle specific information, including shuttle specific cost (different from taxi)
 */
public class Shuttle extends Vehicle {
    
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }        
    
    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 1.5);
    }

    @Override
    public String toString() {
        return "Shuttle " + super.toString();
    }
}
