package com.example.myprojectapp;

import java.util.ArrayList;

public class User {
    public String userPhone, userPassword, userName, userTg;
    public int userId;
    public ArrayList<Integer> favorites;
    public User(){

    }
    public User(String userPhone, String userPassword, String userName, String userTg, int userId, ArrayList<Integer> favorites){
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userTg = userTg;
        this.userId = userId;
        this.favorites = favorites;
    }
}
