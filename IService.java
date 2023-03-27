package taxify.sprint3;
/**
 * Service implements IService, provides the services of the taxi company -- services of the app, how it changes 
 */
public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public int getStars();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();
    

    // testing
}
