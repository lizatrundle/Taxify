package taxify.Taxify;

import java.util.List;
import java.util.ArrayList;


/**
 * TaxiCompany class, implements ITaxiCompany and ISubject
 * defines the key methods for the taxi company
 */
public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    //keep track of total users and vehicles 
    private int totalServices;
    private IObserver observer;
    
    /**
    * constructor, takes in name of taxi comapany, list of users, and list of vehicles 
    initializes totalServices to inital 0
    **/
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;
        
        // set the taxi company for users and vehicles
        for (int i=0; i<this.users.size(); i++)
            this.users.get(i).setCompany(this);
        
        for (int i=0; i<this.vehicles.size(); i++)
            this.vehicles.get(i).setCompany(this);
    }
    

    
    @Override
    /**
     * getter method to get the name of the taxi company 
     */
    public String getName() {
        return this.name;
    }

    @Override
    /**
     * getter method to get the number of total services 
     */
    public int getTotalServices() {
        return this.totalServices;
    }
        
    @Override
    /**
     * method for users to request a ride (service)
     */
    public boolean provideService(int user) {

        int userIndex = indexOfUserId(user);        
        int vehicleIndex = findFreeVehicle();
        
        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks
           
        if (vehicleIndex != -1) {
            ILocation origin, destination;
            
            do {
                
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);
                
            } while (ApplicationLibrary.distance(origin, destination) < 3);
            //this.vehicles.get(vehicleIndex).getLocation()
            // update the user status
                       
            this.users.get(userIndex).setService(true);
            
            // create a service with the user, the pickup and the drop-off location

            Service service = new Service(this.users.get(userIndex), origin, destination, false);
            
            // shared ride specifics 

            int shared_vehicle = -1;
            int distance_to_shared = 100000;


            // determining "close" as manhattan distance of 2
            for (IVehicle ve: this.vehicles){
                if (ve.getStatus() == VehicleStatus.SERVICE && ve.getDistanceFromPickUp(service) < distance_to_shared && ve.getDistanceFromPickUp(service) >= 2) {
                    distance_to_shared = (ve.getDistanceFromPickUp(service));
                    shared_vehicle = ve.getId();
                  }
                }



            // randomizing ride share 
             



        if (shared_vehicle !=-1 && ApplicationLibrary.rand() % 2 == 0){
            shared_vehicle --; 
            // getting an index out of bounds error so tried to decrement index 
                
    
            IVehicle shared = this.vehicles.get(shared_vehicle);
                                                                                // this service needs to be charnged to current service 
            if (shared.getDistanceFromPickUp(service) < shared.getDistanceFromDropoff(shared.getClosestService())){
                
                  if (shared.getService().size() == 1){
                    shared.pickService(service);
                    service.setShared(true);
                  }  
             
            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
            shared.getClass().getSimpleName() + " " + shared.getId() + " at location " +
            shared.getLocation().toString() + " and is a shared ride. ");
            
            // update the counter of services
            
            this.totalServices++;
          
            return true;

            }}


            this.users.get(userIndex).setService(true);
            
            // create a service with the user, the pickup and the drop-off location

            Service not_shared_service = new Service(this.users.get(userIndex), origin, destination, false);
            
            // assign the new service to the vehicle
            
            this.vehicles.get(vehicleIndex).pickService(not_shared_service);            
             
            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                           this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                           this.vehicles.get(vehicleIndex).getLocation().toString());
            
            // update the counter of services
            
            this.totalServices++;
          
            return true;
        }
    return false;
}

    

    

    @Override
    /**
     * a vehicle arrives at the pickup location
     */
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads user " + vehicle.getClosestService().getUser().getId());
    }
    
    @Override
     /**
     *  a vehicle arrives at the drop-off location
     */
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        
        IService service = vehicle.getClosestService();   
        
        // check which service to return 


        // fix this 
        int user = service.getUser().getId();
        int userIndex = indexOfUserId(user);
       
        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services
        
        this.totalServices--;    
        
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);         
    }
        
    @Override
    /**
     *  adding an observer to a service
     */
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }
    
    @Override
     /**
     *  notifying an observer with message about a service 
     */
    public void notifyObserver(String message) {
        this.observer.updateObserver(message);
    }

     /**
     *  finds all free vehicles and returns the index of the vehicle in the list, otherwise it returns -1
     */
    //update this so that it returns all free vehicles not just one
    private int findFreeVehicle() {
        int count = 0;

        for (IVehicle ve : this.vehicles) { 	
            if (ve.isFree() == true){
                return count;
            }
            count+=1;
        }

            return -1;
    }

     /**
     *  finds the index of a user with the input id in the list, otherwise it returns -1
     */
    private int indexOfUserId(int id) {
        int count =0;

        for (IUser us : this.users) { 	
            if (us.getId() == id){
                return count;
            }
        count+=1;
    }

            return -1;
    }

        }
