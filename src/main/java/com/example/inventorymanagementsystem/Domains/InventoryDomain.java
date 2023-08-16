package com.example.inventorymanagementsystem.Domains;

public class InventoryDomain {

    private int id;
    private String itemName;

    private int itemQuantity;
    private ItemCategoryDomain itemCategory;
    private ItemLocationDomain itemLocation;

    public InventoryDomain(int id, String itemName, int itemQuantity, ItemLocationDomain itemLocation, ItemCategoryDomain itemCategory) {
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

    public void setItemLocation(ItemLocationDomain itemLocation) {
        this.itemLocation = itemLocation;
    }

    public ItemLocationDomain getItemLocation() {
        return itemLocation;
    }

    public void setItemCategory(ItemCategoryDomain itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ItemCategoryDomain getItemCategory() {
        return itemCategory;
    }
}
