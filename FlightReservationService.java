import java.util.List;

public class FlightReservationService {
    private List<Flight> availableFlights;

    public FlightReservationService(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    // ✅ Fixed: Proper flight booking logic
    public boolean bookFlight(Customer customer, String flightId, int numOfSeats) {
        for (Flight flight : availableFlights) {
            if (flight.getFlightNumber() && flight.getAvailableSeats() >= numOfSeats) {
                flight.setAvailableSeats(flight.getAvailableSeats() - numOfSeats);
                FlightReservation reservation = new FlightReservation(flight, numOfSeats, customer);
                customer.addReservation(reservation);
                System.out.println("✅ Booking Successful!");
                return true;
            }
        }
        System.out.println("❌ Booking Failed! Not enough seats available.");
        return false;
    }

    // ✅ Fixed: Proper seat update logic when canceling
    public boolean cancelReservation(Customer customer, String flightId) {
        FlightReservation reservationToRemove = null;

        for (FlightReservation reservation : customer.getReservations()) {
            if (reservation.getFlight().getFlightNumber()) {
                reservation.getFlight().setAvailableSeats(
                        reservation.getFlight().getAvailableSeats() + reservation.getNumberOfSeats()
                );
                reservationToRemove = reservation;
                break;
            }
        }

        if (reservationToRemove != null) {
            customer.getReservations().remove(reservationToRemove);
            System.out.println("✅ Reservation Canceled Successfully!");
            return true;
        }
        System.out.println("❌ No Reservation Found!");
        return false;
    }

    // ✅ Fixed: Display proper reservation details
    public void displayReservations(Customer customer) {
        for (FlightReservation reservation : customer.getReservations()) {
            System.out.println("Flight ID: " + reservation.getFlight().getFlightNumber() +
                    ", Seats: " + reservation.getNumberOfSeats());
        }
    }

    public void displayAvailableFlights() {
    }

    public boolean addFlight(String id, String source, String destination, int seats) {
        return false;
    }
}
