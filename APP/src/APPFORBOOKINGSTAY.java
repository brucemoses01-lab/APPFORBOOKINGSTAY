
import java.util.*;

// Domain Model: Room
class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return "Room Type: " + type +
                ", Price: $" + price +
                ", Amenities: " + amenities;
    }
}

        // Inventory acts as State Holder
        class Inventory {
            private Map<String, Integer> availability;

            public Inventory() {
                availability = new HashMap<>();
            }

            public void addRoomType(String type, int count) {
                availability.put(type, count);
            }

            public int getAvailability(String type) {
                return availability.getOrDefault(type, 0);
            }

            public Map<String, Integer> getAllAvailability() {
                // Defensive copy to ensure read-only access
                return new HashMap<>(availability);
            }
        }

        // Search Service (Read-only access)
        class SearchService {
            private Inventory inventory;
            private Map<String, Room> roomCatalog;

            public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
                this.inventory = inventory;
                this.roomCatalog = roomCatalog;
            }

            public List<Room> searchAvailableRooms() {
                List<Room> availableRooms = new ArrayList<>();
                Map<String, Integer> currentAvailability = inventory.getAllAvailability();

                for (Map.Entry<String, Integer> entry : currentAvailability.entrySet()) {
                    String type = entry.getKey();
                    int count = entry.getValue();

                    // Validation Logic: Only include rooms with availability > 0
                    if (count > 0 && roomCatalog.containsKey(type)) {
                        availableRooms.add(roomCatalog.get(type));
                    }
                }
                return availableRooms;
            }
        }

        // Main Program
        public class APPFORBOOKINGSTAY {
            public static void main(String[] args) {
                // Step 1: Setup Inventory
                Inventory inventory = new Inventory();
                inventory.addRoomType("Deluxe", 3);
                inventory.addRoomType("Suite", 0); // unavailable
                inventory.addRoomType("Standard", 5);

                // Step 2: Setup Room Catalog
                Map<String, Room> roomCatalog = new HashMap<>();
                roomCatalog.put("Deluxe", new Room("Deluxe", 150.0, "WiFi, TV, Mini-bar"));
                roomCatalog.put("Suite", new Room("Suite", 300.0, "WiFi, TV, Mini-bar, Jacuzzi"));
                roomCatalog.put("Standard", new Room("Standard", 100.0, "WiFi, TV"));

                // Step 3: Search Service
                SearchService searchService = new SearchService(inventory, roomCatalog);

                // Step 4: Guest initiates search
                System.out.println("Available Rooms:");
                List<Room> availableRooms = searchService.searchAvailableRooms();
                for (Room room : availableRooms) {
                    System.out.println(room);
                }
            }
        }

