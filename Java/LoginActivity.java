package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoginActivity extends AppCompatActivity {
    Button ok, cancel;
    EditText username, password;
    String usernameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText3);
        cancel = (Button)findViewById(R.id.button20);
        ok = (Button)findViewById(R.id.button21);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FileInputStream inputStream;
        try {

            inputStream =  openFileInput("myobjfile");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Customer c = new Customer((Customer)ois.readObject());

            TextView txtUsername =(TextView) findViewById(R.id.textView21);
            TextView txtPassword =(TextView) findViewById(R.id.textView22);
            TextView txtAddress =(TextView) findViewById(R.id.textView23);
            /*
            txtUsername.setText(c.getUsername());
            txtPassword.setText(c.getPassword());
            txtAddress.setText(c.getAddress());
            */


        } catch ( Exception ioe ) {
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readObjectFile();
            }

        });
    }

    public void readObjectFile() {
        FileInputStream inputStream;
        try {

            inputStream =  openFileInput("myobjfile");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Customer c = new Customer((Customer)ois.readObject());
            TextView txtUsername =(TextView) findViewById(R.id.textView22);

            usernameStr = username.getText().toString();
            String passwordStr = password.getText().toString();
            if (usernameStr.equals(c.getUsername()) && passwordStr.equals(c.getPassword())){

                txtUsername.setText("Success!");

                String message = "Logged in to your account";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                ((MyApplication) this.getApplication()).setLoggedIn(true);
                ((MyApplication) this.getApplication()).setEmail(usernameStr);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else txtUsername.setText("Account not found. Please try again");
        } catch ( Exception ioe ) {
        }

    }

}
