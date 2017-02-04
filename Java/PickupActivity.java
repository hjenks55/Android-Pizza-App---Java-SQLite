package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PickupActivity extends AppCompatActivity {

    public final static String EXTRA_STORE = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.STORE";
    //RadioButton parramatta, auburn, ermington, concord;
    RadioGroup radioStoreGroup;
    RadioButton radioStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((MyApplication) this.getApplication()).setPickup(true);
        ((MyApplication) this.getApplication()).setDelivery(false);

        radioStoreGroup = (RadioGroup)findViewById(R.id.radioStore);


        //Next Navigation Button
        Button nextButton = (Button) findViewById(R.id.button15);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int selectedId = radioStoreGroup.getCheckedRadioButtonId();
                radioStoreButton = (RadioButton)findViewById(selectedId);


                String store = (String) radioStoreButton.getText();
                Intent intent = new Intent(PickupActivity.this, PickupActivity2.class);
                intent.putExtra(EXTRA_STORE, store);
                startActivity(intent);




            }

        });

        //Back Navigation Button
        Button backButton = (Button) findViewById(R.id.button14);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               ;
                Intent intent = new Intent(PickupActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }

}
