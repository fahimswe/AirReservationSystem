import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        CustomerService customerService = new CustomerService();

        System.out.println("Welcome to the Airline Reservation System!");

        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.authenticateAdmin(username, password)) {
            System.out.println("Login successful!");
            // Continue with system operations...
        } else {
            System.out.println("Invalid credentials!");
        }

        scanner.close();
    }
}
