package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.phoneNumber;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    public DatabaseManager myDManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createPizza();
        //createSides();does not work
        createOrder();

        final Boolean isLoggedIn = ((MyApplication) this.getApplication()).getLoggedIn();

        //***********Quick Order button
        Button quickOrderBtn = (Button) findViewById(R.id.quickOrder);
        quickOrderBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuickOrderActivity.class);
                startActivity(intent);
            }

        });
        //********** Delivery button
        Button deliveryBtn = (Button) findViewById(R.id.delivery);
        deliveryBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //If user logged is not logged in go to enter address screen
                if (isLoggedIn == false) {
                    Intent intent = new Intent(MainActivity.this, DeliveryActivity.class);
                    startActivity(intent);
                }
                //If user is logged in skip to confirm time
                if (isLoggedIn == true) {
                    Intent intent = new Intent(MainActivity.this, DeliveryActivity2.class);
                    startActivity(intent);
                }

            }
        });
        //********** Pickup button
        Button pickupBtn = (Button) findViewById(R.id.pickup);
        pickupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createPizza() {
        //Create database *************************************************************************

        myDManager = new DatabaseManager(this);
        myDManager.addPizzasRow(1, "Pepperoni", "Pepperoni slices, mozerella on a tomato base", Float.parseFloat("10"));
        myDManager.addPizzasRow(2, "Mexican", "Chorizo, beef, capsicum, mozerella onions with a spicy tomato base", Float.parseFloat("15"));
        myDManager.addPizzasRow(3, "Hawaiian", "Pineapple & ham", Float.parseFloat("12"));
        myDManager.addPizzasRow(4, "Meatlovers", "Pepperoni slices, bacon, ham & mozerella on a tomato base", Float.parseFloat("10"));
        myDManager.addPizzasRow(5, "Supreme", "Olives, capsicum, ham, salami", Float.parseFloat("16"));

        myDManager.close();
    }

    public void createSides() {
        //Create database *************************************************************************

        myDManager = new DatabaseManager(this);
        myDManager.addSidesRow(1, "Garlic Bread", "Crunchy bread with garlic and herbs", Float.parseFloat("5"));
        myDManager.addSidesRow(2, "French Fries", "Thinly cut chips with sauce", Float.parseFloat("4"));

        myDManager.close();
    }
    public void createOrder() {

        myDManager = new DatabaseManager(this);
        //User info to be added here
        int currentOrder = myDManager.getCurrentOrderId();
        if (currentOrder == 0){
            myDManager.addOrdersRow(1, "test@gmail.com", "123 Fake St", 0.0f, "", 0, "", 0, "", 0, "", 0, "", 0, "", 0);
        }
        else {
            int nextId = myDManager.getCurrentOrderId() + 1;
            myDManager.addOrdersRow(nextId, "test@gmail.com", "123 Fake St", 0.0f, "", 0, "", 0, "", 0, "", 0, "", 0, "", 0);
        }
        /*******Toast Debugging - get currnet order number******
        String itemStr = String.valueOf(currentOrder);
        Toast.makeText(getApplicationContext(), "Current Order = "+itemStr, Toast.LENGTH_SHORT).show();
*/
        if ( ((MyApplication) this.getApplication()).getOrderItems() == 0) {
            ((MyApplication) this.getApplication()).setOrderItems(1);
        }

        myDManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                return true;

            case R.id.action_signUp:
                Intent intentSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}