package com.example.inventorymanagementsystem;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger("LogFile");
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventorymanagement";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final HikariDataSource dataSource;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found");
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Connection closing failed");
                e.printStackTrace();
            }
        }
    }
}
