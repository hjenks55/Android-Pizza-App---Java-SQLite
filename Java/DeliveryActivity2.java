package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DeliveryActivity2 extends AppCompatActivity {

    public final static String EXTRA_DELIVERY_TIME = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.DELIVERYTIME";
    String time = null;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((MyApplication) this.getApplication()).setDelivery(true);
        ((MyApplication) this.getApplication()).setPickup(false);

        Boolean isLoggedIn = ((MyApplication) this.getApplication()).getLoggedIn();
        if (!isLoggedIn) {
            //get store data from previous screen
            Intent intent = getIntent();
            String address = intent.getStringExtra(DeliveryActivity.EXTRA_ADDRESS);
            TextView addressText = (TextView)findViewById(R.id.textView14);
            addressText.setText("Address: " + address);
            ((MyApplication) this.getApplication()).setDeliveryAddress(address);
        }

        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        int hour = tp.getCurrentHour();
        int minute = tp.getCurrentMinute();
        time = Integer.toString(hour)+":"+Integer.toString(minute);

        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        int year = dp.getYear();
        int month = dp.getMonth() + 1;
        int day = dp.getDayOfMonth();
        date = Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);

        Button nextButton = (Button) findViewById(R.id.button17);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String timeDate = time + " " + date;
                Intent intent = new Intent(DeliveryActivity2.this, OrderActivity1.class);
                intent.putExtra(EXTRA_DELIVERY_TIME, timeDate);
                startActivity(intent);
            }

        });

        Button cancelButton = (Button) findViewById(R.id.button16);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity2.this, DeliveryActivity.class);
                startActivity(intent);
            }

        });
    }
}


