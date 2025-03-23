public class FlightReservation {
    private Flight flight;
    private int numberOfSeats;
    private Customer customer;

    public FlightReservation(Flight flight, int numberOfSeats, Customer customer) {
        this.flight = flight;
        this.numberOfSeats = numberOfSeats;
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Customer getCustomer() {
        return customer;
    }
}
