package taxify.sprint3;
import java.util.ArrayList;
import java.util.List;


/**
 * ABSTRACT class vehicle implements IVehicle 
 * constructor takes in id and location (x,y)
 * implements IVehicle methods, includes information for taxi and shuttle as child classes 
 */

public abstract class Vehicle implements IVehicle {
    private int id;
    private ITaxiCompany company;
    private IService service;
    private VehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private List<ILocation> route;
        

     /**
     * Vehicle class constructor, takes in unique user ID and pickup location (x,y)
     */
    public Vehicle(int id, ILocation location) {        
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
    }

    @Override
     /**
     * returns users unique id 
     */
    public int getId() {
        return this.id;
    }
 

    @Override
     /**
     * getter method: returns users pickup location, as an Ilocation object (x,y) coordinate
     */
    public ILocation getLocation() {
        return this.location;
        
    }

    @Override
    /**
     * getter method: returns users destination
     */
    public ILocation getDestination() {
        return this.destination;
       
    }
    
    @Override
     /**
     * getter method: returns service
     */
    public IService getService() {
       return this.service;
    }
    
    @Override
     /**
     * getter method: returns statistics 
     */
    public IStatistics getStatistics() {
       return this.statistics;
    }
    
    @Override
     /**
     * setter method: change the company to specific taxicompany 
     */
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
     /**
     * pick a service, set destination to the service pickup location, and status to "pickup"
     */
    public void pickService(IService service) {
        
        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.PICKUP;
    }

    @Override
    /**
     * set destination to the service drop-off location, update the driving route,
     * set status to "service"
     */
    public void startService() {
        
        this.destination = service.getDropoffLocation();
        //used get pickuplocation() as start, could alternatively be this.location
        this.route = setDrivingRouteToDestination(service.getPickupLocation(), this.destination);
        this.status = VehicleStatus.SERVICE;

    }



    @Override
    /** 
     * ending a service, resetting all the settings
     */
    public void endService() {
        // update vehicle statistics
        
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();
        
        // if the service is rated by the user, update statistics
        
        if (this.service.getStars() != 0) {
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }
        
        // set service to null, and status to "free"
        
        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    }

    @Override
    /**
     * notifying the company that the vehicle is at the pickup location,
     * then start the service 
     */
    public void notifyArrivalAtPickupLocation() {
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }
        
    @Override
    /**
     * notifying the company that the vehicle is at the dropoff location,
     * then end the service 
     */
    public void notifyArrivalAtDropoffLocation() {
        this.company.arrivedAtDropoffLocation(this);
        this.endService();
        // this.calculateCost();
     }
        
    @Override
    /**
     * returns true if the status of the vehicle is "free" and false otherwise
     */
    public boolean isFree() {
        if (this.status == VehicleStatus.FREE){
            return true;
        }
        return false;
    } 
    

    @Override
    /**
     * ADDED THIS: A getter method to get the status of the vehicle, before there was only a isFree() method
     */
    public VehicleStatus getStatus(){
        return this.status;
    } 

    
    @Override
    /**
     * gets the next location from the driving route
     */
    public void move() {
        
        
        this.location = this.route.get(0);        
        this.route.remove(0);

        if (this.route.isEmpty()) {
            if (this.service == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setDrivingRouteToDestination(this.location, this.destination);
            }
            else {
                // checks if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.service.getPickupLocation();
                ILocation destination = this.service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();
                    // endService();
                    // this.endService();

                }        
            }
        }
    }

    @Override
    /**
     * returns the cost of the service as the distance
     * super method of Vehicle, and the specific taxi and shuttle cost 
     * are calculated in their own methods 
     *
     */
    public int calculateCost() {
        
        return this.service.calculateDistance();
    
    }

    @Override
    /**
     * shows the route of the car in string format 
     */
    public String showDrivingRoute() {
        String s = "";
       
           for (ILocation l : this.route)
               s = s + l.toString() + " ";
       
           return s;
    }

    @Override
     /**
     * turns entire method to string
     */
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free with path " + showDrivingRoute(): ((this.status == VehicleStatus.PICKUP) ? " to pickup user " +
               this.service.getUser().getId() : " in service "));
    }
    
    private List<ILocation> setDrivingRouteToDestination(ILocation location, ILocation destination) {
        List<ILocation> route = new ArrayList<ILocation>();
        
        int x1 = location.getX();
        int y1 = location.getY();
        
        int x2 = destination.getX();
        int y2 = destination.getY();
        
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
       
        for (int i=1; i<=dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }
        
        for (int i=1; i<=dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;
            
            route.add(new Location(x1, y1));
        }
        
        return route;
    }       
}
