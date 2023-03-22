package taxify.sprint3;

/**
 * test program to test changes made to taxify application
 * testing continuously on each sprint
**/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestProgram {

    public static void main(String[] args) {

      //Declare a list of users. Instantiate at least 15 users
      List<IUser> users = new ArrayList<IUser>();

      IUser liza = new User(1,"liza", "trundle");
      IUser ben = new User(2,"ben", "smith");
      IUser max = new User(3,"max", "moore");
      IUser emma = new User(4,"emma", "wayne");
      IUser nate = new User(5,"nate", "stone");
      IUser rachel = new User(6,"rachel", "wilson");
      IUser arianna = new User(7,"arianna", "santiago");
      IUser chris = new User(8,"chris", "wack");
      IUser james = new User(9,"james", "gresham");
      IUser kelly = new User(10,"kelly", "kat");
      IUser sam = new User(11, "sam", "davenport");
      IUser cameron = new User(12, "cameron", "green");
      IUser teddy = new User(13, "teddy", "donahue");
      IUser lily = new User(14, "lily", "grayson");
      IUser catherine = new User(15, "catherine", "frank");

      users.add(liza);
      users.add(ben);
      users.add(max);
      users.add(emma);
      users.add(nate);
      users.add(rachel);
      users.add(arianna);
      users.add(chris);
      users.add(james);
      users.add(kelly);
      users.add(sam);
      users.add(catherine);
      users.add(lily);
      users.add(teddy);
      users.add(cameron);

      //Declare a list of vehicles. Instantiate at least 10 vehicles (Taxis and Shuttles) and 
      //place them at random locations of the city map
      
      List<IVehicle> vehicles = new ArrayList<IVehicle>();

      IVehicle car1 = new Shuttle(1, ApplicationLibrary.randomLocation());
      IVehicle car2 = new Taxi(2, ApplicationLibrary.randomLocation());
      IVehicle car3 = new Taxi(3, ApplicationLibrary.randomLocation());
      IVehicle car4 = new Taxi(4, ApplicationLibrary.randomLocation());
      IVehicle car5 = new Shuttle(5, ApplicationLibrary.randomLocation());
      IVehicle car6 = new Shuttle(6, ApplicationLibrary.randomLocation());
      IVehicle car7 = new Shuttle(7, ApplicationLibrary.randomLocation());
      IVehicle car8 = new Taxi(8, ApplicationLibrary.randomLocation());
      IVehicle car9 = new Taxi(9, ApplicationLibrary.randomLocation());
      IVehicle car10 = new Taxi(10, ApplicationLibrary.randomLocation());

      vehicles.add(car1);
      vehicles.add(car2);
      vehicles.add(car3);
      vehicles.add(car4);
      vehicles.add(car5);
      vehicles.add(car6);
      vehicles.add(car7);
      vehicles.add(car8);
      vehicles.add(car9);
      vehicles.add(car10);

      TaxiCompany taxify = new TaxiCompany("Taxify", users, vehicles);
      ApplicationSimulator application = new ApplicationSimulator(taxify, users, vehicles);
      taxify.addObserver(application);

      
      application.show();
      for (int i=1; i<=5; i++){
      application.requestService();
      }

      do {

			application.show();
			application.update();
			
			if (ApplicationLibrary.rand() % 4 == 0)
				application.requestService();
			
		} while (application.getTotalServices() != 0);
		
		application.showStatistics();


    }}

      
