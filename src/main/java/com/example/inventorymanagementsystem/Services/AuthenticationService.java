package com.example.inventorymanagementsystem.Services;

import com.example.inventorymanagementsystem.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger("LoggerFile");

        public String getUserRole(String username, String password) {
            try (Connection connection = DatabaseConnectionManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQLQueries.userCheck)) {

                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        logger.info("got user role : AuthenticationService");
                        return resultSet.getString("role");
                    }
                }
            } catch (Exception e) {
                logger.error("Invalid user credentials: AuthenticationService");
                e.printStackTrace();
            }

            return null;
        }
}




