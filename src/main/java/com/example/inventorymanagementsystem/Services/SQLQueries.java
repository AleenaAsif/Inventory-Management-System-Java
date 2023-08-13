package com.example.inventorymanagementsystem.Services;
public class SQLQueries {

    public static final String getAllInventories="SELECT * FROM inventory";
    public static final String getInventoryById="SELECT * FROM inventory WHERE id=?";

    public static final String addInventory="INSERT INTO inventory (item_name, item_quantity, item_category_id, item_location_id) VALUES (?, ?, ?, ?)";
    public static final String getInventoryByCategory="SELECT * FROM inventory WHERE item_category_id=?";


    public static final String getInventoryByLocation="SELECT * FROM inventory WHERE item_location_id=?";


    public static final String updateInventory = "UPDATE inventory SET item_name = ?, item_quantity = ?, item_category_id = ?, item_location_id = ? WHERE id = ?";
    public static final String deleteInventory = "DELETE FROM inventory WHERE id = ?";
    public static final String getInventoryByLocationAndCategory =
            "SELECT * FROM inventory WHERE item_location_id = ? AND item_category_id = ?";

    public static final String userCheck="SELECT * FROM users WHERE username=? AND password=?";

    public static final String getItemCategoryById="SELECT * FROM itemcategory WHERE id=?";

    public static final String getItemLocationById="SELECT * FROM itemlocation WHERE id=?";

    public static final String getCategoryIdByName = "SELECT id FROM itemcategory WHERE category_name = ?";
    public static final String getLocationIdByName = "SELECT id FROM itemlocation WHERE location_name = ?";

}

