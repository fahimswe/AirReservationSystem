import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean removeCustomer(String userID) {
        return customers.removeIf(customer -> customer.getUserID().equals(userID));
    }

    public Customer findCustomer(String userID) {
        for (Customer customer : customers) {
            if (customer.getUserID().equals(userID)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
