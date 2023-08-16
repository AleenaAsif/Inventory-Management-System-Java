package com.example.inventorymanagementsystem.Services;
public class SQLQueries {

    public static final String getAllInventories="SELECT i.id, i.item_name, i.item_quantity, c.id AS category_id, c.category_name, l.id AS location_id, l.location_name " +
            "FROM inventory i " +
            "JOIN itemcategory c ON i.item_category_id = c.id " +
            "JOIN itemlocation l ON i.item_location_id = l.id";
    public static final String getInventoryById="SELECT i.id, i.item_name, i.item_quantity, " +
            "       c.id AS category_id, c.category_name, " +
            "       l.id AS location_id, l.location_name " +
            "FROM inventory i " +
            "JOIN itemcategory c ON i.item_category_id = c.id " +
            "JOIN itemlocation l ON i.item_location_id = l.id " +
            "WHERE i.id = ?";

    public static final String addInventory="INSERT INTO inventory (item_name, item_quantity, item_category_id, item_location_id) VALUES (?, ?, ?, ?)";
    public static final String getInventoryByCategory="SELECT i.id, i.item_name, i.item_quantity, " +
            "i.item_category_id, i.item_location_id, " +
            "c.id AS category_id, c.category_name, " +
            "l.id AS location_id, l.location_name " +
            "FROM inventory i " +
            "JOIN itemcategory c ON i.item_category_id = c.id " +
            "JOIN itemlocation l ON i.item_location_id = l.id " +
            "WHERE i.item_category_id = ?";


    public static final String getInventoryByLocation="SELECT i.id, i.item_name, i.item_quantity, " +
            "i.item_category_id, i.item_location_id, " +
            "c.id AS category_id, c.category_name, " +
            "l.id AS location_id, l.location_name " +
            "FROM inventory i " +
            "JOIN itemcategory c ON i.item_category_id = c.id " +
            "JOIN itemlocation l ON i.item_location_id = l.id " +
            "WHERE i.item_location_id = ?";

    public static final String updateInventory = "UPDATE inventory SET item_name = ?, item_quantity = ?, item_category_id = ?, item_location_id = ? WHERE id = ?";
    public static final String deleteInventory = "DELETE FROM inventory WHERE id = ?";
    public static final String getInventoryByLocationAndCategory =
            "SELECT i.id, i.item_name, i.item_quantity, " +
                    "i.item_category_id, i.item_location_id, " +
                    "c.category_name, " +
                    "l.location_name " +
                    "FROM inventory i " +
                    "JOIN itemcategory c ON i.item_category_id = c.id " +
                    "JOIN itemlocation l ON i.item_location_id = l.id " +
                    "WHERE i.item_location_id = ? AND i.item_category_id = ?";

    public static final String userCheck="SELECT * FROM users WHERE username=? AND password=?";

    public static final String getItemCategoryById="SELECT * FROM itemcategory WHERE id=?";

    public static final String getItemLocationById="SELECT * FROM itemlocation WHERE id=?";

    public static final String getCategoryIdByName = "SELECT id FROM itemcategory WHERE category_name = ?";
    public static final String getLocationIdByName = "SELECT id FROM itemlocation WHERE location_name = ?";

}

