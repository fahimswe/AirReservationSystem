import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private List<Flight> flights;

    public FlightService() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean removeFlight(String flightNumber) {
        return flights.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public List<Flight> getAllFlights() {
        return flights;
    }
}
