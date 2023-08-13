package com.example.inventorymanagementsystem.Domains;

public class ItemLocationDomain {
    private int id;
    private String location_name;

    public ItemLocationDomain(int id, String location_name) {
        this.id = id;
        this.location_name = location_name;
    }

    public ItemLocationDomain() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLocationName(String location_name) {
        this.location_name = location_name;
    }

    public String getLocationName() {
        return location_name;
    }
}
