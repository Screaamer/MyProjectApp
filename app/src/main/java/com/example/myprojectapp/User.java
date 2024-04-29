package com.example.myprojectapp;

public class User {
    public String userNumber, userPassword;
    public int id;
    public User(){

    }
    public User(String userNumber, String userPassword, int id){
        this.userNumber = userNumber;
        this.userPassword = userPassword;
        this.id = id;
    }
}
