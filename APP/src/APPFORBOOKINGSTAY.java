/**
 * Abstract class representing a generic Room.
 * Defines common properties and behavior.
 */
abstract class Room {

    protected int beds;
    protected double price;
    protected String type;

    /**
     * Abstract method to display room details.
     * Must be implemented by subclasses.
     */
    public abstract void displayDetails();
}
/**
 * ============================================================
 * MAIN CLASS - UseCase2HotelBookingApp
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates object-oriented design using
 * abstraction, inheritance, and polymorphism.
 *
 * At this stage, the application:
 * - Creates different types of Room objects
 * - Stores availability using simple variables
 * - Displays room details and availability
 *
 * No data structures are used in this use case.
 *
 * The goal is to establish domain modeling before
 * introducing inventory management.
 *
 * @author Bruce
 * @version 2.0
 */

public class APPFORBOOKINGSTAY{

    /**
     * Application entry point.
     *
     * This method initializes room objects and displays
     * their details along with availability.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Static availability variables
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display Room Details
        System.out.println("=========== ROOM DETAILS ===========");

        single.displayDetails();
        System.out.println("Available: " + singleRoomAvailable + "\n");

        doub.displayDetails();
        System.out.println("Available: " + doubleRoomAvailable + "\n");

        suite.displayDetails();
        System.out.println("Available: " + suiteRoomAvailable + "\n");

        System.out.println("====================================");
    }
}
/**
 * Represents a Single Room type.
 */
class SingleRoom extends Room {

    public SingleRoom() {
        beds = 1;
        price = 1000;
        type = "Single Room";
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: " + price);
    }
}
/**
 * Represents a Double Room type.
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        beds = 2;
        price = 2000;
        type = "Double Room";
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: " + price);
    }
}
/**
 * Represents a Suite Room type.
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        beds = 3;
        price = 5000;
        type = "Suite Room";
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: " + price);
    }
}


