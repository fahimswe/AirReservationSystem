import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = customers;
    }

    public CustomerService(ArrayList<Object> objects) {
    }

    public Customer findCustomerByEmailAndPassword(String email, String password) {
        for (Customer c : customers) {
            if (email.equals(c.getEmail()) && password.equals(c.getPassword())) {
                return c;
            }
        }
        return null;
    }

    public void addCustomer(Customer newCustomer) {
    }
}
