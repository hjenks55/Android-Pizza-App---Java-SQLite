package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity1 extends AppCompatActivity {

    public DatabaseManager myDManager;
    public final static String EXTRA_MESSAGE = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.MESSAGE";
    String itemSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean isDelivery = ((MyApplication) this.getApplication()).getDeliveryStatus();
        boolean isPickup = ((MyApplication) this.getApplication()).getPickupStatus();

        if (isDelivery) {
            Intent intent = getIntent();
            String timeDate = intent.getStringExtra(DeliveryActivity2.EXTRA_DELIVERY_TIME);
            ((MyApplication) this.getApplication()).setDeliveryTime(timeDate);
        }
        if (isPickup) {
            Intent intent = getIntent();
            String timeDate = intent.getStringExtra(PickupActivity2.EXTRA_PICKUP_TIME);
            ((MyApplication) this.getApplication()).setPickupTime(timeDate);
        }

        //Pizza Button
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderActivity1.this, OrderActivity2.class);
                itemSelection = "pizza";
                intent.putExtra(EXTRA_MESSAGE, itemSelection);
                startActivity(intent);
            }

        });

        //Sides Button does not work
        /*
        Button sidesButton = (Button) findViewById(R.id.button4);
        sidesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity1.this, OrderActivity2.class);
                itemSelection = "sides";
                intent.putExtra(EXTRA_MESSAGE, itemSelection);
                startActivity(intent);
            }

        });
           */
    }


}

