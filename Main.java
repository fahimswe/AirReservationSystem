import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize Services
        List<Flight> flights = new ArrayList<>();
        AuthService authService = new AuthService();
        CustomerService customerService = new CustomerService(new ArrayList<>());
        FlightReservationService flightService = new FlightReservationService(flights);
        RolesAndPermissions roles = new RolesAndPermissions(authService, customerService);

        // Sample Admin
        authService.registerAdmin("admin", "admin123");

        while (true) {
            System.out.println("\n🔹 Airline Reservation System 🔹");
            System.out.println("1. Admin Login");
            System.out.println("2. Passenger Registration");
            System.out.println("3. Passenger Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> new AdminController(scanner, roles, flightService).handleAdmin();
                case 2 -> new PassengerController(scanner, customerService).registerPassenger();
                case 3 -> new PassengerController(scanner, customerService, flightService).handlePassenger();
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
