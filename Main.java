import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize system components
        List<Flight> flights = new ArrayList<>();
        AuthService authService = new AuthService();
        CustomerService customerService = new CustomerService(new ArrayList<>());
        FlightReservationService flightService = new FlightReservationService(flights);
        RolesAndPermissions roles = new RolesAndPermissions(authService, customerService);

        // Sample Admin Account
        authService.registerAdmin("admin", "admin123");

        while (true) {
            System.out.println("\n🔹 Airline Reservation System 🔹");
            System.out.println("1. Admin Login");
            System.out.println("2. Passenger Registration");
            System.out.println("3. Passenger Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> adminLogin(scanner, roles, flightService);
                case 2 -> registerPassenger(scanner, customerService);
                case 3 -> passengerLogin(scanner, customerService, flightService);
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ✅ Admin Login & Flight Management
    private static void adminLogin(Scanner scanner, RolesAndPermissions roles, FlightReservationService flightService) {
        System.out.print("\n🔑 Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("🔒 Password: ");
        String password = scanner.nextLine();

        if (roles.isPrivilegedUser(username, password)) {
            System.out.println("\n✅ Admin Login Successful!");

            while (true) {
                System.out.println("\n🔹 Flight Management 🔹");
                System.out.println("1. View Flights");
                System.out.println("2. Add Flight");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");

                int adminChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (adminChoice) {
                    case 1 -> flightService.displayAvailableFlights();
                    case 2 -> addFlight(scanner, flightService);
                    case 3 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("❌ Invalid Credentials!");
        }
    }

    // ✅ Add a new flight (Admin Only)
    private static void addFlight(Scanner scanner, FlightReservationService flightService) {
        System.out.print("\nEnter Flight ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Available Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (flightService.addFlight(id, source, destination, seats)) {
            System.out.println("✅ Flight Added Successfully!");
        } else {
            System.out.println("❌ Flight ID already exists! Try another ID.");
        }
    }

    // ✅ Passenger Registration
    private static void registerPassenger(Scanner scanner, CustomerService customerService) {
        System.out.print("\n📩 Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("👤 Enter Name: ");
        String name = scanner.nextLine();

        if (customerService.isEmailRegistered(email)) {
            System.out.println("❌ Email already registered! Try logging in.");
        } else {
            Customer newCustomer = new Customer(name, email, password);
            customerService.addCustomer(newCustomer);
            System.out.println("✅ Registration Successful! Your User ID: " + newCustomer.getUserID());
        }
    }

    // ✅ Passenger Login & Flight Booking
    private static void passengerLogin(Scanner scanner, CustomerService customerService, FlightReservationService flightService) {
        System.out.print("\n📩 Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = customerService.findCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            System.out.println("\n✅ Login Successful!");
            while (true) {
                System.out.println("\n🔹 Passenger Dashboard 🔹");
                System.out.println("1. View Available Flights");
                System.out.println("2. Book a Flight");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. View My Reservations");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1 -> flightService.displayAvailableFlights();
                    case 2 -> bookFlight(scanner, flightService, customer);
                    case 3 -> cancelReservation(scanner, flightService, customer);
                    case 4 -> flightService.displayReservations(customer);
                    case 5 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("❌ Invalid Email or Password!");
        }
    }

    // ✅ Book a Flight
    private static void bookFlight(Scanner scanner, FlightReservationService flightService, Customer customer) {
        System.out.print("\nEnter Flight ID to book: ");
        String flightId = scanner.nextLine();
        System.out.print("Enter Number of Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (flightService.bookFlight(customer, flightId, seats)) {
            System.out.println("✅ Booking Successful!");
        } else {
            System.out.println("❌ Booking Failed! Check seat availability.");
        }
    }

    // ✅ Cancel a Reservation
    private static void cancelReservation(Scanner scanner, FlightReservationService flightService, Customer customer) {
        System.out.print("\nEnter Flight ID to cancel: ");
        String flightId = scanner.nextLine();

        if (flightService.cancelReservation(customer, flightId)) {
            System.out.println("✅ Reservation Canceled Successfully!");
        } else {
            System.out.println("❌ No Reservation Found!");
        }
    }
}
