package taxify.sprint3;

import java.util.List;
 /**
     *  class Applicationsimulator, implements applicationsimulator interface and observer interface
     */
public class ApplicationSimulator implements IApplicationSimulator, IObserver {
    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;

    /**
     *  constructor, takes in parameters company, list of users, and list of vehicles 
     */
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
    }
    
    @Override
     /**
     *  shows the status of the vehicles 
     */
    public void show() {
        
        System.out.println("\n" + this.company.getName() + " status \n");

        for (int i=0; i<this.vehicles.size(); i++) {
            System.out.println(this.vehicles.get(i).toString());
        }   
    }
    
    @Override
      /**
     * shows the statistics of the company
     */
    public void showStatistics() {
        System.out.println("\n" + this.company.getName() + " statistics \n");

        for (IVehicle ve : this.vehicles){
            IStatistics stats = ve.getStatistics();
            System.out.println("\n" + ve.getClass().getSimpleName() + " " +ve.getId() + " " + stats.getServices() + " services " + stats.getDistance() + " km. " + stats.getBilling() + " eur. " + stats.getReviews()+ " reviews. " + stats.getStars() + " stars. ");
        } 
    }    

    @Override
    /**
     * moves the vehicles to their next location
     */
    public void update() {
        
        for (int i=0; i<this.vehicles.size(); i++) {
               this.vehicles.get(i).move();
        }
    }
        
    @Override
    /**
     * finds a "free" user and requests a service to the Taxi Company
     */
    public void requestService() {      
        for (IUser u : this.users){
            if (u.getService()== false){
                this.company.requestService(u.getId());
                break;
            }}
    }
    
    @Override
    /**
     * getter method: returns total services 
     */
    public int getTotalServices() {
        return this.company.getTotalServices();
    }
    
    @Override
    /**
     * setter method: prints method for observer 
     */
    public void updateObserver(String message) {
        System.out.println(message);
    }
}
