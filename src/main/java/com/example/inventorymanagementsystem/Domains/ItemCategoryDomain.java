package com.example.inventorymanagementsystem.Domains;

public class ItemCategoryDomain {

    private int id;
    private String category_name;

    public ItemCategoryDomain(int id, String category_name){
        this.id=id;
        this.category_name=category_name;
    }

    public ItemCategoryDomain() {

    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String categoryName) {
        this.category_name = categoryName;
    }
}
