import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String userID;
    private String email;
    private String name;
    private String phone;
    private final String password;
    private String address;
    private int age;
    private List<FlightReservation> reservations;

    public Customer(String userID, String name, String email, String password, String phone, String address, int age) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.reservations = new ArrayList<>();
    }

    public String getUserID() { return userID; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public List<FlightReservation> getReservations() { return reservations; }

    public void addReservation(FlightReservation reservation) {
        reservations.add(reservation);
    }

    public void cancelReservation(FlightReservation reservation) {
        reservations.remove(reservation);
    }
}
