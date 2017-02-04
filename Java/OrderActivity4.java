package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity4 extends AppCompatActivity {

    SendSMS mSender = new SendSMS();
    public DatabaseManager myDManager;

    String item1,item2;
    int item1Qty, item2Qty;
    String item1QtyStr, item2QtyStr;

    TextView item1Text, item2Text;
    TextView qty1Text, qty2Text;
    int currentOrder;
    int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String delivery = String.valueOf(((MyApplication) this.getApplication()).getDeliveryStatus());
        //Toast.makeText(this, delivery, Toast.LENGTH_SHORT).show();

        myDManager = new DatabaseManager(this);

        getOrders();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });


        //Confirm Button -> proceed to sms
        Button button = (Button) findViewById(R.id.button12);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent nextIntent = new Intent(OrderActivity4.this, OrderActivity3.class);
                startActivity(nextIntent);
            }

        });

        //Cancel Button -> back to order3
        Button backButton = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                sendit(view);  //Send SMS
            }

        });
    }

    public void addItem() {

        item2Text = (TextView)findViewById(R.id.item2);
        if (item2Text.getText().equals("")) {
            ((MyApplication) this.getApplication()).setOrderItems(2);

            //Toast Debugging
            int item = ((MyApplication) this.getApplication()).getOrderItems();
            String itemStr = String.valueOf(item);
            //Toast.makeText(getApplicationContext(), "Order items =" +itemStr, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(OrderActivity4.this, OrderActivity1.class);
            startActivity(intent);

        }
    }

    public void getOrders() {

        //Toast Debugging
        int item = ((MyApplication) this.getApplication()).getOrderItems();
        String itemStr = String.valueOf(item);
        //Toast.makeText(getApplicationContext(), "Order items =" +itemStr, Toast.LENGTH_LONG).show();

        currentOrder = myDManager.getCurrentOrderId();

        //item 1
        item1 = myDManager.getItem1(currentOrder);
        item1Text = (TextView)findViewById(R.id.item1);

        //For pricing
        //String item1Price = String.valueOf(myDManager.retrievePizzaPrice(item1));
        //item1Text.setText(item1+" $"+item1Price+" each");

        item1Text.setText(item1);

        item1Qty = myDManager.getItem1Qty(currentOrder);
        item1QtyStr = String.valueOf(item1Qty);
        qty1Text = (TextView)findViewById(R.id.item1Qty);
        qty1Text.setText("x"+item1QtyStr);

        //Adding items
        currentItem = ((MyApplication) this.getApplication()).getOrderItems();

        //item2
        if (item == 2) {
            item2 = myDManager.getItem2(currentOrder);
            item2Text = (TextView)findViewById(R.id.item2);
            item2Text.setText(item2);

            item2Qty = myDManager.getItem2Qty(currentOrder);
            item2QtyStr = String.valueOf(item2Qty);
            qty2Text = (TextView)findViewById(R.id.item2Qty);
            qty2Text.setText("x"+item2QtyStr);
        }
    }

    public void sendit(View v) {
        // This is standard lorem-ipsum text

        String deliveryTime;
        Boolean isDelivery = ((MyApplication) this.getApplication()).getDeliveryStatus();
        if (isDelivery) {
            deliveryTime = ((MyApplication) this.getApplication()).getDeliveryTime();

            boolean success = mSender.sendSMSMessage("0409122199",
                    "Your order has been successfully received: \n   "+item1+" x"+item1QtyStr +"\nAnd will arrive at " + deliveryTime);
            Toast.makeText(this, "Order sent " + (success ? "successfully" : "unsuccessfully"),
                    Toast.LENGTH_SHORT).show();
        }

        String pickupTime;
        String store;
        Boolean isPickup = ((MyApplication) this.getApplication()).getPickupStatus();
        if (isPickup) {
            pickupTime = ((MyApplication) this.getApplication()).getPickupTime();
            store = ((MyApplication) this.getApplication()).getPickupStore();

            boolean success = mSender.sendSMSMessage("0409122199",
                    "Your order has been successfully received: \n   "+item1+" x"+item1QtyStr +"\nAnd will be ready to pickup at " + pickupTime + " " +
                            "at the " + store + " store.");
            Toast.makeText(this, "Order sent " + (success ? "successfully" : "unsuccessfully"),
                    Toast.LENGTH_SHORT).show();
        }

    }

}
