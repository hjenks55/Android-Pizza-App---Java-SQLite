package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DeliveryActivity extends AppCompatActivity {

    public final static String EXTRA_ADDRESS = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.ADDRESS";
    EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addressText = (EditText)findViewById(R.id.editText);

        //Next Navigation Button
        Button nextButton = (Button) findViewById(R.id.button19);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String address = addressText.getText().toString();

                //Toast Validation variables for Enter Address
                Context context = getApplicationContext();
                CharSequence text = "Please enter an address";
                int duration = Toast.LENGTH_SHORT;
                if (address.equals("")) {
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                //Go to next Step
                else if (!address.equals("")) {
                    Intent intent = new Intent(DeliveryActivity.this, DeliveryActivity2.class);
                    intent.putExtra(EXTRA_ADDRESS, address);

                    startActivity(intent);
                }
            }
        });

        //Cancel button
        Button cancelButton = (Button) findViewById(R.id.button18);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
