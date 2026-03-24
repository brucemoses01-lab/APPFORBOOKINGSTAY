

import java.util.*;

// Reservation represents a confirmed booking
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double roomCost;

    public Reservation(String reservationId, String guestName, String roomType, double roomCost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomCost = roomCost;
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

    public double getRoomCost() {
        return roomCost;
    }

    @Override
    public String toString() {
        return "Reservation -> ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Cost: $" + roomCost;
    }
}

        // Booking History stores confirmed reservations
        class BookingHistory {
            private List<Reservation> history;

            public BookingHistory() {
                history = new ArrayList<>();
            }

            // Add confirmed reservation
            public void addReservation(Reservation reservation) {
                history.add(reservation);
                System.out.println("Reservation added to history: " + reservation);
            }

            // Retrieve all reservations
            public List<Reservation> getAllReservations() {
                return Collections.unmodifiableList(history); // read-only view
            }
        }

        // Booking Report Service generates summaries
        class BookingReportService {
            private BookingHistory bookingHistory;

            public BookingReportService(BookingHistory bookingHistory) {
                this.bookingHistory = bookingHistory;
            }

            // Generate summary report
            public void generateReport() {
                List<Reservation> reservations = bookingHistory.getAllReservations();
                System.out.println("\n--- Booking History Report ---");
                System.out.println("Total Bookings: " + reservations.size());

                double totalRevenue = 0;
                Map<String, Integer> roomTypeCount = new HashMap<>();

                for (Reservation r : reservations) {
                    System.out.println(r);
                    totalRevenue += r.getRoomCost();
                    roomTypeCount.put(r.getRoomType(), roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1);
                }

                System.out.println("\n--- Summary ---");
                System.out.println("Total Revenue: $" + totalRevenue);
                System.out.println("Bookings by Room Type: " + roomTypeCount);
            }
        }

        // Main Program
        public class APPFORBOOKINGSTAY {
            public static void main(String[] args) {
                // Step 1: Create Booking History
                BookingHistory history = new BookingHistory();

                // Step 2: Confirm Reservations
                Reservation res1 = new Reservation("R001", "Alice", "Deluxe", 150.0);
                Reservation res2 = new Reservation("R002", "Bob", "Standard", 100.0);
                Reservation res3 = new Reservation("R003", "Charlie", "Suite", 300.0);

                history.addReservation(res1);
                history.addReservation(res2);
                history.addReservation(res3);

                // Step 3: Generate Report
                BookingReportService reportService = new BookingReportService(history);
                reportService.generateReport();
            }
        }

