package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.R.id.numberPicker;

public class OrderActivity3 extends AppCompatActivity {
    public final static String EXTRA_PIZZA = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.PIZZA";
    public final static String EXTRA_QTY = "com.example.harry.mad_assignment1_harrisonjenkins_17967352.QTY";

    NumberPicker np;
    int qty;
    String itemType;
    ImageView pizzaImage;
    public DatabaseManager myDbManager;

    int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Toast Debugging
        int item = ((MyApplication) this.getApplication()).getOrderItems();
        String itemStr = String.valueOf(item);
        //Toast.makeText(getApplicationContext(), "Order items =" +itemStr, Toast.LENGTH_LONG).show();

        //currentItem = ((MyApplication)this.getApplication()).getOrderItems() + 1;
        Intent intent = getIntent();
        itemType = intent.getStringExtra(OrderActivity2.EXTRA_MESSAGE);

        //TO-DO Image and Description
        pizzaImage = (ImageView)findViewById(R.id.imageView3);

        if (itemType.equals("Hawaiian")){
            pizzaImage.setImageResource(R.drawable.hawaiian);
        }
        if (itemType.equals("Pepperoni")){
            pizzaImage.setImageResource(R.drawable.pepperoni);
        }
        if (itemType.equals("Meatlovers")){
            pizzaImage.setImageResource(R.drawable.meatlovers);
        }
        if (itemType.equals("Supreme")){
            pizzaImage.setImageResource(R.drawable.supreme);
        }
        if (itemType.equals("Mexican")){
            pizzaImage.setImageResource(R.drawable.seafood);
        }



        String description = null;
        TextView pizzaDescText = (TextView)findViewById(R.id.pizzaDesc);
        pizzaDescText.setText(description);
        //

        TextView pizzaTypeText = (TextView)findViewById(R.id.pizzaText);
        pizzaTypeText.setText(itemType);

        //Number Picker
        np = (NumberPicker)this.findViewById(numberPicker);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);

        myDbManager = new DatabaseManager(this);

        //*****Button for next Activity******
        Button nextButton = (Button) findViewById(R.id.button10);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                qty = np.getValue();
                int currentOrder = myDbManager.getCurrentOrderId();
                String item = itemType;
                myDbManager.updateItem(currentOrder, 1, item, qty);

                Intent nextIntent = new Intent(OrderActivity3.this, OrderActivity4.class);
                startActivity(nextIntent);
            }
        });

        //Navigation Buttons
        //****************Cancel Button for previous Activity********************
        Button cancelButton = (Button) findViewById(R.id.button9);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent cancelIntent = new Intent(OrderActivity3.this, OrderActivity2.class);
                startActivity(cancelIntent);
            }
        });

    }

    //http://stackoverflow.com/questions/6783327/setimageresource-from-a-string
    //Retrieve image
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("R.drawable/" + imageName, null, context.getPackageName());
    }
}
