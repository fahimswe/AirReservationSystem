import java.util.Scanner;

public class AdminController {
    private final Scanner scanner;
    private final RolesAndPermissions roles;
    private final FlightReservationService flightService;

    public AdminController(Scanner scanner, RolesAndPermissions roles, FlightReservationService flightService) {
        this.scanner = scanner;
        this.roles = roles;
        this.flightService = flightService;
    }

    public void handleAdmin() {
        System.out.print("\n Admin Username: ");
        String username = scanner.nextLine();
        System.out.print(" Password: ");
        String password = scanner.nextLine();

        if (!roles.isPrivilegedUser(username, password)) {
            System.out.println(" Invalid Credentials!");
            return;
        }

        System.out.println("\n Admin Login Successful!");
        while (true) {
            System.out.println("\n🔹 Flight Management 🔹");
            System.out.println("1. View Flights");
            System.out.println("2. Add Flight");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> flightService.displayAvailableFlights();
                case 2 -> addFlight();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addFlight() {
        System.out.print("\nEnter Flight ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Available Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();

        if (flightService.addFlight(id, source, destination, seats)) {
            System.out.println(" Flight Added Successfully!");
        } else {
            System.out.println(" Flight ID already exists! Try another ID.");
        }
    }
}
