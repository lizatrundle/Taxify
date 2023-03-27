package taxify.Taxify;
/**
 * Service implements IService, provides the services of the taxi company -- services of the app, how it changes 
 */
public interface IService {


     // todo --> maybe add the ride share method in Iservice / the service class 
    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public int getStars();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();
    

   
}
