package taxify.sprint3;

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
    public void setCompany(ITaxiCompany company);
    public void pickService(IService service);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isFree();
    public VehicleStatus getStatus();
    public void move();
    public int calculateCost();
    public String showDrivingRoute();
    public String toString();
    
}
