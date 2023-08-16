
import com.example.inventorymanagementsystem.Services.AuthenticationService;
import com.example.inventorymanagementsystem.DatabaseConnectionManager;
import com.example.inventorymanagementsystem.Services.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationServiceTest {

    private AuthenticationService authService = new AuthenticationService();

    public String getUserRole(String username, String password) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.userCheck)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        AuthenticationServiceTest test = new AuthenticationServiceTest();

        String username = "admin";
        String password = "admin";

        String userRole = test.getUserRole(username, password);

        System.out.println("User Role: " + userRole);
    }
}
