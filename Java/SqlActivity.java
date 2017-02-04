package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SqlActivity extends AppCompatActivity {

    public TextView pizza;
    public TextView toppings;
    public DatabaseManager pizzaDatabase;
    public ImageView pizzaImage;
    public Button pizzaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        pizza = (TextView)findViewById(R.id.textViewPizza);
        toppings = (TextView)findViewById(R.id.textViewToppings);

        pizzaDatabase = new DatabaseManager(this);

        pizzaDatabase.addPizzasRow(1, "Pepperoni", "Pepperoni slices, mozerella on a tomato base", Float.parseFloat("10"));
        pizzaDatabase.addPizzasRow(2, "Mexican", "Chorizo, beef, capsicum, mozerella onions with a spicy tomato base", Float.parseFloat("15"));

        pizzaDatabase.openReadable();

        String pizzaType = pizzaDatabase.retrievePizza();
        String pizzaToppings = pizzaDatabase.retrieveToppings();
        pizza.setText(pizzaType);
        toppings.setText(pizzaToppings);

        pizzaImage = (ImageView)findViewById(R.id.imageView2);
        pizzaImage.setImageResource(R.drawable.pepperoni);

        pizzaButton = (Button)findViewById(R.id.pizzaButton);
        pizzaButton.setText(pizzaType);

        pizzaDatabase.close();
    }


}
