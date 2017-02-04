package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuickOrderActivity extends AppCompatActivity {

    private DatabaseManager mydManager;
    private ListView productRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean isLoggedin = ((MyApplication) this.getApplication()).getLoggedIn();
        if (isLoggedin) {
            fileListView();
        }
        if (!isLoggedin) {
            String message = "Please login to use quick orders";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        /*
        Button quickOrderBtn = (Button) findViewById(R.id.button13);
        quickOrderBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuickOrderActivity.this, OrderActivity1.class);
                startActivity(intent);
            }

        });*/
    }
    public boolean fileListView() {

        mydManager = new DatabaseManager(this);
        mydManager.openReadable();

        String email = (((MyApplication) this.getApplication()).getEmail());
        ArrayList tableContent = mydManager.retrieveOrdersListViewRows();
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

                Intent intent = new Intent(QuickOrderActivity.this, OrderActivity4.class);
                //intent.putExtra(EXTRA_MESSAGE, item);
                startActivity(intent);
            }
        });
        mydManager.close();
         return true;
    }

}
