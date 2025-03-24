import java.util.Scanner;

public class PassengerController {
    private final Scanner scanner;
    private final CustomerService customerService;
    private FlightReservationService flightService;

    public PassengerController(Scanner scanner, CustomerService customerService) {
        this.scanner = scanner;
        this.customerService = customerService;
    }

    public PassengerController(Scanner scanner, CustomerService customerService, FlightReservationService flightService) {
        this(scanner, customerService);
        this.flightService = flightService;
    }

    public void registerPassenger() {
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

    public void handlePassenger() {
        System.out.print("\n📩 Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = customerService.findCustomerByEmailAndPassword(email, password);

        if (customer == null) {
            System.out.println("❌ Invalid Email or Password!");
            return;
        }

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
            scanner.nextLine();

            switch (choice) {
                case 1 -> flightService.displayAvailableFlights();
                case 2 -> bookFlight(customer);
                case 3 -> cancelReservation(customer);
                case 4 -> flightService.displayReservations(customer);
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void bookFlight(Customer customer) {
        System.out.print("\nEnter Flight ID to book: ");
        String flightId = scanner.nextLine();
        System.out.print("Enter Number of Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();

        if (flightService.bookFlight(customer, flightId, seats)) {
            System.out.println("✅ Booking Successful!");
        } else {
            System.out.println("❌ Booking Failed! Check seat availability.");
        }
    }

    private void cancelReservation(Customer customer) {
        System.out.print("\nEnter Flight ID to cancel: ");
        String flightId = scanner.nextLine();

        if (flightService.cancelReservation(customer, flightId)) {
            System.out.println("✅ Reservation Canceled Successfully!");
        } else {
            System.out.println("❌ No Reservation Found!");
        }
    }
}
