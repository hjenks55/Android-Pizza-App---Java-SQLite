package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity2 extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.MESSAGE";

    private DatabaseManager mydManager;
    private ListView productRec;
    String itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fillListViewDatabase();
    }

    public boolean fillListViewDatabase() {

        String targetDb = null;

        Intent intent = getIntent();
        String itemSelection = intent.getStringExtra(OrderActivity1.EXTRA_MESSAGE);

        //Could not implement object orientated solution for List view custom adapter

//Pizza selection
        if (itemSelection.equals("pizza")) {
            mydManager = new DatabaseManager(this);
            mydManager.openReadable();

            ArrayList tableContent = mydManager.retrievePizzaListViewRows();
            productRec = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> arrayAdpt = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tableContent);
            productRec.setAdapter(arrayAdpt);
            productRec.setVisibility(View.VISIBLE);

            //Click list item from database to goto next activity
            productRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    String item = (String) productRec.getAdapter().getItem(position);

                    Intent intent = new Intent(OrderActivity2.this, OrderActivity3.class);
                    itemType = item;
                    intent.putExtra(EXTRA_MESSAGE, itemType);
                    startActivity(intent);
                }
            });
            mydManager.close();
            return true;
        }

//Sides  selection
        if (itemSelection.equals("sides")) {
            mydManager = new DatabaseManager(this);
            mydManager.openReadable();

            ArrayList tableContent = mydManager.retrieveSidesListViewRows();
            productRec = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> arrayAdpt = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tableContent);
            productRec.setAdapter(arrayAdpt);
            productRec.setVisibility(View.VISIBLE);

            //Click list item from database to goto next activity
            productRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    String item = (String) productRec.getAdapter().getItem(position);


                    Intent intent = new Intent(OrderActivity2.this, OrderActivity3.class);
                    itemType = item;
                    intent.putExtra(EXTRA_MESSAGE, itemType);
                    startActivity(intent);
                }
            });
            mydManager.close();
            return true;
        }return true;
    }

    public void fillListView() {
//Hard coded data only
//ListView & Customer Adapter*******************************************************************************************************************************
        final ListView list;
        String[] values = new String[] {
                "Ham and Pineapple", "Supreme", "Seafood",
                "Italian", "Meat lovers" };
        // use adaptor with rowlayout
        CustomAdapter adapter = new CustomAdapter(this, values);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        //Click list item even here -->
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                                            String item = (String) list.getAdapter().getItem(position);
                                            //Toast.makeText(getApplicationContext(), item + "   	selected", Toast.LENGTH_LONG).show();


                                        }
                                    }
        );
    }


    public void setButtons() {

        /*
        //Hawaiian Button for Order 3
        Button hawaiianButton = (Button) findViewById(R.id.button7);
        hawaiianButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderActivity2.this, OrderActivity3.class);
                pizzaType = "Hawaiian";
                intent.putExtra(EXTRA_MESSAGE, pizzaType);
                startActivity(intent);
            }

        });

        //Meatlovers Button for Order 3
        Button meatButton = (Button) findViewById(R.id.button8);
        meatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderActivity2.this, OrderActivity3.class);
                pizzaType = "Meatlovers";
                intent.putExtra(EXTRA_MESSAGE, pizzaType);
                startActivity(intent);
            }
        });
        */
    }

}
