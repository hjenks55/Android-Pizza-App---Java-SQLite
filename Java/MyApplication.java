package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.app.Application;

/**
 * Created by Harry on 08-Jan-17.
 */

public class MyApplication extends Application {

    //Global Variables
    private boolean isLoggedIn;
    private String email;
    private String test; // Just to get String value of isLoggedIn
    private int orderItems; //How many items are on the order
    private boolean isDelivery;
    private boolean isPickup;
    private String deliveryAddress;
    private String deliveryTime;
    private String pickupStore;
    private String pickupTime;

    //Getters
    public boolean getLoggedIn() {
        return isLoggedIn;
    }
    public int getOrderItems() {
        return orderItems;
    }
    public String getStringLoggedIn() {
        test = String.valueOf(isLoggedIn);
        return test;
    }//for returning String value of boolean isLoggedIn
    public String getEmail() {
        return email;
    }
    public boolean getDeliveryStatus(){ return isDelivery;}
    public boolean getPickupStatus() { return isPickup;}
    public String getDeliveryAddress(){
        return deliveryAddress;
    }
    public String getDeliveryTime() {
        return deliveryTime;
    }
    public String getPickupStore() {
        return pickupStore;
    }
    public String getPickupTime() {
        return pickupTime;
    }

    //Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    public void setOrderItems(int orderItems) {
        this.orderItems = orderItems;
    }
    public void setDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }
    public void setPickup(boolean isPickup) { this.isPickup = isPickup;}
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public void setPickupStore(String pickupStore) {
        this.pickupStore = pickupStore;
    }
    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

}
