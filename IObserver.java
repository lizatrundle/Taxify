package taxify.Taxify;



/**
 
observer is notified of any state change and the observers are notified 
when the observable object changes its data.
The interface IObserver declares the update method to post the notifications received from the subject.
the subject is the taxi company 
**/
public interface IObserver {
    
    public void updateObserver(String message);

}
