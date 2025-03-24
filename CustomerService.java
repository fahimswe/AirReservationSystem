import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer findCustomerByEmailAndPassword(String email, String password) {
        for (Customer c : customers) {
            if (email.equals(c.getEmail()) && password.equals(c.getPassword())) {
                return c;
            }
        }
        return null;
    }
}
