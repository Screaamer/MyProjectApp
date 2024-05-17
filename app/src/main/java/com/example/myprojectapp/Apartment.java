package com.example.myprojectapp;

import java.util.ArrayList;

public class Apartment {
    public String landlord, address;
    public int id, maxAmount;
    public ArrayList<String> listOfImages;
    public Apartment(){

    }

    public Apartment(int id, String landlord, String address, int maxAmount, ArrayList<String> listOfImages) {
        this.id = id;
        this.landlord = landlord;
        this.address = address;
        this.maxAmount = maxAmount;
        this.listOfImages = listOfImages;
    }
}
