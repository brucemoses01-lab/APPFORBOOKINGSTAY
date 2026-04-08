
import java.util.*;

// Add-On Service represents an optional offering
class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " ($" + cost + ")";
    }
}

        // Reservation represents a confirmed booking
        class Reservation {
            private String reservationId;
            private String guestName;
            private String roomType;

            public Reservation(String reservationId, String guestName, String roomType) {
                this.reservationId = reservationId;
                this.guestName = guestName;
                this.roomType = roomType;
            }

            public String getReservationId() {
                return reservationId;
            }

            public String getGuestName() {
                return guestName;
            }

            public String getRoomType() {
                return roomType;
            }

            @Override
            public String toString() {
                return "Reservation -> ID: " + reservationId +
                        ", Guest: " + guestName +
                        ", Room Type: " + roomType;
            }
        }

        // Add-On Service Manager maps reservations to selected services
        class AddOnServiceManager {
            private Map<String, List<AddOnService>> serviceMap;

            public AddOnServiceManager() {
                serviceMap = new HashMap<>();
            }

            // Attach services to a reservation
            public void addServices(String reservationId, List<AddOnService> services) {
                serviceMap.putIfAbsent(reservationId, new ArrayList<>());
                serviceMap.get(reservationId).addAll(services);
                System.out.println("Services added for Reservation ID: " + reservationId);
            }

            // View services for a reservation
            public List<AddOnService> getServices(String reservationId) {
                return serviceMap.getOrDefault(reservationId, Collections.emptyList());
            }

            // Calculate total additional cost
            public double calculateAdditionalCost(String reservationId) {
                return getServices(reservationId).stream()
                        .mapToDouble(AddOnService::getCost)
                        .sum();
            }
        }

        // Main Program
        public class APPFORBOOKINGSTAY {
            public static void main(String[] args) {
                // Step 1: Create Reservations
                Reservation res1 = new Reservation("R001", "Alice", "Deluxe");
                Reservation res2 = new Reservation("R002", "Bob", "Standard");

                // Step 2: Define Add-On Services
                AddOnService breakfast = new AddOnService("Breakfast", 20.0);
                AddOnService airportPickup = new AddOnService("Airport Pickup", 50.0);
                AddOnService spaAccess = new AddOnService("Spa Access", 40.0);

                // Step 3: Manage Add-On Services
                AddOnServiceManager manager = new AddOnServiceManager();

                // Guest Alice selects Breakfast + Spa
                manager.addServices(res1.getReservationId(), Arrays.asList(breakfast, spaAccess));

                // Guest Bob selects Airport Pickup
                manager.addServices(res2.getReservationId(), Arrays.asList(airportPickup));

                // Step 4: Display results
                System.out.println("\n--- Reservation Details with Add-On Services ---");
                System.out.println(res1);
                System.out.println("Selected Services: " + manager.getServices(res1.getReservationId()));
                System.out.println("Additional Cost: $" + manager.calculateAdditionalCost(res1.getReservationId()));

                System.out.println();
                System.out.println(res2);
                System.out.println("Selected Services: " + manager.getServices(res2.getReservationId()));
                System.out.println("Additional Cost: $" + manager.calculateAdditionalCost(res2.getReservationId()));
            }
        }

