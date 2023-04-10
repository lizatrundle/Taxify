package taxify.Taxify;

/**
 * class Taxi extends abstract class Vehicle 
 * taxi specific information, including taxi specific cost (different from shuttle)
 */
public class Taxi extends Vehicle {
    
    public Taxi(int id, ILocation location) {
        super(id, location);
    }        
    
    @Override
    // update this for billing if the ride is shared -- alternatively just call updatebilling() and add a new cost less 
    public int calculateCost(IService service) {
        return super.calculateCost(service) * 2;
    }
    
    @Override
    public String toString() {
        return "Taxi    " + super.toString();
    }
}
