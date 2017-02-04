package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

/**
 * Created by Harry on 06-Jan-17.
 */

public class Customer implements java.io.Serializable {
    private String username;
    private String password;
    private String address;

    public Customer(Customer c) {
        this.username = c.username;
        this.password = c.password;
        this.address = c.address;
    }

    public Customer(String u, String p, String a) {
        username = u; password = p; address = a;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getAddress(){
        return this.address;
    }
}
