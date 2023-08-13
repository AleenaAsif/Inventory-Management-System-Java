package com.example.inventorymanagementsystem.Services;

import com.example.inventorymanagementsystem.Services.SQLQueries;

import com.example.inventorymanagementsystem.DatabaseConnectionManager;
import com.example.inventorymanagementsystem.Domains.ItemCategoryDomain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ItemCategoryService {
    private static final Logger logger = LoggerFactory.getLogger("LoggerFile");
    public ItemCategoryDomain getItemCategoryById(int categoryId) {
        ItemCategoryDomain itemCategory = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQueries.getItemCategoryById)) {

            statement.setInt(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    itemCategory = new ItemCategoryDomain();
                    itemCategory.setId(resultSet.getInt("id"));
                    itemCategory.setCategoryName(resultSet.getString("category_name"));
                    logger.info("got item by id: ItemCategoryService");
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to get item by id: ItemCategoryService");
            e.printStackTrace();

        }

        return itemCategory;
    }}

