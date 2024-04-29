package com.example.myprojectapp;

public class Apartment {
    public String landlord, address;
    public int id, maxAmount;
    public Apartment(){

    }

    public Apartment(int id, String landlord, String address, int maxAmount) {
        this.id = id;
        this.landlord = landlord;
        this.address = address;
        this.maxAmount = maxAmount;
    }
}
