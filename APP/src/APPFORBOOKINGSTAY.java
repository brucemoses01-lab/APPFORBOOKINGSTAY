




import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

        // Reservation represents a booking request
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

        // Inventory holds room availability
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

            public void updateAvailability(String type, int change) throws InvalidBookingException {
                int current = getAvailability(type);
                int newCount = current + change;
                if (newCount < 0) {
                    throw new InvalidBookingException("Error: Cannot reduce availability below zero for room type: " + type);
                }
                availability.put(type, newCount);
            }

            public boolean isValidRoomType(String type) {
                return availability.containsKey(type);
            }
        }

        // Validator checks booking input
        class BookingValidator {
            private Inventory inventory;

            public BookingValidator(Inventory inventory) {
                this.inventory = inventory;
            }

            public void validate(Reservation reservation) throws InvalidBookingException {
                String roomType = reservation.getRoomType();

                // Validate room type exists
                if (!inventory.isValidRoomType(roomType)) {
                    throw new InvalidBookingException("Error: Invalid room type '" + roomType + "'.");
                }

                // Validate availability
                if (inventory.getAvailability(roomType) <= 0) {
                    throw new InvalidBookingException("Error: No availability for room type '" + roomType + "'.");
                }
            }
        }

        // Booking Processor applies validation before confirming
        class BookingProcessor {
            private Inventory inventory;
            private BookingValidator validator;

            public BookingProcessor(Inventory inventory) {
                this.inventory = inventory;
                this.validator = new BookingValidator(inventory);
            }

            public void processBooking(Reservation reservation) {
                try {
                    // Validate input
                    validator.validate(reservation);

                    // Update inventory safely
                    inventory.updateAvailability(reservation.getRoomType(), -1);

                    System.out.println("Booking Confirmed: " + reservation);
                } catch (InvalidBookingException e) {
                    // Graceful failure handling
                    System.out.println(e.getMessage());
                }
            }
        }

        // Main Program
        public class APPFORBOOKINGSTAY {
            public static void main(String[] args) {
                // Step 1: Setup Inventory
                Inventory inventory = new Inventory();
                inventory.addRoomType("Deluxe", 2);
                inventory.addRoomType("Standard", 1);

                // Step 2: Booking Processor
                BookingProcessor processor = new BookingProcessor(inventory);

                // Step 3: Guests attempt bookings
                Reservation res1 = new Reservation("R001", "Alice", "Deluxe");
                Reservation res2 = new Reservation("R002", "Bob", "Standard");
                Reservation res3 = new Reservation("R003", "Charlie", "Suite"); // invalid room type
                Reservation res4 = new Reservation("R004", "David", "Standard"); // overbooking

                processor.processBooking(res1); // valid
                processor.processBooking(res2); // valid
                processor.processBooking(res3); // invalid room type
                processor.processBooking(res4); // no availability
            }
        }

