package com.example.inventorymanagementsystem.Services;

import com.example.inventorymanagementsystem.DatabaseConnectionManager;
import com.example.inventorymanagementsystem.Domains.ItemLocationDomain;
import com.example.inventorymanagementsystem.Services.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ItemLocationService {
    private static final Logger logger = LoggerFactory.getLogger("LoggerFile");
    public ItemLocationDomain getItemLocationById(int locationId) {
        ItemLocationDomain itemLocation = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getItemLocationById)) {

            statement.setInt(1, locationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    itemLocation = new ItemLocationDomain();
                    itemLocation.setId(resultSet.getInt("id"));
                    itemLocation.setLocationName(resultSet.getString("location_name"));
                    logger.info("got item by id: ItemLocationervice");
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to get item by id: ItemLocationService");
            e.printStackTrace();

        }

        return itemLocation;
    }


}
