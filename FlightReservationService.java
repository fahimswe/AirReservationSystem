import java.util.List;

public class FlightReservationService {
    private List<Flight> availableFlights;

    public FlightReservationService(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public boolean bookFlight(Customer customer, String flightId, int numOfSeats) {
        for (Flight flight : availableFlights) {
            flight.getFlightNumber();
        }
        return false;
    }

    public boolean cancelReservation(Customer customer, String flightId) {
        FlightReservation reservationToRemove = null;
        for (FlightReservation reservation : customer.getReservations()) {
            if (reservation.getFlight().getFlightNumber()) {
                reservation.getFlight().getAllFlights(
                        reservation.getFlight().getAllFlights() + reservation.getNumberOfSeats()
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
            System.out.println("Flight ID: " + reservation.getFlight().getFlightNumber() +
                    ", Seats: " + reservation.getNumberOfSeats());
        }
    }
}
