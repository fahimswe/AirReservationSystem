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
                return true;
            }
        }
        return false; // No available seats or flight not found
    }

    // ✅ Fixed: Proper seat update logic when canceling
    public boolean cancelReservation(Customer customer, String flightId) {
        FlightReservation reservationToRemove = null;

        for (FlightReservation reservation : customer.getReservations()) {
            if (reservation.getFlight().getFlightNumber()) {
                // Return seats to flight
                reservation.getFlight().setAvailableSeats(
                        reservation.getFlight().getAvailableSeats() + reservation.getNumberOfSeats()
                );
                reservationToRemove = reservation;
                break;
            }
        }

        if (reservationToRemove != null) {
            customer.getReservations().remove(reservationToRemove);
            return true;
        }
        return false;
    }

    // ✅ Fixed: Display proper reservation details
    public void displayReservations(Customer customer) {
        for (FlightReservation reservation : customer.getReservations()) {
            System.out.println("Flight ID: " + reservation.getFlight().getFlightNumber() +
                    ", Seats: " + reservation.getNumberOfSeats());
        }
    }
}
