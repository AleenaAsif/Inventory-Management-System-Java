package com.example.inventorymanagementsystem.Services;

import com.example.inventorymanagementsystem.DatabaseConnectionManager;
import com.example.inventorymanagementsystem.Domains.InventoryDomain;
import com.example.inventorymanagementsystem.Domains.ItemCategoryDomain;
import com.example.inventorymanagementsystem.Domains.ItemLocationDomain;
import com.example.inventorymanagementsystem.Services.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger("LoggerFile");
    private final ItemCategoryService itemCategoryService = new ItemCategoryService();
    private final ItemLocationService itemLocationService = new ItemLocationService();

    private final SQLQueries queries = new SQLQueries();

    public List<InventoryDomain> fetchAllInventories() {
        List<InventoryDomain> inventories = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getAllInventories);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                InventoryDomain inventory = new InventoryDomain();
                inventory.setId(resultSet.getInt("id"));
                inventory.setItemName(resultSet.getString("item_name"));
                inventory.setItemQuantity(resultSet.getInt("item_quantity"));

                int categoryId = resultSet.getInt("item_category_id");
                ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId);
                inventory.setItemCategory(itemCategory.getCategoryName()); // Use the appropriate property

                int locationId = resultSet.getInt("item_location_id");
                ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                inventory.setItemLocation(itemLocation.getLocationName());


                inventories.add(inventory);
            }
        } catch (Exception e) {
            logger.error("Failed to get all inventories from the database: InventoryService");
            e.printStackTrace();
        }
        return inventories;
    }


    public InventoryDomain fetchInventoryById(int inventoryId) {
        InventoryDomain inventoryDomain = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getInventoryById)) {

            statement.setInt(1, inventoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    inventoryDomain = new InventoryDomain();
                    inventoryDomain.setId(resultSet.getInt("id"));
                    inventoryDomain.setItemName(resultSet.getString("item_name"));
                    inventoryDomain.setItemQuantity(resultSet.getInt("item_quantity"));

                    int categoryId = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId);
                    inventoryDomain.setItemCategory(itemCategory.getCategoryName()); // Use the appropriate property

                    int locationId = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                    inventoryDomain.setItemLocation(itemLocation.getLocationName()); // Use the appropriate property
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            logger.error("Failed to get inventory by id from database: InventoryService");
            e.printStackTrace();
        }
        return inventoryDomain;
    }


    public List<InventoryDomain> fetchInventoriesByCategory(int categoryId) {
        List<InventoryDomain> inventories = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getInventoryByCategory)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDomain inventory = new InventoryDomain();
                    inventory.setId(resultSet.getInt("id"));
                    inventory.setItemName(resultSet.getString("item_name"));
                    inventory.setItemQuantity(resultSet.getInt("item_quantity"));

                    int categoryId2 = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId2);
                    inventory.setItemCategory(String.valueOf(itemCategory.getCategoryName())); // Use the appropriate property

                    int locationId = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                    inventory.setItemLocation(String.valueOf(itemLocation.getLocationName())); // Use the appropriate property

                    inventories.add(inventory);
                }
            } catch (Exception e) {
                logger.error("Failed to get inventories by category id from database: InventoryService");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Failed to get inventories by category id from database: InventoryService");
            e.printStackTrace();
        }
        return inventories;
    }

    public List<InventoryDomain> fetchInventoriesByLocation(int locationId) {
        List<InventoryDomain> inventories = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getInventoryByLocation)) {
            statement.setInt(1, locationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDomain inventory = new InventoryDomain();
                    inventory.setId(resultSet.getInt("id"));
                    inventory.setItemName(resultSet.getString("item_name"));
                    inventory.setItemQuantity(resultSet.getInt("item_quantity"));

                    int categoryId2 = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId2);
                    inventory.setItemCategory(String.valueOf(itemCategory.getCategoryName()));

                    int locationId2 = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                    inventory.setItemLocation(String.valueOf(itemLocation.getLocationName()));

                    inventories.add(inventory);
                }
            } catch (Exception e) {
                logger.error("Failed to get inventories by category id from database: InventoryService");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Failed to get inventories by category id from database: InventoryService");
            e.printStackTrace();
        }
        return inventories;
    }
    public List<InventoryDomain> fetchInventoriesByLocationAndCategory(int locationId, int categoryId) {
        List<InventoryDomain> inventories = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getInventoryByLocationAndCategory)) {
            statement.setInt(1, locationId);
            statement.setInt(2, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryDomain inventory = new InventoryDomain();
                    inventory.setId(resultSet.getInt("id"));
                    inventory.setItemName(resultSet.getString("item_name"));
                    inventory.setItemQuantity(resultSet.getInt("item_quantity"));

                    int categoryId2 = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId2);
                    inventory.setItemCategory(itemCategory.getCategoryName()); // Set the actual category name

                    int locationId2 = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId2);
                    inventory.setItemLocation(itemLocation.getLocationName()); // Set the actual location name

                    inventories.add(inventory);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to fetch inventories by location and category from database: InventoryService");
            e.printStackTrace();
        }

        return inventories;
    }


    public Response addNewInventoryItem(InventoryDomain inventoryDomain) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.addInventory, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, inventoryDomain.getItemName());
            statement.setInt(2, inventoryDomain.getItemQuantity());

            int categoryId = getCategoryIDByName(inventoryDomain.getItemCategory());
            int locationId = getLocationIDByName(inventoryDomain.getItemLocation());

            statement.setInt(3, categoryId);
            statement.setInt(4, locationId);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        inventoryDomain.setId(generatedId);
                        logger.info("Added new inventory item with ID: " + generatedId);
                        return Response.status(Response.Status.CREATED).entity(inventoryDomain).build();
                    } else {
                        logger.error("Failed to retrieve generated ID for new inventory item");
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                }
            } else {
                logger.error("Failed to add new inventory item");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            logger.error("Failed to add new inventory item: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    public Response updateInventory(int inventoryId, InventoryDomain inventoryDomain) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.updateInventory)) {

            statement.setString(1, inventoryDomain.getItemName());
            statement.setInt(2, inventoryDomain.getItemQuantity());

            int categoryId = getCategoryIDByName(inventoryDomain.getItemCategory());
            statement.setInt(3, categoryId);

            int locationId = getLocationIDByName(inventoryDomain.getItemLocation());
            statement.setInt(4, locationId);

            statement.setInt(5, inventoryId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                logger.info("Updated inventory with ID: " + inventoryId);
                // Create a success response with the updated inventoryDomain
                return Response.ok(inventoryDomain).build();
            } else {
                logger.error("Failed to update inventory with ID: " + inventoryId);
                // Return a NOT_FOUND response
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Failed to update inventory with ID " + inventoryId + ": " + e.getMessage());
            // Return an INTERNAL_SERVER_ERROR response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    public boolean deleteInventory(int inventoryId) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.deleteInventory)) {
            statement.setInt(1, inventoryId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Failed to delete inventory from database: InventoryService");
            e.printStackTrace();
            return false;
        }
    }

    private int getCategoryIDByName(String categoryName) {
        int categoryId = -1; // Default value if not found

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getCategoryIdByName)) {

            statement.setString(1, categoryName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoryId = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            logger.error("Failed to get category ID by name: " + e.getMessage());
        }

        return categoryId;
    }


    private int getLocationIDByName(String locationName) {
        int locationId = -1; 

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getLocationIdByName)) {

            statement.setString(1, locationName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    locationId = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            logger.error("Failed to get location ID by name: " + e.getMessage());
        }

        return locationId;
    }



}



