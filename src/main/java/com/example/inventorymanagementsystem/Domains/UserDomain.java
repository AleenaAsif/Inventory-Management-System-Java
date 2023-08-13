package com.example.inventorymanagementsystem.Domains;

public class UserDomain {
    private int userId;

    private String username;
    private String password;
    private String user_role;

    public UserDomain(int userId, String username, String password, String user_role){

        this.userId=userId;
        this.username=username;
        this.password=password;
        this.user_role=user_role;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getUserId(){
        return userId;
    }

    public void setUserName(String username){
        this.username=username;
    }
    public String getUserName(){
        return username;
    }

    public void setUserPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }

    public void setRole(String role){
        this.user_role=role;
    }
    public String getRole(){
        return user_role;
    }
}
