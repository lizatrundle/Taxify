package taxify.Taxify;

/**
 * Vehicle implements IVehicle -- from vehicle comes taxi, shuttle etc 
 * specific information about the vehicle, service info, pickup/dropoff, route, etc
 */
public interface IVehicle {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public IService getService();
    public IStatistics getStatistics();



    // add new : something like this?? 
    // public IRideShare getShared();


    public void setCompany(ITaxiCompany company);
    public void pickService(IService service);

    // update this to check for ride share 
    public void startService();
    // update this to check for ride share 
    public void endService();
    // update this to check for ride share 
    public void notifyArrivalAtPickupLocation();
    // update this to check for ride share 
    public void notifyArrivalAtDropoffLocation();

    public boolean isFree();
    public VehicleStatus getStatus();
    public void move();
    public int calculateCost();
    public String showDrivingRoute();
    public String toString();
    
}
