package com.example.inventorymanagementsystem.Domains;

public class InventoryDomain {

    private int id;
    private String itemName;

    private int itemQuantity;
    private String itemCategory;

    private String itemLocation;

    public InventoryDomain(int id, String itemName, int itemQuantity, String itemLocation, String itemCategory) {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemLocation = itemLocation;
        this.itemCategory = itemCategory;
    }

    public InventoryDomain() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCategory() {
        return itemCategory;
    }
}
