import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private List<Flight> flights = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(String flightId, String source, String destination, int seats) {
        flights.add(new Flight(flightId, source, destination, seats));
        System.out.println("✅ Flight Added Successfully!");
    }

    public void displayFlights() {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
