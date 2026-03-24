
import java.util.*;

// Reservation represents a guest’s intent to book a room
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation Request -> Guest: " + guestName + ", Room Type: " + roomType;
    }
}

        // Booking Request Queue (FIFO)
        class BookingRequestQueue {
            private Queue<Reservation> requestQueue;

            public BookingRequestQueue() {
                requestQueue = new LinkedList<>();
            }

            // Accept booking request
            public void addRequest(Reservation reservation) {
                requestQueue.offer(reservation); // FIFO insertion
                System.out.println("Request added: " + reservation);
            }

            // View all queued requests (without processing)
            public void viewRequests() {
                System.out.println("\n--- Current Booking Requests in Queue ---");
                for (Reservation r : requestQueue) {
                    System.out.println(r);
                }
            }

            // Retrieve next request (for later allocation system)
            public Reservation getNextRequest() {
                return requestQueue.poll(); // FIFO retrieval
            }

            public boolean hasRequests() {
                return !requestQueue.isEmpty();
            }
        }

        // Main Program
        public class APPFORBOOKINGSTAY {
            public static void main(String[] args) {
                BookingRequestQueue bookingQueue = new BookingRequestQueue();

                // Guests submit booking requests
                bookingQueue.addRequest(new Reservation("Alice", "Deluxe"));
                bookingQueue.addRequest(new Reservation("Bob", "Standard"));
                bookingQueue.addRequest(new Reservation("Charlie", "Suite"));

                // View queued requests (no inventory mutation yet)
                bookingQueue.viewRequests();

                // Demonstrate FIFO order retrieval
                System.out.println("\n--- Processing Requests in FIFO Order ---");
                while (bookingQueue.hasRequests()) {
                    Reservation next = bookingQueue.getNextRequest();
                    System.out.println("Processing: " + next);
                }
            }
        }

