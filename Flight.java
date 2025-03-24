import java.util.ArrayList;
import java.util.List;

public class Flight {
    private List<Flight> flights;

    public Flight(String f101, String newYork, String london, int i) {
    }

    public void FlightService() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean removeFlight(String flightNumber) {
        return flights.removeIf(flight -> flight.getFlightNumber());
    }

    boolean getFlightNumber() {
        return false;
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber()) {
                return flight;
            }
        }
        return null;
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public int getAvailableSeats() {
        return 0;
    }

    public void setAvailableSeats(int i) {
    }
}
