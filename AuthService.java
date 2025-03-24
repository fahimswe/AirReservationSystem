import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, String> adminCredentials;

    public AuthService() {
        this.adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "password123"); // Default admin credentials
    }

    public boolean authenticateAdmin(String username, String password) {
        return adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password);
    }

    public void registerAdmin(String username, String password) {
        adminCredentials.put(username, password);
    }
}
