import java.util.List;

public class FlightReservationService {
    private List<Flight> availableFlights;

    public FlightReservationService(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public boolean bookFlight(Customer customer, String flightId, int numOfSeats) {
        for (Flight flight : availableFlights) {
            if (flight.getFlightId().equals(flightId) && flight.getAvailableSeats() >= numOfSeats) {
                flight.setAvailableSeats(flight.getAvailableSeats() - numOfSeats);
                FlightReservation reservation = new FlightReservation(flight, numOfSeats, customer);
                customer.addReservation(reservation);
                return true;
            }
        }
        return false;
    }

    public boolean cancelReservation(Customer customer, String flightId) {
        FlightReservation reservationToRemove = null;
        for (FlightReservation reservation : customer.getReservations()) {
            if (reservation.getFlight().getFlightId().equals(flightId)) {
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

    public void displayReservations(Customer customer) {
        for (FlightReservation reservation : customer.getReservations()) {
            System.out.println("Flight ID: " + reservation.getFlight().getFlightId() +
                    ", Seats: " + reservation.getNumberOfSeats());
        }
    }
}
