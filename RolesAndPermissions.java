public class RolesAndPermissions {
    private AuthService authService;
    private CustomerService customerService;

    public RolesAndPermissions(AuthService authService, CustomerService customerService) {
        this.authService = authService;
        this.customerService = customerService;
    }

    public boolean isPrivilegedUserOrNot(String username, String password) {
        return authService.authenticateAdmin(username, password);
    }

    public String isPassengerRegistered(String email, String password) {
        Customer customer = customerService.findCustomerByEmailAndPassword(email, password);
        return (customer != null) ? "1-" + customer.getUserID() : "0";
    }
}
