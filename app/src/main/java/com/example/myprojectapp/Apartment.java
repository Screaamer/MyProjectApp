package com.example.myprojectapp;

import java.util.ArrayList;

public class Apartment {
    public String city, address, description;
    public int apartmentId, landlordId, maxAmount;
    public ArrayList<String> listOfImages;
    public Apartment(){

    }

    public Apartment(int apartmentId, int landlordId, String city, String address, int maxAmount, ArrayList<String> listOfImages, String  description) {
        this.apartmentId = apartmentId;
        this.landlordId = landlordId;
        this.city = city;
        this.address = address;
        this.maxAmount = maxAmount;
        this.listOfImages = listOfImages;
        this.description = description;
    }
}
