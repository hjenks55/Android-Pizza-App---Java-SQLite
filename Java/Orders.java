package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

/**
 * Created by Harry on 06-Jan-17.
 */

public class Orders {

    private String username;
    private String address;
    private String[] pizzas;
    private int[] qty;

    //Constructor
    public Orders(String accountUsername, String userAddress, String[]selectedPizzas, int[]pizzaQty) {
        username = accountUsername;
        address = userAddress;
        pizzas = selectedPizzas;
        qty = pizzaQty;
    }

    //No Arg Constructor
    public Orders() {
        username = null;
        address = null;
        pizzas = null;

    }

    //Setters
    public void setUsername(String accountUsername) {
        username = accountUsername;
    }
    public void setAddress(String userAddress) {
        address = userAddress;
    }
    public void setPizzas(String[] selectedPizzas) {
        pizzas = selectedPizzas;
    }
    public void setQty(int[]pizzaQty) {
        qty = pizzaQty;
    }

    //Getters
    public String getUsername() {
        return username;
    }
    public String getAddress() {
        return address;
    }
    public String[] getPizzas() {
        return pizzas;
    }
    public int[] getQty() {
        return qty;
    }


}
