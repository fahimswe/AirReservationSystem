public interface Reservable {
    boolean bookFlight(Customer customer, String flightId, int numOfSeats);
    boolean cancelReservation(Customer customer, String flightId);
}
