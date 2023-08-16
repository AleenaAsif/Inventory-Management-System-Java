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
    public final ItemCategoryService itemCategoryService = new ItemCategoryService();
    public final ItemLocationService itemLocationService = new ItemLocationService();
    private final AuthenticationService authService = new AuthenticationService();

    private final SQLQueries queries = new SQLQueries();

    public List<InventoryDomain> fetchAllInventories() {
        List<InventoryDomain> inventories = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getAllInventories);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                logger.info("inside while loop, fetchedAll : InventoryService");
                ItemCategoryDomain category = new ItemCategoryDomain(resultSet.getInt("category_id"), resultSet.getString("category_name"));
                ItemLocationDomain location = new ItemLocationDomain(resultSet.getInt("location_id"), resultSet.getString("location_name"));

                InventoryDomain inventory = new InventoryDomain();
                inventory.setId(resultSet.getInt("id"));
                inventory.setItemName(resultSet.getString("item_name"));
                inventory.setItemQuantity(resultSet.getInt("item_quantity"));
                inventory.setItemCategory(category);
                inventory.setItemLocation(location);

                inventories.add(inventory);
            }

        } catch (SQLException e) {
            logger.error("Failed to get all inventories from the database: InventoryService", e);
        }

        return inventories;
    }


    public InventoryDomain fetchInventoryById(int inventoryId) {
        InventoryDomain inventoryDomain = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getInventoryById)) {
            logger.info("getting by id: inventoryService");

            statement.setInt(1, inventoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("getting by id exists: inventoryService");
                    inventoryDomain = new InventoryDomain();
                    inventoryDomain.setId(resultSet.getInt("id"));
                    inventoryDomain.setItemName(resultSet.getString("item_name"));
                    inventoryDomain.setItemQuantity(resultSet.getInt("item_quantity"));

                    int categoryId = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId);
                    inventoryDomain.setItemCategory(new ItemCategoryDomain(itemCategory.getId(), itemCategory.getCategoryName()));

                    int locationId = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                    inventoryDomain.setItemLocation(new ItemLocationDomain(itemLocation.getId(), itemLocation.getLocationName()));


                    inventoryDomain.setItemCategory(itemCategory);
                    inventoryDomain.setItemLocation(itemLocation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
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
                    inventory.setItemCategory(itemCategory);

                    int locationId = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId);
                    inventory.setItemLocation(itemLocation);

                    inventories.add(inventory);
                }
            } catch (Exception e) {
                logger.error("Failed to get inventories by category id from database: InventoryService", e);
            }
        } catch (Exception e) {
            logger.error("Failed to get inventories by category id from database: InventoryService", e);
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

                    int categoryId = resultSet.getInt("item_category_id");
                    ItemCategoryDomain itemCategory = itemCategoryService.getItemCategoryById(categoryId);
                    inventory.setItemCategory(itemCategory);

                    int locationId2 = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = itemLocationService.getItemLocationById(locationId2);
                    inventory.setItemLocation(itemLocation);

                    inventories.add(inventory);
                }
            } catch (Exception e) {
                logger.error("Failed to get inventories by location id from database: InventoryService");
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Failed to get inventories by location id from database: InventoryService");
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
                    ItemCategoryDomain itemCategory = new ItemCategoryDomain();
                    itemCategory.setId(categoryId2);
                    itemCategory.setCategoryName(resultSet.getString("category_name"));

                    int locationId2 = resultSet.getInt("item_location_id");
                    ItemLocationDomain itemLocation = new ItemLocationDomain();
                    itemLocation.setId(locationId2);
                    itemLocation.setLocationName(resultSet.getString("location_name"));

                    inventory.setItemCategory(itemCategory);
                    inventory.setItemLocation(itemLocation);

                    inventories.add(inventory);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to fetch inventories by location and category from database: InventoryService");
            e.printStackTrace();
        }

        return inventories;
    }


    public InventoryDomain addNewInventoryItem(InventoryDomain inventoryDomain) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.addInventory, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, inventoryDomain.getItemName());
            statement.setInt(2, inventoryDomain.getItemQuantity());

            ItemCategoryDomain category = getCategoryByName(inventoryDomain.getItemCategory().getCategoryName());
            ItemLocationDomain location = getLocationByName(inventoryDomain.getItemLocation().getLocationName());

            if (category != null && location != null) {
                statement.setInt(3, category.getId());
                statement.setInt(4, location.getId());

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int generatedId = generatedKeys.getInt(1);
                            inventoryDomain.setId(generatedId);
                            logger.info("Added new inventory item with ID: " + generatedId);
                            return inventoryDomain;
                        } else {
                            logger.error("Failed to retrieve generated ID for new inventory item");
                            return null;
                        }
                    }
                } else {
                    logger.error("Failed to add new inventory item");
                    return null;
                }
            } else {
                logger.error("Invalid category or location");
                return null;
            }
        } catch (Exception e) {
            logger.error("Failed to add new inventory item: " + e.getMessage());
            return null;
        }
    }



    public InventoryDomain updateInventory(int inventoryId, InventoryDomain updatedInventory) {
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.updateInventory)) {

            statement.setString(1, updatedInventory.getItemName());
            statement.setInt(2, updatedInventory.getItemQuantity());

            ItemCategoryDomain category = getCategoryByName(updatedInventory.getItemCategory().getCategoryName());
            ItemLocationDomain location = getLocationByName(updatedInventory.getItemLocation().getLocationName());

            if (category != null && location != null) {
                statement.setInt(3, category.getId());
                statement.setInt(4, location.getId());
                statement.setInt(5, inventoryId);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    updatedInventory.setId(inventoryId);
                    logger.info("Updated inventory item with ID: " + inventoryId);
                    return updatedInventory;
                } else {
                    logger.error("Failed to update inventory item");
                    return null;
                }
            } else {
                logger.error("Invalid category or location");
                return null;
            }
        } catch (Exception e) {
            logger.error("Failed to update inventory item: " + e.getMessage());
            return null;
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

    private ItemCategoryDomain getCategoryByName(String categoryName) {
        ItemCategoryDomain category = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getCategoryIdByName)) {

            statement.setString(1, categoryName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = new ItemCategoryDomain();
                    category.setId(resultSet.getInt("id"));
                    category.setCategoryName(resultSet.getString("category_name"));
                }
            }
        } catch (Exception e) {
            logger.error("Failed to get category by name: " + e.getMessage());
        }

        return category;
    }


    private ItemLocationDomain getLocationByName(String locationName) {
        ItemLocationDomain location = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getLocationIdByName)) {

            statement.setString(1, locationName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    location = new ItemLocationDomain();
                    location.setId(resultSet.getInt("id"));
                    location.setLocationName(resultSet.getString("location_name"));
                }
            }
        } catch (Exception e) {
            logger.error("Failed to get location by name: " + e.getMessage());
        }

        return location;
    }

    }


