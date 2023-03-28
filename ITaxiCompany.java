package taxify.Taxify;

/**
 * Taxi company as a whole
 */
public interface ITaxiCompany {

    public String getName();    
    public int getTotalServices();
    public boolean provideService(int user);
    
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);
    // update this method with an if statement regarding if the ride is shared 
        
    }

    // more methods will be declared in upcoming sprints


