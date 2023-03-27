package taxify.sprint3;

/**
 * User implements IUser, gives info about the user
 * services that the user can request, user can rate, user specific information
 */
public interface IUser {

    public int getId();
    public String getFirstName();
    public String getLastName();
    public boolean getService();
    public void setService(boolean service);
    public void requestService();
    public void rateService(IService service);
    public void setCompany(ITaxiCompany company);
    public String toString();

    // this should stay the same, there are separate users in a ride share instance 

}
