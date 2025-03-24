import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample flight data
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F101", "New York", "London", 50));
        flights.add(new Flight("F202", "Paris", "Tokyo", 30));

        // Initialize services
        AuthService authService = new AuthService();
        CustomerService customerService = new CustomerService(new ArrayList<>());
        FlightReservationService flightService = new FlightReservationService(flights);
        RolesAndPermissions roles = new RolesAndPermissions(authService, customerService);

        // Sample Admin Registration
        authService.registerAdmin("admin", "admin123");

        // Application Menu
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
                case 1:
                    adminLogin(scanner, roles, flights);
                    break;
                case 2:
                    registerPassenger(scanner, customerService);
                    break;
                case 3:
                    passengerLogin(scanner, customerService, flightService);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ✅ Admin Login & Flight Management
    private static void adminLogin(Scanner scanner, RolesAndPermissions roles, List<Flight> flights) {
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

                if (adminChoice == 1) {
                    for (Flight flight : flights) {
                        System.out.println(flight);
                    }
                } else if (adminChoice == 2) {
                    System.out.print("\nEnter Flight ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Source: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter Available Seats: ");
                    int seats = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    flights.add(new Flight(id, source, destination, seats));
                    System.out.println("✅ Flight Added Successfully!");
                } else if (adminChoice == 3) {
                    System.out.println("Logging out...");
                    return;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("❌ Invalid Credentials!");
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

        Customer newCustomer = new Customer(name, email, password);
        customerService.addCustomer(newCustomer);
        System.out.println("✅ Registration Successful! Your User ID: " + newCustomer.getUserID());
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

                if (choice == 1) {
                    flightService.displayAvailableFlights();
                } else if (choice == 2) {
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
                } else if (choice == 3) {
                    System.out.print("\nEnter Flight ID to cancel: ");
                    String flightId = scanner.nextLine();

                    if (flightService.cancelReservation(customer, flightId)) {
                        System.out.println("✅ Reservation Canceled Successfully!");
                    } else {
                        System.out.println("❌ No Reservation Found!");
                    }
                } else if (choice == 4) {
                    flightService.displayReservations(customer);
                } else if (choice == 5) {
                    System.out.println("Logging out...");
                    return;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("❌ Invalid Email or Password!");
        }
    }
}
