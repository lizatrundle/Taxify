package taxify.Taxify;

/**
 * class Shuttle extends abstract class Vehicle 
 * shuttle specific information, including shuttle specific cost (different from taxi)
 */
public class Shuttle extends Vehicle {
    
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }        
    
    @Override
     // update this for billing if the ride is shared -- alternatively just call updatebilling() and add a new cost less 
    public int calculateCost(IService service) {
        return (int) (super.calculateCost(service) * 1.5);
    }

    @Override
    public String toString() {
        return "Shuttle " + super.toString();
    }
}
