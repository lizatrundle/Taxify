package taxify.Taxify;


/**
 *  The interface ISubject declares methods to add and notify observers.
 */
public interface ISubject {
    
    public void addObserver(IObserver observer);
    // maybe add observer when there is a ride share? unsure what this method is really for? 
    

    public void notifyObserver(String message);
    // call notify observer when there is an option of a ride share 
    
}
