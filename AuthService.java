import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, String> adminCredentials;
    private Map<String, Role> userRoles;

    public AuthService() {
        this.adminCredentials = new HashMap<>();
        this.userRoles = new HashMap<>();

        // Default admin account
        adminCredentials.put("admin", "password123");
        userRoles.put("admin", Role.ADMIN);
    }

    public boolean authenticateAdmin(String username, String password) {
        return adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password);
    }

    public void registerAdmin(String username, String password) {
        adminCredentials.put(username, password);
        userRoles.put(username, Role.ADMIN);
    }

    public Role getUserRole(String username) {
        return userRoles.getOrDefault(username, Role.CUSTOMER);
    }
}
